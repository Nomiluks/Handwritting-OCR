/**
 * 

package net.obviam.droidz;



import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;


 * @author impaler
 * This is the main surface that handles the ontouch events and draws
 * the image to the screen.

public class MainGamePanel extends SurfaceView implements
		SurfaceHolder.Callback {

	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	private MainThread thread;
	
	private Bitmap doorScene = null; 
	private Canvas gameScene = null; 

	public MainGamePanel(Context context) {
		super(context);
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);
		
		// create the game loop thread
		thread = new MainThread(getHolder(), this);
		
		// make the GamePanel focusable so it can handle events
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// at this point the surface is created and
		// we can safely start the game loop
		
		Toast.makeText(this.getContext(),"surfaceCreated", Toast.LENGTH_LONG).show(); 
//		Bitmap background = null;
//		gameScene = holder.lockCanvas();
//		background = BitmapFactory.decodeResource(getResources(), R.drawable.doors);
//	    float scale = (float)background.getHeight()/(float)getHeight();
//	    int newWidth = Math.round(background.getWidth()/scale);
//	    int newHeight = Math.round(background.getHeight()/scale);
//	    doorScene = Bitmap.createScaledBitmap(background, newWidth, newHeight, true);
//	    gameScene.drawBitmap(doorScene, 0, 0, null);
//	    holder.unlockCanvasAndPost(gameScene);
	    
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
		Log.d(TAG, "Thread was shut down cleanly");
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (event.getY() > getHeight() - 50) {
				thread.setRunning(false);
				((Activity)getContext()).finish();
			} else {
				Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
			}
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Toast.makeText(this.getContext(),"onDraw", Toast.LENGTH_LONG).show(); 
		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.baddie1), 10, 10, null);
	}

}
*/

package net.obviam.droidz;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



public class MainGamePanel extends SurfaceView implements
  SurfaceHolder.Callback {

 private static final String TAG = MainGamePanel.class.getSimpleName();

 private MainThread thread;
 private Droid droid;
 public boolean left = false;
 public boolean mid = false;
 public boolean right = false;
 public Long timeRight = null;
 public Long timeMid = null;
 public Long timeLeft = null;
 private String avgFps;
 
 public MainGamePanel(Context context) {
  super(context);
  // adding the callback (this) to the surface holder to intercept events
  getHolder().addCallback(this);

  // create droid and load bitmap
  droid = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.doors), 0, -50);

  // create the game loop thread
  thread = new MainThread(getHolder(), this);

  // make the GamePanel focusable so it can handle events
  setFocusable(true);
 }

 @Override
 public void surfaceChanged(SurfaceHolder holder, int format, int width,
   int height) {
 }

 @Override
 public void surfaceCreated(SurfaceHolder holder) {
  // at this point the surface is created and
  // we can safely start the game loop
  thread.setRunning(true);
  thread.start();
 }

 @Override
 public void surfaceDestroyed(SurfaceHolder holder) {
  Log.d(TAG, "Surface is being destroyed");
  // tell the thread to shut down and wait for it to finish
  // this is a clean shutdown
  boolean retry = true;
  while (retry) {
   try {
    thread.join();
    retry = false;
   } catch (InterruptedException e) {
    // try again shutting down the thread
   }
  }
  Log.d(TAG, "Thread was shut down cleanly");
 }

 @Override
 public boolean onTouchEvent(MotionEvent event) {
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
   // delegating event handling to the droid
   droid.handleActionDown((int)event.getX(), (int)event.getY());

   // check if in the upper part of the screen we exit
   if (event.getY() < getHeight() - 300) {
    thread.setRunning(false);
    ((Activity)getContext()).finish();
   } 
   else if (((event.getY() < getHeight() - 75) && (event.getY() > getHeight() - 280)) && ((event.getX() > (getWidth()- 420) && (event.getX() < (getWidth()-380))))) {
	   
	   this.left = true;
	   this.mid = false;
	   this.right = false;
	   this.timeLeft = System.currentTimeMillis();
	   Log.d(TAG, "Fire Left");
	   
	 }
   else if (((event.getY() < getHeight() - 75) && (event.getY() > getHeight() - 280)) && ((event.getX() > (getWidth()- 270) && (event.getX() < (getWidth()-230))))){
	   
	   this.left = false;
	   this.mid = true;
	   this.right = false;
	   this.timeMid = System.currentTimeMillis();
	   Log.d(TAG, "Fire Mid");
	   
   }
  else if(((event.getY() < getHeight() - 75) && (event.getY() > getHeight() - 280)) && ((event.getX() > (getWidth()- 120) && (event.getX() < (getWidth()-80))))){
	   
	   this.left = false;
	   this.mid = false;
	   this.right = true;
	   this.timeRight = System.currentTimeMillis();
	   Log.d(TAG, "Fire Right: " + this.timeRight);
  }
   else {
	   
	   this.left = false;
	   this.mid = false;
	   this.right = false;
   }
  }/* if (event.getAction() == MotionEvent.ACTION_MOVE) {
   // the gestures
   if (droid.isTouched()) {
    // the droid was picked up and is being dragged
    droid.setX((int)event.getX());
    droid.setY((int)event.getY());
   }
  } if (event.getAction() == MotionEvent.ACTION_UP) {
   // touch was released
   if (droid.isTouched()) {
    droid.setTouched(false);
   }
  }*/
  return true;
 }

 @Override
 protected void onDraw(Canvas canvas) {
  // fills the canvas with black
  //canvas.drawColor(Color.BLACK);
  
	 //Me Added later
	 canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.fullopendoors), 0, -50, null);
	 //canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.closeddoor), 160, 20, null);
	 //canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.closeddoor), 310, 20, null);
	 droid.draw(canvas);
	 Log.d(TAG, "OnDraw");
 }
 public void loadBitmap(int x, int y){
	 
	 Bitmap tempBitmap;
	 	 
	 tempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.closeddoors);
	 
	 droid.setBitmap1(tempBitmap, x , y);
	 //droid = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.closeddoors), x, y);
	 
 }
 
 public void loadBomber(){
	 
	 Bitmap tempBitmap;
	 int width;
	 
	 tempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.crazybomber1);
	 width = tempBitmap.getWidth();
	 droid.setBitmap1(tempBitmap, width - 180 , 30);
	 
	 
 }
 
 public void loadBomber(int x){
	 
	 Bitmap tempBitmap;
	 int width;
	 
	 tempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.crazybomber1);
	 width = tempBitmap.getWidth();
	 if (x == 1)
		 droid.setBitmap1(tempBitmap, width - 180 , 30);
	 else if (x == 2)
		 droid.setBitmap1(tempBitmap, width - 30 , 30);
	 else if (x == 3)
		 droid.setBitmap1(tempBitmap, width  + 120, 30);
	 
 }
 public void loadBitmap(int x, int y, int z){
	 
	 //droid = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.background), 125, 250);
	 Bitmap tempBitmap;
	 int width;
	 if (z == 1)
	 {

		 tempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.semiopendoor1);
		 width = tempBitmap.getWidth();
		 droid.setBitmap(tempBitmap, width + x, y);
		 Log.d(TAG, "semiopendoor1");
	 }
	 else if (z == 2)
	 {
		 
		 tempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.semiopendoor2);
		 width = tempBitmap.getWidth();
		 droid.setBitmap(tempBitmap, width + x, y);
	 }
	 else if (z == 3)
	 {
		 
		 tempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.semiopendoor3);
		 width = tempBitmap.getWidth();
		 droid.setBitmap(tempBitmap, width + x, y);
	 }	 
	 else if (z == 4)
	 {
		 
		 tempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.semiopendoor4);
		 width = tempBitmap.getWidth();
		 droid.setBitmap(tempBitmap, width + x, y);
	 }	 
	 else if (z == 5)
	 {

		 tempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.semiopendoor5);
		 width = tempBitmap.getWidth();
		 droid.setBitmap(tempBitmap, width + x, y);
	 }	 
	 else if (z == 6)
	 {
		 
		 tempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.semiopendoor6);
		 width = tempBitmap.getWidth();
		 droid.setBitmap(tempBitmap, width + x, y);
	 }	 
	 else if (z == 7)
	 {
		 
		 tempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.opendoor);
		 width = tempBitmap.getWidth();
		 droid.setBitmap(tempBitmap, width + x, y);
		 
	 }	 
	 else if (z == 8)
	 {
		 
		 tempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.closeddoor);
		 width = tempBitmap.getWidth();
		 droid.setBitmap(tempBitmap, width + x, y);
		 Log.d(TAG, "Closed Door");
		 
	 }	 	 
 }
 
 public void loadBitmapShot(int x, int y){
	 Bitmap tempBitmap;
	 int width;
	 
	 tempBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.crazybombershot);
	 width = tempBitmap.getWidth();
	 droid.setBitmap1(tempBitmap, width - x , y);
 }
 
 
 public void clearBitmap(){
	 
	 droid.clean();
	 
 }
 public void setLeft(boolean value){
	 left = value;
 }

 public void setRight(boolean value){
	 right = value;
 }
 
 public void setMid(boolean value){
	 mid = value;
 }
 
 public boolean isLeft(){
	 
	 if (left)
		 return true;
	 else
		 return false;
 }
 
 public boolean isRight(){
	 
	 if (right)
		 return true;
	 else
		 return false;
 }
 
 public boolean isMid(){
	 
	 if (mid)
		 return true;
	 else
		 return false;
 }
 
 private void displayFps(Canvas canvas, String fps) {
		if (canvas != null && fps != null) {
			Paint paint = new Paint();
			paint.setARGB(255, 255, 255, 255);
			canvas.drawText(fps, this.getWidth() - 50, 20, paint);
			//canvas.co
		}
	}
 
 public void render(Canvas canvas) {
		//Me added later
	 	canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.doors), 0, 0, null);
		droid.draw(canvas);
		// display fps
		displayFps(canvas, avgFps);
	}
 
 public void setAvgFps(String avgFps) {
		this.avgFps = avgFps;
	}
 
}
