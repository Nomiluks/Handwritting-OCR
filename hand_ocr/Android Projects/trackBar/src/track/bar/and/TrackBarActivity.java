package track.bar.and;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class TrackBarActivity extends Activity {
    static   int i = 0;
    static   int count = 0;
    ImageView img;
    Bitmap bmp;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SeekBar sk=(SeekBar)findViewById(R.id.seekBar1);
        final TextView tv=(TextView)findViewById(R.id.textView1);
        img = (ImageView) findViewById(R.id.imageView1);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.zul_cap);
        sk.setOnSeekBarChangeListener(new OnSeekBarChangeListener() 
        {
public void onStopTrackingTouch(SeekBar arg0) {
                           // TODO Auto-generated method stub
                          
                     }
                    
                     public void onStartTrackingTouch(SeekBar arg0) {
                           // TODO Auto-generated method stub
                          
                     }
                    
                     public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                           // TODO Auto-generated method stub
                           tv.setText("Seek bar value is"+ arg1);
                    	  tv.setText("Seek bar value is"+ arg1);
                    	  Bitmap nbitmp = bmp.copy(Config.ARGB_8888, true);
                     	  img.setImageBitmap(getAdaptiveLocalBinarization(17,arg1,nbitmp));
                          
                     }
              });
    }
    public Bitmap getAdaptiveLocalBinarization(int blocksize, int constant, Bitmap bmp)
    {
        Mat imgToProcess2 = Utils.bitmapToMat(bmp);
	    Imgproc.GaussianBlur(imgToProcess2, imgToProcess2, new Size(3,3), 1,1,Imgproc.BORDER_DEFAULT);
	    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_BGR2GRAY);
	    Imgproc.adaptiveThreshold(imgToProcess2, imgToProcess2,255,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY,blocksize,constant);
	    Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_GRAY2BGRA, 4);
	    Bitmap bmpOut2 = Bitmap.createBitmap(imgToProcess2.cols(), imgToProcess2.rows(), Bitmap.Config.ARGB_8888);
	    Utils.matToBitmap(imgToProcess2, bmpOut2);
		return bmpOut2;
    }
}