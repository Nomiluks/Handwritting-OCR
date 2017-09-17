package com.fyp.ocr;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.widget.Toast;
public class croper extends Activity {
	private static final int CROP_FROM_CAMERA = 2;
    static Bitmap bitmap,bmp;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performCrop();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
    	if (resultCode != RESULT_OK) 
    	{
    		Toast.makeText(getApplicationContext(), "Camera Exit!", Toast.LENGTH_LONG).show();
    		return;
    	}
		switch (requestCode) 
		{ 
		/*    	
        	bitmap = extras.getParcelable("data");
        	bitmap.setDensity(density);
    		
    		finish();
    		break;
        }
        break;*/
			case CROP_FROM_CAMERA:   
				Bundle extras = data.getExtras();
		        if (extras != null) 
		        {	        	
		        	bitmap = extras.getParcelable("data");
		        	bitmap = bitmap.copy(Config.ARGB_8888, true);
		        	startActivity(new Intent (croper.this, TrackBarActivity.class));
		    		croper.this.finish();
		        }
		        break;
        }
    }
    private void performCrop(){
    	try {
	    	Intent cropIntent = new Intent("com.android.camera.action.CROP"); 
	    	cropIntent.setDataAndType(CaptureImageActivity.picUri, "image/*");
	    	cropIntent.putExtra("crop", "true");
	    	cropIntent.putExtra("aspectX", 0);
	    	cropIntent.putExtra("aspectY", 0);
	    	cropIntent.putExtra("return-data", true);
	        startActivityForResult(cropIntent, CROP_FROM_CAMERA);  
    	}
  
    	catch(ActivityNotFoundException anfe){
    		String errorMessage = "Whoops - your device doesn't support the crop action!";
    		Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
    		toast.show();
    	}
    }
}

