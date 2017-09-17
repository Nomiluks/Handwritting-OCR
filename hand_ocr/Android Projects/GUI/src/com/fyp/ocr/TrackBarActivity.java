package com.fyp.ocr;
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
import android.widget.TextView;

public class TrackBarActivity extends Activity {
    static   int i = 0;
    static   int count = 0;
    ImageView img;
    static Bitmap bmpmapthresh,bmpnew;
    preprocessing pre = new preprocessing();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trackbar);
        SeekBar sk=(SeekBar)findViewById(R.id.seekBar1);
        final TextView tv=(TextView)findViewById(R.id.textView1);
        this.img = (ImageView) findViewById(R.id.imageView1);
        
        if(MenuActivity.imagecomefrom==1)
        	bmpmapthresh = croper.bitmap;
        else 
        	bmpmapthresh=SegmentActivity.bitmap;
        
        bmpmapthresh = bmpmapthresh.copy(Config.ARGB_8888, true);
        img.setImageBitmap(pre.preproccessingMain(bmpmapthresh));
        Button btn = (Button) findViewById(R.id.ok);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	if(i == 0)
            	{
            		bmpmapthresh = bmpmapthresh.copy(Config.ARGB_8888, true);
            		//bmpnew = getAdaptiveLocalBinarization(17,12,bmpmapthresh);
            		bmpnew = pre.preproccessingMain(bmpmapthresh) ;
            	}
            	i = 0;
            	//if(MenuActivity.imagecomefrom==1){
            	startActivity(new Intent (TrackBarActivity.this, segmentResultActivty.class));
            	TrackBarActivity.this.finish();
            	//}
            /*	else{
            		startActivity(new Intent (TrackBarActivity.this, segmentResultActivty.class));
                	TrackBarActivity.this.finish();
            	}*/
            	
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
                    	  tv.setText("Seek bar value is: "+ arg1);
                    	  bmpnew = bmpmapthresh.copy(Config.ARGB_8888, true);
                    	  
                    	 // bmpnew = pre.binrizationdynamic(bmpmapthresh,arg1) ;
                    	  //int val = 10 +arg1 ;
                    	  /*if(val%2==0){
                    		  val =val -1 ;
                    	  }*/
                    	  bmpnew = pre.umer(bmpnew ,arg1+8, arg1+2);
                     	  img.setImageBitmap(bmpnew);
                     	  i = 1;
                     }
              });
    }
}