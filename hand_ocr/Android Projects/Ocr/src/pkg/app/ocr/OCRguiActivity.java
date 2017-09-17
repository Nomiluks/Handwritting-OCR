
package pkg.app.ocr;


import java.io.InputStream;

import org.neuroph.core.NeuralNetwork;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ProgressBar;


public class OCRguiActivity extends Activity {
	private  final int LOADING_DATA_DIALOG = 1;
	public   static  NeuralNetwork nnet;
	ProgressBar myProgressBar;
	int myProgress = 0;
	MediaPlayer song;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        loadData();
    }
    private void loadData() 
	{
		showDialog(LOADING_DATA_DIALOG);
		Thread t = new Thread(null, loadDataRunnable, "dataLoader", 9900000);
		t.start();
	}
    private Runnable loadDataRunnable = new Runnable() 
    {
		public void run() {
			InputStream is = getResources().openRawResource(R.raw.fif_six);
			nnet = NeuralNetwork.load(is);
			dismissDialog(LOADING_DATA_DIALOG);
			//startActivity(new Intent (OCRguiActivity.this, ModeSelectionActivity.class));
			startActivity(new Intent (OCRguiActivity.this, MenuActivity.class));
			OCRguiActivity.this.finish();
		}
	};
	protected Dialog onCreateDialog(int id) {
		ProgressDialog dialog;
		if (id == LOADING_DATA_DIALOG) {
			dialog = new ProgressDialog(this);
			dialog.setTitle("HandWritten OCR");
			dialog.setMessage("Loading data...");
			dialog.setCancelable(false);
			return dialog;
		} 		
		return null;
	}
}
    

