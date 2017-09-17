package com.fyp.ocr;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends Activity {
	static int imagecomefrom;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_menu);
        ImageButton cam = (ImageButton)findViewById(R.id.cam);
        ImageButton galri = (ImageButton)findViewById(R.id.galeri);
        ImageButton set = (ImageButton)findViewById(R.id.sett);
        ImageButton help = (ImageButton)findViewById(R.id.help);

        cam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	imagecomefrom=1;
                startActivity(new Intent (MenuActivity.this, CaptureImageActivity.class));
                MenuActivity.this.finish();
            	
            }
        }); 
        galri.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	imagecomefrom=2;
            	startActivity(new Intent (MenuActivity.this, SelectActivity.class));
                MenuActivity.this.finish();
               
            }
        }); 
        set.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent (MenuActivity.this, SettingActivity.class));
                MenuActivity.this.finish();
            }
        }); 
        help.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            	startActivity(new Intent (MenuActivity.this, HelpActivity.class));
                MenuActivity.this.finish();
            }
        }); 

    }
}