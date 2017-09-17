package pkg.app.ocr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends Activity {
    static int flow = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manuslection);
        ImageButton capture = (ImageButton)findViewById(R.id.imageButton1);
        ImageButton gallery = (ImageButton)findViewById(R.id.imageButton2);
        ImageButton help    = (ImageButton)findViewById(R.id.imageButton3);
        ImageButton exit    = (ImageButton)findViewById(R.id.imageButton4);

        capture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	flow = 0;
                startActivity(new Intent (MenuActivity.this, CaptureImageActivity.class));
                MenuActivity.this.finish();
                finish();
            }
        }); 
        gallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	flow = 1;
            	startActivity(new Intent (MenuActivity.this, ImageSelectionActivity.class));
                MenuActivity.this.finish();
                finish();
            }
        }); 
        help.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent (MenuActivity.this, HelpActivity.class));
                MenuActivity.this.finish();
                finish();
            }
        }); 
        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	finish();
            	MenuActivity.this.finish();
            }
        }); 
    }
}