package pkg.ocr.app;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class AdjustCharactersActivity  extends Activity {
    private int i = 0;
    static   int count = 0;
    ImageView img;
    static Bitmap bmpmapthreshseg,bmpnewseg;
    private int density  =  320;
    int window   = 17;
    int constant = 14;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trackbar);
        SeekBar sk=(SeekBar)findViewById(R.id.seekBar1);
        this.img = (ImageView) findViewById(R.id.imageView1);
    	if(MenuActivity.flow == 1)
    	{
            bmpmapthreshseg = ImageSelectionActivity.bitmap;
            bmpmapthreshseg = bmpmapthreshseg.copy(Config.ARGB_8888, true);
            bmpmapthreshseg.setDensity(density);
    	}
    	else if(MenuActivity.flow == 0)
    	{
            bmpmapthreshseg = croper.bitmap;
            bmpmapthreshseg = bmpmapthreshseg.copy(Config.ARGB_8888, true);
            bmpmapthreshseg.setDensity(density);
    	}

        img.setImageBitmap(getAdaptiveLocalBinarization(window,constant,bmpmapthreshseg));
        Button btn = (Button) findViewById(R.id.ok);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	if(i == 0)
            	{
            		bmpmapthreshseg = bmpmapthreshseg.copy(Config.ARGB_8888, true);
            		bmpnewseg = getAdaptiveLocalBinarization(window,constant,bmpmapthreshseg);
            	}
            	if(CheckImage(bmpnewseg))
            	{
            		bmpnewseg.setDensity(density);
            		startActivity(new Intent (AdjustCharactersActivity.this, TextOutputActivity.class));
            		AdjustCharactersActivity.this.finish(); 	
            	}
            }
        }); 
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
                    	  //tv.setText("Seek bar value is: "+ arg1);
                    	 if(arg1 > 2)
                    	 {
                    		 img.setImageBitmap(getAdaptiveLocalBinarization(window,arg1,bmpmapthreshseg));
                    		 bmpnewseg = getAdaptiveLocalBinarization(window,arg1,bmpmapthreshseg);
                    		 i = 1;
                    	 }
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
    public boolean CheckImage(Bitmap bmp)
	{
		int picw     = bmp.getWidth();
		int pich     = bmp.getHeight();
	    int[] pix = new int[picw * pich];
	    bmp.getPixels(pix, 0, picw, 0, 0, picw, pich); 
	    int R=0, G=0, B=0,index=0;
	    boolean check = false;
	    for (int y = 0; y < pich; y++)
	    {
	    	for (int x = 0; x < picw; x++)
	        {
	    		 index = y * picw + x;
	    		
	    	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
	    		 G = (pix[index] >> 8) & 0xff;
	    		 B =  pix[index] & 0xff;
	    		 
	    		 if(R == 0 && G == 0 && B == 0)
	    		 {
	    			 check = true;
	    			 break;
	    		 }
	        }
	    }
	    if(check == true)
	    {
	    	return check;
	    }
	    else
	    {
	    	check = false;
	    	return check;
	    }
	}
}