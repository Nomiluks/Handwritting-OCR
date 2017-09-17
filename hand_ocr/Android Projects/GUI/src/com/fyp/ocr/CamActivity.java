package com.fyp.ocr;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

public class CamActivity extends Activity{
	private static final int CAMERA_REQUEST = 1888; 
	private static final int CROP_FROM_CAMERA = 2;
    Uri picUri;
    String seting;
    static Bitmap bitmap;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         seting = OCRguiActivity.readFromFile();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());
	    startActivityForResult(intent, CAMERA_REQUEST);
    }
	/**
	 * Get the uri of the captured file
	 * @return A Uri which path is the path of an image file, stored on the dcim folder
	 */
	private Uri getImageUri() {
	    // Store image in dcim
	    File file = new File(Environment.getExternalStorageDirectory() + "/DCIM", "umar.jpg");
	    Uri imgUri = Uri.fromFile(file);

	    return imgUri;
	}
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
    	if (resultCode != RESULT_OK) 
    	{
    		startActivity(new Intent (CamActivity.this, MenuActivity.class));
    		CamActivity.this.finish();
    		return ;
    	}
		switch (requestCode) { 
		case CAMERA_REQUEST:  
            picUri = getImageUri();
         
            Toast.makeText(getApplicationContext(), "rotation angle = " + this.getWindowManager().getDefaultDisplay().getRotation(), Toast.LENGTH_LONG).show();
            
    		doCrop();
    		break;
		case CROP_FROM_CAMERA:   
    			 	Bundle extras = data.getExtras();
    		        if (extras != null) 
    		        {	        	
    		        	bitmap = extras.getParcelable("data");
    		        	if (seting.contains("1")){
    		              	
    		        		startActivity(new Intent (CamActivity.this, segmentResultActivty.class));
        		    		CamActivity.this.finish();    		              }
    		              else if (seting.contains("2")){
    		              	
    		            	  startActivity(new Intent (CamActivity.this, TrackBarActivity.class));
    	    		    		CamActivity.this.finish();    		              }
       		    		
    		        }
        }
    }
    
   /* public static void setCameraDisplayOrientation(Activity activity,int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        //Camera.
        if (info.facing == android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        
		//Toast.makeText(this, "filetxt " + result, Toast.LENGTH_LONG).show();
        camera.setDisplayOrientation(result);
		//Toast.makeText(getApplicationContext(), "filetxt " + result, Toast.LENGTH_LONG).show();

    }*/
    
    
    private void doCrop() {
    	final ArrayList<CropOption> cropOptions = new ArrayList<CropOption>();
		Toast.makeText(CamActivity.this, "filetxt ", Toast.LENGTH_LONG).show();

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
        	//Toast.makeText(this, "Gone.....", Toast.LENGTH_SHORT).show();
        	//Specify the image path, crop dimension and scale
        	intent.setData(picUri);
            intent.putExtra("aspectX", 0);
            intent.putExtra("aspectY", 0);
            intent.putExtra("scale", true);
            intent.putExtra("return-data", true);
      
        /*There is possibility when more than one image cropper app exist, 
          so we have to check for it first. 
          If there is only one app, open then app. */
       	if (size == 1) {
        		Intent i = new Intent(intent);
            	ResolveInfo res	= list.get(0);
            	
            	i.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
    	        //Toast.makeText(this, "go for saving", Toast.LENGTH_SHORT).show();
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
}