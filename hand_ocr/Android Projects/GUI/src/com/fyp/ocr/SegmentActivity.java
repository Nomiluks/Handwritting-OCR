package com.fyp.ocr;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SegmentActivity extends Activity{// implements OnTouchListener{
	private final int SELECT_PHOTO = 1;
	private final int CROP_FROM_CAMERA = 5;
	static public Bitmap bitmap;
	Uri selectedImage;
	Button processBtn;
	ImageView targetImage;
	static int set  = 0;
	
	public static int contre1;
	public static int contre2;
	static String set_result = null;
	static String answer = "";
	double[] networkOutput = {};
	double[] input   = {};
	static String textseg = "";
	static int a = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
		Intent imageIntent = new Intent(Intent.ACTION_PICK);
		imageIntent.setType("image/*");
		startActivityForResult(imageIntent, SELECT_PHOTO);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
		super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
		if (resultCode != RESULT_OK) 
		{
			startActivity(new Intent (SegmentActivity.this, MenuActivity.class));
            SegmentActivity.this.finish();
            return;
		}
		switch (requestCode) { 
		case SELECT_PHOTO:
				selectedImage = imageReturnedIntent.getData();
				doCrop();		
				break;
	    case CROP_FROM_CAMERA:
	    	//img = (ImageView)findViewById(R.id.imageView1);
	        Bundle extras = imageReturnedIntent.getExtras();
	        if (extras != null) 
	        {	
	        	bitmap = extras.getParcelable("data");
	        	startActivity(new Intent (SegmentActivity.this, TrackBarActivity.class));
	        	SegmentActivity.this.finish();
	        }
	break;
	}
}
    private void doCrop() {
    	final ArrayList<CropOption> cropOptions = new ArrayList<CropOption>();
    	
    	Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");
        
        //Check if there is image cropper app installed.
        
        List<ResolveInfo> list = getPackageManager().queryIntentActivities( intent, 0 );
        
        int size = list.size();
       
        // If there is no image cropper app, display warning message
        
        if (size == 0) {	        
        	Toast.makeText(this, "Can not find image crop app", Toast.LENGTH_SHORT).show();
        	
            return;
        } else {
        	//Specify the image path, crop dimension and scale
        	intent.setData(selectedImage);
            intent.putExtra("aspectX", 0);
            intent.putExtra("aspectY", 0);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);

       	if (size == 1) {
        		Intent i = new Intent(intent);
            	ResolveInfo res	= list.get(0);
            	
            	i.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            	startActivityForResult(i, CROP_FROM_CAMERA);
        	} 
       	else {
    	        for (ResolveInfo res : list) {
    	        	final CropOption co = new CropOption();
    	        	
    	        	co.title 	= getPackageManager().getApplicationLabel(res.activityInfo.applicationInfo);
    	        	co.icon		= getPackageManager().getApplicationIcon(res.activityInfo.applicationInfo);
    	        	co.appIntent= new Intent(intent);
    	        	
    	        	co.appIntent.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
    	        	
    	            cropOptions.add(co);
    	        }
            
    	        CropOptionAdapter adapter = new CropOptionAdapter(getApplicationContext(), cropOptions);
    	        
    	        AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	        builder.setTitle("Choose Crop App");
    	        builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
    	            public void onClick( DialogInterface dialog, int item ) {
    	                startActivityForResult( cropOptions.get(item).appIntent, CROP_FROM_CAMERA);
    	            }
    	        });
        	}
        }
    }
    public String getRealPathFromURI(Uri contentUri) 
    {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(contentUri, projection, null, null, null);
		int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(columnIndex);
}
	 public String getOutput()
	 {
		 String output = null;
		 try{
			   File f = new File(Environment.getExternalStorageDirectory()+"/XOR.txt");
			   FileInputStream fileIS = new FileInputStream(f);
			   fileIS = new FileInputStream(f);
			   BufferedReader buf = new BufferedReader(new InputStreamReader(fileIS));
			   String readString = new String(); 
			   //just reading each line and pass it on the debugger
			   while((readString = buf.readLine())!= null)
			   {
			      Log.d("line: ", readString);
			      output = " "+readString;
			   }
			} catch (FileNotFoundException e) {
			   e.printStackTrace();
			} catch (IOException e){
			   e.printStackTrace();
			}
		 return output;
	 }

	
	}