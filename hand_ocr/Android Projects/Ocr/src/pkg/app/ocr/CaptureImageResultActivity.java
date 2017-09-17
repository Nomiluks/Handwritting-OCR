package pkg.app.ocr;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CaptureImageResultActivity extends Activity  implements OnInitListener{
	private int MY_DATA_CHECK_CODE = 0;
	private TextToSpeech tts;
	static  TextView set;
	private Bitmap bmp;
	private TextMaker answer = new TextMaker();
	private String text = "";
	private int cCount = 0 ;
	SegmentationProcess seg = new SegmentationProcess();
    private int density  =  320;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showresult);
        set = (TextView) findViewById(R.id.setresult);
        ImageView img1 = (ImageView) findViewById(R.id.imageView1);
        ImageButton menu = (ImageButton)findViewById(R.id.for_menu);
        text = "";
        ImageButton clickForspeechcam = (ImageButton)findViewById(R.id.speaking);
        clickForspeechcam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Intent checkIntent = new Intent();
            	checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
            }
        }); 
        menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	startActivity(new Intent (CaptureImageResultActivity.this, MenuActivity.class));
            	CaptureImageResultActivity.this.finish();
            }
        }); 

        bmp = AdjustCharactersActivity.bmpmapthreshseg.copy(Config.ARGB_8888, true);
        bmp.setDensity(density);
        img1.setImageBitmap(bmp);
        Bitmap characters[][][]  = seg.segmentationMain(bmp);
		  for(int l=0;l<SegmentationProcess.counterCantainar[0][0][0];l++) 
	        {
	        	for(int w=0;w<SegmentationProcess.counterCantainar[0][l+1][0];w++)
	        	{
	        		cCount = SegmentationProcess.counterCantainar[0][l+1][w+1];
	        		for(int c=1;c<cCount;c++)
	        		{
	        			text += answer.calculateTextforLine(characters[l][w][c]);
	        			//break;
	        		}
	        		text += " ";//break;
	        	}
	        	text += "\n";//break;
	        }
		Toast.makeText(getBaseContext(), "MODE:"+ModeSelectionActivity.Join, Toast.LENGTH_SHORT).show();
		//img1.setImageBitmap(SegmentationProcess.Segmentedword);
		if(ModeSelectionActivity.Join == 1)
		{
			img1.setImageBitmap(SegmentationProcess.Segmentedword);
		}
		set.setText("Written data is: \n" +text);
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

	@Override
	public void onInit(int status) {		
		if (status == TextToSpeech.SUCCESS) {
			
			if (text!=null && text.length()>0) {
				Toast.makeText(CaptureImageResultActivity.this, "Saying: " + text, Toast.LENGTH_LONG).show();
				tts.speak(text, TextToSpeech.QUEUE_ADD, null);
			}
		}
		else if (status == TextToSpeech.ERROR) {
			Toast.makeText(CaptureImageResultActivity.this, 
					"Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
		}
	}
}