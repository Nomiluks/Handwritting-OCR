package pkg.app.ocr;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.widget.ImageView;

public class showPicture extends Activity {
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView img = (ImageView)findViewById(R.id.imageView1);
        Bitmap bmp = croper.bitmap.copy(Config.ARGB_8888, true);
        img.setImageBitmap(bmp);
    }
}
