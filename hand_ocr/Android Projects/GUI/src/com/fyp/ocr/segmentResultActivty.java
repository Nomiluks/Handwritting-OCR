package com.fyp.ocr;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class segmentResultActivty extends Activity implements OnInitListener{
	private TextView txtAnswer;
	private TextToSpeech tts;
	private ImageView img;
	private int MY_DATA_CHECK_CODE = 0;
	 String seting;
	ImageView targetImage;
public static Bitmap bmp;
public static int contre1;
public static int contre2;
static String set_result = null;
static String answer = "";
preprocessing pre = new preprocessing();
ConectedCompunent1 con=new ConectedCompunent1();
Mach match=new Mach();
Segment seg = new Segment();
static String textseg = "";
String out = "";
@Override
public void onCreate(Bundle savedInstanceState) 
{
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	ImageButton clickForMenu = (ImageButton)findViewById(R.id.menucam);
	clickForMenu.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	startActivity(new Intent (segmentResultActivty.this, MenuActivity.class));
        	segmentResultActivty.this.finish();
        }
    });
	txtAnswer = (TextView) findViewById(R.id.txtAnswer);
	bmp = TrackBarActivity.bmpnew.copy(Bitmap.Config.ARGB_8888, true);
	img = (ImageView)findViewById(R.id.imageView1);
//	seting = OCRguiActivity.readFromFile();
  //  if (seting.contains("1")){
      	
		bmp = SegmentActivity.bitmap;
		bmp = pre.preproccessingMain(bmp);
	//	}
     // else if (seting.contains("2")){
      	
    	  bmp = TrackBarActivity.bmpnew.copy(Config.ARGB_8888, true );    		              
    	//  }
    bmp = pre.rescale(bmp);
    img.setImageBitmap(bmp);
	Bitmap charcter[][][] = seg.segmentationMain(bmp);
	out += match.testNeuralNetwork(OCRguiActivity.nnet, charcter);
	textseg= "Written data is: " +out;
    txtAnswer.setText("Written data is: " +out);
	ImageButton clickForspeechcam = (ImageButton)findViewById(R.id.speakselect);
    clickForspeechcam.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	Intent checkIntent = new Intent();
        	checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        	startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
        }
    });
}
public String getRealPathFromURI(Uri contentUri) 
{
	String[] projection = { MediaStore.Images.Media.DATA };
	Cursor cursor = managedQuery(contentUri, projection, null, null, null);
	int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	cursor.moveToFirst();
	return cursor.getString(columnIndex);
}
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	if (requestCode == MY_DATA_CHECK_CODE) {
		if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
			// success, create the TTS instance
			tts = new TextToSpeech(this, this);
		} 
		else {
			// missing data, install it
			Intent installIntent = new Intent();
			installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
			startActivity(installIntent);
		}
	}

}

public void onInit(int status) {		
	if (status == TextToSpeech.SUCCESS) {
		if (textseg!=null && textseg.length()>0) 
		{
			Toast.makeText(segmentResultActivty.this, "Saying: " + textseg, Toast.LENGTH_LONG).show();
			tts.speak(textseg, TextToSpeech.QUEUE_ADD, null);
		}
	}
	else if (status == TextToSpeech.ERROR) {
		Toast.makeText(segmentResultActivty.this, 
				"Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
	}
}
}

