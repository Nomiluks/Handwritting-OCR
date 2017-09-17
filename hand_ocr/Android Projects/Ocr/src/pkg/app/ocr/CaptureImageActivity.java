package pkg.app.ocr;

import java.io.File;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

public class CaptureImageActivity extends Activity{
	private static final int CAMERA_REQUEST = 1; 
    static Uri picUri;
    static Bitmap bitmap,bmp;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Opencamera();
    }
    public void Opencamera()
    {
    	try {
        	Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());
	        picUri = getImageUri();
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
    	}
        catch(ActivityNotFoundException anfe){
    		//display an error message
    		String errorMessage = "Whoops - your device doesn't support capturing images!";
    		Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
    		toast.show();
    	}
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
    	if (resultCode != RESULT_OK) 
    	{
    		Toast.makeText(getApplicationContext(), "Camera Exit!", Toast.LENGTH_LONG).show();
    		return;
    	}
		switch (requestCode) 
		{ 
			case CAMERA_REQUEST:  
				startActivity(new Intent (CaptureImageActivity.this, croper.class));
	    		CaptureImageActivity.this.finish();
				break;
        }
    }
    private Uri getImageUri()
    {
    	File file = new File(Environment.getExternalStorageDirectory()+"/DCIM","processImage.jpg");
    	Uri imgUri = Uri.fromFile(file);
    	return imgUri;
    }
}