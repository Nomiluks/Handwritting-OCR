package pkg.ocr.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ModeSelectionActivity extends Activity {
	ImageButton GoForDiscrete;
	ImageButton GoForJoining;
	static  int Join = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appmode);
        GoForDiscrete = (ImageButton) findViewById(R.id.discrete);
        GoForJoining  = (ImageButton) findViewById(R.id.joining);
        
       GoForDiscrete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent (ModeSelectionActivity.this, MenuActivity.class));
                ModeSelectionActivity.this.finish();
                Join = 0;
                finish();
            }
        }); 
        
        GoForJoining.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	startActivity(new Intent (ModeSelectionActivity.this, MenuActivity.class));
                ModeSelectionActivity.this.finish();
            	Join = 1;
                finish();
            }
        }); 
    }
}