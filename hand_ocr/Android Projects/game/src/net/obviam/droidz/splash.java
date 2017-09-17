package net.obviam.droidz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends Activity {
	protected void onPause(){
    	super.onPause();
    	ImageView logo = (ImageView)findViewById(R.id.TOPTiltelText);
    	logo.clearAnimation();
    	//TextView logo1 = (TextView)findViewById(R.id.Bottomtext);
    	//logo1.clearAnimation();
    	
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        //ImageView logo1 = (ImageView)findViewById(R.id.TOPTiltelText);
        //Animation fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        //logo1.startAnimation(fade1);
        
        ImageView logo2 = (ImageView)findViewById(R.id.TOPTiltelText);
        Animation fade2 = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
        logo2.startAnimation(fade2);
        
        fade2.setAnimationListener(new AnimationListener()
        {
        	public void onAnimationEnd(Animation animation)
        	{
        		startActivity(new Intent(splash.this,startgame.class));
        		splash.this.finish();
        		
        	}

			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}

			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
        });
        
        
    }
}