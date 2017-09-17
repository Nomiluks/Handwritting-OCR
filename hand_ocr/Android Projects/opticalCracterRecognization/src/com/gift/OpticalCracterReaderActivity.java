package com.gift;


import bismillah.app.android.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
public class OpticalCracterReaderActivity extends Activity {
	Segment seg = new Segment();
	ConectedCompunent con = new ConectedCompunent();
	 preprocessing pre = new preprocessing();
	Mach maching  = new Mach();
	Transform rot = new Transform();
	public static int inc=0;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView img  = (ImageView)findViewById(R.id.imageView1);
        ImageView img2 = (ImageView)findViewById(R.id.imageView2);
        ImageView img3 = (ImageView)findViewById(R.id.imageView3);
        ImageView img4 = (ImageView)findViewById(R.id.imageView4);
        EditText text  = (EditText)findViewById(R.id.editText1);
        Bitmap bmp1;
        bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.fsixt );
        Bitmap bmp;
        binrization bin =  new binrization() ;
        Bitmap sha []  = con . conectedCompunentMain3( bmp );
		android.util.Log.i("i love you ", " "+Segment.counterCantainar[0][0][0]+" "+Segment.counterCantainar[0][1][0]);

	   }
}