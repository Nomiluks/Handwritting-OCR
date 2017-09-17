package games.project.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.TextView;

public class SplashActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    protected void onPause(){
    	super.onPause();
    	TextView logo = (TextView)findViewById(R.id.TOPTiltelText);
    	logo.clearAnimation();
    	TextView logo1 = (TextView)findViewById(R.id.Bottomtext);
    	logo1.clearAnimation();
    	
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        TextView logo1 = (TextView)findViewById(R.id.TOPTiltelText);
        Animation fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo1.startAnimation(fade1);
        
        TextView logo2 = (TextView)findViewById(R.id.TOPTiltelText);
        Animation fade2 = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
        logo2.startAnimation(fade2);
        
        fade2.setAnimationListener(new AnimationListener()
        {
        	public void onAnimationEnd(Animation animation)
        	{
        		startActivity(new Intent(SplashActivity.this,MainMenuActivity.class));
        		SplashActivity.this.finish();
        		
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
