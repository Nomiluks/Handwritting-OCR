/*
 * Copyright (C) 2012 WIMM Labs Incorporated
 */

package com.wimm.demo.wimmbtdemo;

import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wimm.framework.app.LauncherActivity;
import com.wimm.framework.app.TextInputDialog;
import com.wimm.framework.view.ViewTray;
import com.wimm.framework.widget.Button;


/*
 * This is a demo of Bluetooth connectivity on the WIMM One.
 * This app is designed to work with the BluetoothChat sample
 * code found in Android SDK 2.1 (API 7).
 * It allows user to initiate a Bluetooth connection to an Android
 * phone that has been previously paired with the WIMM. It is
 * also capable of accepting connection coming from the same
 * Android device.
 * After a connection has been establish, you would be able to
 * send data (message) between the two device.
 *
 * NOTE:
 * As of WIMM SDK 1.0.3:
 * 1. You can only be paired to one device.
 * 2. You cannot turn on Bluetooth programmatically.
 * BluetoothAdapter.ACTION_REQUEST_ENABLE Intent is not supported.
 * 3. You cannot make the WIMM discoverable over Bluetooth programmatically.
 * BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE Intent is not supported.
 *
 * Last updated: 04/10/2012
 */

public class WIMMBTDemoActivity extends LauncherActivity implements OnClickListener {
    // For debugging purposes.
    public final static String TAG = WIMMBTDemoActivity.class.getSimpleName();

    // Message types for WIMMBTDemoManager handler.
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;

    private BluetoothAdapter mBluetoothAdapter;
    // Member object for our Bluetooth services
    private WIMMBTDemoManager mWIMMBTDemoManager;

    // Layout Views.
    private ViewTray mViewTray;
    private TextView mTitle;
    private TextView mTitle2;
    private TextView mConnectionStatusText;
    private ConversationListView mConversationView;
    private Button mConnectionButton;
    private Button mWriteMessageButton;
    private TextInputDialog mTextInput = null;

    // The paired device.
    private BluetoothDevice mPairedDevice = null;
    // Array Adapter for the conversation thread.
    private ArrayAdapter<String> mConversationArrayAdapter;
    // The current UI connection state;
    private int mState = WIMMBTDemoManager.STATE_NONE;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Start off the Connection service.
        mWIMMBTDemoManager = new WIMMBTDemoManager(this, mHandler);
        mWIMMBTDemoManager.ListenForConnection();

        // Initialize the local bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Initialize the array adapter for the conversation thread
        mConversationArrayAdapter = new ArrayAdapter<String>(this, R.layout.message);
        mConversationView = (ConversationListView) findViewById(R.id.conversation_tray_messages);
        mConversationView.setAdapter(mConversationArrayAdapter);

        mViewTray = (ViewTray) findViewById(R.id.view_tray);
        mTitle = (TextView) findViewById(R.id.connection_tray_title);
        mTitle2 = (TextView) findViewById(R.id.conversation_tray_title);
        mConnectionStatusText = (TextView) findViewById(R.id.connection_tray_status_text);

        mConnectionButton = (Button) findViewById(R.id.connect_button);
        mConnectionButton.setOnClickListener(this);
        mWriteMessageButton = (Button) findViewById(R.id.write_message_button);
        mWriteMessageButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mWriteMessageButton) {
            getTextInput();
        }
        else if(view == mConnectionButton) {
            // Connections button triggered
            if (mWIMMBTDemoManager == null) {
                return;
            }

            if (mState == WIMMBTDemoManager.STATE_CONNECTED) {
                mWIMMBTDemoManager.ListenForConnection();
            }
            else {
                mWIMMBTDemoManager.connect(mPairedDevice);
            }
        }
        else {
            Log.e(TAG, "Invalid button triggered");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        BluetoothDevice oldPairedDevice = mPairedDevice;

        // We will only support one device pairing.
        if (mBluetoothAdapter.getBondedDevices().size() == 1) {
            mPairedDevice = pairedDevices.iterator().next();
        }
        else {
            // set mPairedDevice to null otherwise.
            mPairedDevice = null;
        }

        // Only update UI when they is a change in Paired Device.
        if (oldPairedDevice != mPairedDevice) {
            updateConnectionUIStatus();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cleaning up
        mWIMMBTDemoManager.closeAllThreads();

    }

    @Override
    public boolean dragCanExit() {
        // Prevent a downward drag from exiting the activity
        // if the scroll view is showing and its content is
        // not scrolled to the top.
        return (mViewTray.getIndex() != 1 ) || mConversationView.isAtTop();
    }

    // This will show our default TextInputdialog to get user inputs.
    private void getTextInput() {
        if (mTextInput == null) {
            // setup a new dialog
            mTextInput = new TextInputDialog(this, true, null);
            mTextInput.setTitle(R.string.dialog_title);
            mTextInput.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    sendMessage(mTextInput.getText());
                }
            });
        }

        mTextInput.setText("");
        mTextInput.show();
    }

    // A helper functions that set UI depending on the current connection status.
    private void updateConnectionUIStatus(){

        // We must have a device paired to do anything useful.
        if (mPairedDevice == null) {
            mConnectionButton.setClickable(false);
            mWriteMessageButton.setClickable(false);
            mConnectionStatusText.setText(R.string.status_no_paired_device_text);
            return;
        }

        switch (mState) {
            case WIMMBTDemoManager.STATE_NONE:
                // Fall through to STATE_LISTEN as STATE_NONE does
                // nothing in term of UI.
            case WIMMBTDemoManager.STATE_LISTEN:
                mWriteMessageButton.setClickable(false);

                // Set the connection button to "connect" to allow the user to begin.
                mConnectionButton.setClickable(true);
                mConnectionButton.setText(R.string.connect_button_title);

                // update status text
                mConnectionStatusText.setText(R.string.status_disconnected);
                mConnectionStatusText.append(mPairedDevice.getName());

                // Update titles
                mTitle.setText(R.string.header_title_disconnected);
                mTitle2.setText(R.string.header_title_disconnected);

                // Move to main tray
                mViewTray.setIndex(0, true);

                break;
            case WIMMBTDemoManager.STATE_CONNECTING:
                // Disable connect button until we finish our current connection
                // attempt
                mConnectionButton.setClickable(false);

                // update status text
                mConnectionStatusText.setText(R.string.status_connecting);
                mConnectionStatusText.append(mPairedDevice.getName());

                // Update titles
                mTitle.setText(R.string.header_title_connecting);
                mTitle2.setText(R.string.header_title_connecting);
                break;
            case WIMMBTDemoManager.STATE_CONNECTED:
                mWriteMessageButton.setClickable(true);

                // Switch the connection button to the "disconnect" state
                mConnectionButton.setClickable(true);
                mConnectionButton.setText(R.string.disconnect_button_title);

                mConnectionStatusText.setText(R.string.status_connected);
                mConnectionStatusText.append(mPairedDevice.getName());

                // Update titles
                mTitle.setText(R.string.header_title_connected);
                mTitle2.setText(R.string.header_title_connected);

                // Clear previous conversation thread.
                mConversationArrayAdapter.clear();

                // Move to conversation tray
                mViewTray.setIndex(1, true);
                break;
            default:
                Log.e(TAG, "Invalid state at updateConnectionUIStatus()");
                break;
        }
    }

    /**
     * Send a message
     * @param message  String to be send to remote device
     */
    private void sendMessage(String message) {
        if (mState != WIMMBTDemoManager.STATE_CONNECTED ||
                message.length() == 0) {
            // If we aren't connected or the message is empty we have nothing to do.
            return;
        }

        byte[] send = message.getBytes();
        mWIMMBTDemoManager.write(send);
    }

    // The handler that will handle message from WIMMBTDemoManager
    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
            case MESSAGE_STATE_CHANGE:
                // update the local UI connection status
                mState = msg.arg1;
                // update connection UI status
                updateConnectionUIStatus();
                break;
            case MESSAGE_WRITE:
                String writeMessage = new String((byte[]) msg.obj);
                mConversationArrayAdapter.add("Me:  " + writeMessage);
                break;
            case MESSAGE_READ:
                String readMessage = new String((byte []) msg.obj, 0, msg.arg1);
                mConversationArrayAdapter.add(mPairedDevice.getName() + ":  " + readMessage);
                break;
            }
        }

    };
}