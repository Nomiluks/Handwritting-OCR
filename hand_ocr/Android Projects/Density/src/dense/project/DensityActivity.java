package dense.project;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class DensityActivity extends Activity {
	static Bitmap bmp;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView img = (ImageView) findViewById(R.id.imageView1);
        int density  =  320;
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        bmp.setDensity(density);
        img.setImageBitmap(bmp);
        int a = bmp.getDensity();
        Toast.makeText(getApplicationContext(), "DENSITY:"+a, Toast.LENGTH_LONG).show();  
    }
}