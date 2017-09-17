/*
 * Copyright (C) 2012 WIMM Labs Incorporated
 */

package com.wimm.demo.wimmbtdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

/*
 * This is the class used by the WIMMBTDemoActivity to initiate, accept and
 * manage a Bluetooth connection. It has a thread that listen to incoming
 * connection, a thread used to initiate outgoing connection and a thread
 * that managed an ongoing connection.
 */
public class WIMMBTDemoManager {
    // For debugging purposes
    private static final String TAG = WIMMBTDemoManager.class.getSimpleName();
    // Set to True to enable debugging logs
    private static final boolean DEBUG_LOG = true;

    // Constants indicating connection state
    // Not doing anything
    public static final int STATE_NONE = 0;
    // Listening to incoming Bluetooth connections.
    public static final int STATE_LISTEN = 1;
    // Initiating an outgoing Bluetooth connection.
    public static final int STATE_CONNECTING = 2;
    // Connected to a remote device.
    public static final int STATE_CONNECTED = 3;

    // UUID for this application, this is the same the one used in Android SDK sample code for Bluetoothchat.
    private static final UUID mUUID = UUID.fromString("d0c722b0-7e15-11e1-b0c4-0800200c9a66");

    private final BluetoothAdapter mBluetoothAdapter;
    // Handler used to update the main UI activity.
    private final Handler mHandler;
    // Thread that will listen for external connections.
    private AcceptThread mAcceptThread;
    // The thread used to initiate a connection.
    private ConnectThread mConnectThread;
    // Thread that will hold the connected socket.
    private ConnectedThread mConnectedThread;
    // The current connection state.
    private int mState;

    public WIMMBTDemoManager (Context context, Handler handler) {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        setState(STATE_NONE);
        mHandler = handler;
    }

    /**
     * Set the current state of the bluetooth connection, this will notify
     * the UI Activity thread.
     * @param state  An integer constant that defines the connection state
     */
    private synchronized void setState(int state) {
        if (DEBUG_LOG) Log.d(TAG, "setState() " + mState + " -> " + state);
        if (mState == state) return;

        mState = state;

        // Update the UI Activity thread of the state changes
        mHandler.obtainMessage(WIMMBTDemoActivity.MESSAGE_STATE_CHANGE, state, -1).sendToTarget();
    }

//    /**
//     * Returns the current connection state
//     * @return state  An integer constant that defines the connection state
//     */
//    public synchronized int getState() {
//        return mState;
//    }

    /**
     * Reset the connection state and put the service back to
     * listening mode.
     */
    public synchronized void ListenForConnection() {
        if (DEBUG_LOG) Log.d(TAG, "ListenForConnection(): Listening for connections");

        // Reset mState
        setState(STATE_NONE);

        // Cancel any thread that is making a connection
        if (mConnectThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        // Cancel any thread currently managing a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        // Start an AcceptThread to listen for Bluetooth connection
        if (mAcceptThread == null) {
            mAcceptThread = new AcceptThread();
            mAcceptThread.start();
        }

        setState(STATE_LISTEN);

    }

    /**
     * Initiate an connection to a remote device
     * @params device  The BluetoohDevice to initiate an connection to.
     */
    public synchronized void connect(BluetoothDevice device) {
        if (DEBUG_LOG) Log.d(TAG, "connect(): Initating connection to "+device.getName());

        // Cancel any thread that is attempting to make an outgoing connection
        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        // Cancel any thread that is managing a connection
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        // Start a ConnectThread to initiate a connection to the give device
        mConnectThread = new ConnectThread(device);
        mConnectThread.start();

        setState(STATE_CONNECTING);
    }

    /**
     * Start the ConnectedThread to manage a Bluetooth connection
     * @param socket  The BluetoothSocket for the connection
     * @param device  The BluetoothDevice that has been connected
     */
    private synchronized void connected(BluetoothSocket socket, BluetoothDevice device) {
        if (DEBUG_LOG) Log.d(TAG, "connected(): managing " + device.getName());

        closeAllThreads();

        // Start a new ConnectedThread to manage the connection
        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();

        setState(STATE_CONNECTED);
    }

    /**
     * This will stop all threads. Used for clean up.
     */
    public synchronized void closeAllThreads() {
        if (DEBUG_LOG) Log.d(TAG, "stop(): Cleaning up all threads");

        if (mConnectThread != null) {
            mConnectThread.cancel();
            mConnectThread = null;
        }

        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }

        if (mAcceptThread != null) {
            mAcceptThread.cancel();
            mAcceptThread = null;
        }

    }


    /**
     * This will write to the socket managed by mConnectedThread.
     * @param out  The bytes to write
     */
    public void write(byte[] out) {
        // Create temporary ConnectThread
        ConnectedThread tmp;

       // These need to be synchronized since connections can be dropped without
       // warning.
       synchronized (this) {
           if (mState != STATE_CONNECTED || mConnectedThread == null) return;
           tmp = mConnectedThread;
       }

       // Perform write unsynchronized since it's a potential long operation
       tmp.write(out);
    }

    /**
     * This thread run while listening for incoming connections. It allows the
     * WIMM One to accept external Bluetooth connections. It runs until a
     * connection is accepted or when cancel() is called.
     */
    private class AcceptThread extends Thread {
        // Local server socket
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            BluetoothServerSocket tmp = null;
            // Create a listening server socket on our UUID
            try {
                tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(WIMMBTDemoManager.class.getSimpleName(), mUUID);
            } catch (IOException e) {
                Log.e (TAG, "Unable to create listening server socket: ", e);
            }
            mmServerSocket = tmp;
        }

        public void run() {
            if (DEBUG_LOG) Log.d(TAG, "Begin mAcceptThread on: " + this);
            setName("AcceptThread");
            BluetoothSocket socket = null;

            // Listen to server socket for connection ONLY when we
            // are not connected.
            while(mState != STATE_CONNECTED) {
                try {
                    // This is a blocking call.
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    Log.e(TAG, "Socket.accept() fails on AcceptThread: ", e);
                    break;
                }

                // A connection has been accepted
                if (socket != null) {
                    synchronized (WIMMBTDemoManager.this) {
                        switch (mState) {
                        case STATE_LISTEN:
                        case STATE_CONNECTING:
                            // The correct state. Start connected thread here
                            connected(socket, socket.getRemoteDevice());
                            break;
                        case STATE_NONE:
                        case STATE_CONNECTED:
                            // Not ready yet or already connected. Terminate new socket here.
                            try {
                                socket.close();
                            } catch (IOException e) {
                                Log.e(TAG, "Unable to close unwanted socket: ", e);
                            }
                            break;
                        }
                    }
                }
            }
            if (DEBUG_LOG) Log.d(TAG, "End mAcceptThread on: " + this);
        }

        public void cancel() {
            if (DEBUG_LOG) Log.d(TAG, "Cancelling mAcceptThread on: " + this);
            try {
                mmServerSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Unable to close() Listening socket: ", e);
            }
        }

    }

    /**
     * This thread runs while attempting to make an outgoing BT connection.
     * It runs straight through and will called connected() on a successful
     * BT connection.
     */
    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            mmDevice = mBluetoothAdapter.getRemoteDevice(device.getAddress());
            BluetoothSocket tmp = null;

            // Create BluetoothSocket for the device given using our UUID
            try {
                tmp = mmDevice.createRfcommSocketToServiceRecord(mUUID);
            } catch (IOException e) {
                Log.e(TAG, "Unable to create BT socket: ", e);
            }
            mmSocket = tmp;
        }

        public void run() {
            if (DEBUG_LOG) Log.d(TAG, "Begin mConnectThread on: " + this);
            setName("ConnectThread");

            // Always cancel discovery before attempting to connect
            mBluetoothAdapter.cancelDiscovery();

            // Attempt to make an connection over the BluetoothSocket
            try {
                // This will block and return only on a successful
                // connection or an exceptions.
                mmSocket.connect();
            } catch (IOException e) {
                Log.e(TAG, "Connection to " + mmDevice.getName() + " failed: ", e);
                setState(STATE_LISTEN);

                // Close socket on failure
                try {
                    mmSocket.close();
                } catch (IOException e2) {
                    Log.e(TAG, "Unable to close() socket for unsuccessful connection: ", e2);
                }

                if (DEBUG_LOG) Log.d(TAG, "End mConnectThread on: " + this);
                // exiting thread.
                return;
            }

            /****** Connection was successful ******/

            // Reset ConnectThread since we are done
            synchronized (WIMMBTDemoManager.this) {
                mConnectThread = null;
            }

            // Kick off the Connected Thread
            connected(mmSocket, mmDevice);

            if (DEBUG_LOG) Log.d(TAG, "End mConnectThread on: " + this);
        }

        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Unable to close() connecting socket: ", e);
            }
        }

    }

    /**
     * This threads managed the connected Bluetooth socket. It handles both
     * incoming and outgoing data stream over the socket.
     */
    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private boolean mRestartServiceOnConnectionLost = true;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get Input and Output stream for BluetoothSocket
            try {
                tmpIn = mmSocket.getInputStream();
                tmpOut = mmSocket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "Unable to get data stream from socket: ", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            if (DEBUG_LOG) Log.d(TAG, "Begin mConnectedThread on: " + this);
            setName("ConnectedThread");

            // Local variables to read InputStream, reading in 1kb chunk.
            byte[] buffer = new byte[1024];
            int bytes;

            // Keep listening to InputStream while connected
            while (true) {
                try {
                    // Read from InputStream
                    bytes = mmInStream.read(buffer);

                    // Send the bytes obtained to the UI Activity
                    mHandler.obtainMessage(WIMMBTDemoActivity.MESSAGE_READ, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e) {
                    Log.e(TAG, "Connection Lost on mConnectThread: ", e);
                    break;
                }
            }

            // Reset connection stages and restart listening mode if
            // connection lost is not user triggered.
            if (mRestartServiceOnConnectionLost) {
                ListenForConnection();
            }


            if (DEBUG_LOG) Log.d(TAG, "End mConnectedThread on: " + this);
        }

        /**
         * Write to the connected Outstream.
         * @param buffer  The byte[] array to be written
         */
        public void write(byte[] buffer) {
            try {
                mmOutStream.write(buffer);

                // update UI Activity of message sent
                mHandler.obtainMessage(WIMMBTDemoActivity.MESSAGE_WRITE, -1, -1, buffer).sendToTarget();
            } catch (IOException e) {
                Log.e(TAG, "Exception during write: ", e);
            }
        }

        public void cancel() {
            mRestartServiceOnConnectionLost = false;
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Unable to close() connected socket: ", e);
            }
        }

    }

}
