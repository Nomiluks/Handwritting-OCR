package com.fyp.ocr;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import org.neuroph.core.NeuralNetwork;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class OCRguiActivity extends Activity {
	public static NeuralNetwork nnet;
	ProgressBar myProgressBar;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashs);
        readFromFile();
        TextView logo1 = (TextView)findViewById(R.id.TOPTiltelText);
        Animation fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo1.startAnimation(fade1);
        
        TextView logo2 = (TextView)findViewById(R.id.TOPTiltelText);
        Animation fade2 = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
        logo2.startAnimation(fade2);
        loadData();
    }
    private void loadData() 
	{
		Thread t = new Thread(null, loadDataRunnable, "dataLoader", 512000);//.start();
		t.start();
	}
    private Runnable loadDataRunnable = new Runnable() 
    {
		public void run() {
			InputStream is = getResources().openRawResource(R.raw.finaltraining1);
			nnet = NeuralNetwork.load(is);
			startActivity(new Intent (OCRguiActivity.this, MenuActivity.class));
			OCRguiActivity.this.finish();
		}
	};
    static String readFromFile() {
		 
		//Get the text file 
		File file = new File(Environment.getExternalStorageDirectory(),"mysdfile.txt"); 
		 
		//Read text from file 
		StringBuilder text = new StringBuilder(); 
		 
		try { 
		    BufferedReader br = new BufferedReader(new FileReader(file)); 
		    String line; 
		    while ((line = br.readLine()) != null) { 
		        text.append(line); 
		        //text.append('\n'); 
		    } 
		    
		} 
		catch (IOException e) { 
		    //You'll need to add proper error handling here 
		}
		String str = text.toString();
	     return str;		 
 }
  public void getdesity() {
		Toast.makeText(OCRguiActivity.this, "Density: " + (getResources().getDisplayMetrics().density), Toast.LENGTH_LONG).show();
	  
  }

}
    

