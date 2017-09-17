package pkg.ocr.app;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;

public class ImageSelectionActivity extends Activity{
	private final int SELECT_PHOTO = 1;
	private final int CROP_FROM_CAMERA = 2;
	static public Bitmap bitmap;
	Uri selectedImage;
	Button processBtn;
	static int a = 0;
	private int density  =  320;
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
			startActivity(new Intent (ImageSelectionActivity.this, MenuActivity.class));
            ImageSelectionActivity.this.finish();
            return;
		}
		switch (requestCode) { 
		case SELECT_PHOTO:
				selectedImage = imageReturnedIntent.getData();
				performCrop();		
				break;
	    case CROP_FROM_CAMERA:
	        Bundle extras = imageReturnedIntent.getExtras();
	        if (extras != null) 
	        {	
	        	bitmap = extras.getParcelable("data");
	        	bitmap.setDensity(density);
	        	startActivity(new Intent (ImageSelectionActivity.this, AdjustCharactersActivity.class));
	        	ImageSelectionActivity.this.finish();
	        	finish();
	        	break;
	        }
	        break;
	}
    }
    private void performCrop(){
    	try {
	    	Intent cropIntent = new Intent("com.android.camera.action.CROP"); 
	    	cropIntent.setDataAndType(selectedImage, "image/*");
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
   /* private void doCrop() {
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
    }*/
    public String getRealPathFromURI(Uri contentUri) 
    {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(contentUri, projection, null, null, null);
		int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(columnIndex);
	}
}
