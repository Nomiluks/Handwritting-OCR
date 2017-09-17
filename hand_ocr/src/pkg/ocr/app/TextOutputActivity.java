package pkg.ocr.app;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TextOutputActivity extends Activity implements OnInitListener{
	
	private int MY_DATA_CHECK_CODE = 0;
	private TextToSpeech tts;
	private TextView txtAnswer;
	private ImageView img;
	static String textseg = "";
	//private ImageBasicOperations imgref = new ImageBasicOperations();
	private TextMaker answer           = new TextMaker();
	private Bitmap bmp;
	private String text = "";
	private int cCount = 0 ;
	int density  =  320;
	SegmentationProcess seg = new SegmentationProcess();
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txtAnswer = (TextView) findViewById(R.id.txtAnswer);
		img = (ImageView)findViewById(R.id.imageView1);
		ImageButton clickForMenu = (ImageButton)findViewById(R.id.for_menu_select);
		bmp                      = AdjustCharactersActivity.bmpnewseg;
		img.setImageBitmap(bmp);
		//int a = bmp.getDensity();
		Bitmap characters[][][]  = seg.segmentationMain(bmp);
		for(int l=0;l<SegmentationProcess.counterCantainar[0][0][0];l++) 
		{
        	for(int w=0;w<SegmentationProcess.counterCantainar[0][l+1][0];w++)
        	{
        		cCount = SegmentationProcess.counterCantainar[0][l+1][w+1];
        		for(int c=1;c<cCount;c++)
        		{
        			text += answer.calculateTextforLine(characters[l][w][c]);
        		}
        		text += " ";
        	}
        	text += "\n";
	    }
		//Toast.makeText(getApplicationContext(), "DENSITY:"+bmp.getHeight()+" "+bmp.getWidth(),Toast.LENGTH_LONG).show();
		//Toast.makeText(getBaseContext(), "MODE:"+ModeSelectionActivity.Join, Toast.LENGTH_SHORT).show();
		/*if(ModeSelectionActivity.Join == 1)
		{
			img.setImageBitmap(SegmentationProcess.Segmentedword);
		}*/
		txtAnswer.setText("Written data is :\n " +text);
        //Toast.makeText(getApplicationContext(), "DENSITY:"+a, Toast.LENGTH_LONG).show();
		clickForMenu.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	startActivity(new Intent (TextOutputActivity.this, MenuActivity.class));
	        	TextOutputActivity.this.finish();
	        }
	    });

		ImageButton clickForspeechcam = (ImageButton)findViewById(R.id.speakfromgallery);
	    clickForspeechcam.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) 
	        {
	            Intent checkIntent = new Intent();
	    		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
	    		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
	        }
	    });
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) 
		{
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
	@Override
	public void onInit(int status) {		
		if (status == TextToSpeech.SUCCESS) 
		{
			if (text!=null && text.length()>0) 
			{
				Toast.makeText(TextOutputActivity.this, "Saying: " + text, Toast.LENGTH_LONG).show();
				tts.speak(text, TextToSpeech.QUEUE_ADD, null);
			}
		}
		else if (status == TextToSpeech.ERROR) {
			Toast.makeText(TextOutputActivity.this, "Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
		}
	}
}

