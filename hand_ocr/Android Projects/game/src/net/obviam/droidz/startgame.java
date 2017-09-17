package net.obviam.droidz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class startgame extends Activity{
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.gamestart);
	        ImageButton button = (ImageButton) findViewById(R.id.gamestartbutton);
	        button.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		startActivity(new Intent(startgame.this,GameActivity.class));
	        		startgame.this.finish();
	        	}
	        });
	 }
}
