/**
 * 
 */
package net.obviam.droidz;

import java.text.DecimalFormat;
import java.util.Random;

import android.util.Log;
import android.view.SurfaceHolder;
import android.graphics.Canvas;


/**
 * @author impaler
 *
 * The Main thread which contains the game loop. The thread must have access to 
 * the surface view and holder to trigger events every game tick.
 */
public class MainThread extends Thread {
	
	private static final String TAG = MainThread.class.getSimpleName();

	// desired fps
	private final static int 	MAX_FPS = 20;
	// maximum number of frames to be skipped
	private final static int	MAX_FRAME_SKIPS = 5;
	// the frame period
	private final static int	FRAME_PERIOD = 1000 / MAX_FPS;

	// Stuff for stats */
    private DecimalFormat df = new DecimalFormat("0.##");  // 2 dp
	// we'll be reading the stats every second
	private final static int 	STAT_INTERVAL = 1000; //ms
	// the average will be calculated by storing
	// the last n FPSs
	private final static int	FPS_HISTORY_NR = 10;
	// last time the status was stored
	private long lastStatusStore = 0;
	// the status time counter
	private long statusIntervalTimer	= 0l;
	// number of frames skipped since the game started
	private long totalFramesSkipped			= 0l;
	// number of frames skipped in a store cycle (1 sec)
	private long framesSkippedPerStatCycle 	= 0l;

	// number of rendered frames in an interval
	private int frameCountPerStatCycle = 0;
	private long totalFrameCount = 0l;
	// the last FPS values
	private double 	fpsStore[];
	// the number of times the stat has been read
	private long 	statsCount = 0;
	// the average FPS since the game started
	private double 	averageFps = 0.0;

	// Surface holder that can access the physical surface
	private SurfaceHolder surfaceHolder;
	// The actual view that handles inputs
	// and draws to the surface
	private MainGamePanel gamePanel;

	// flag to hold game state 
	private boolean running;
	private boolean Left;
	private boolean Mid;
	private boolean Right;
	private boolean left_strike = false;
	private boolean Shot = false;
	private long leftBaddie;
	private long rightBaddie;
	
	private int count = 0;
	private int countMid = 0;
	private int countRight = 0;
	
	private Random r = new Random();

	private boolean mid_strike = false;

	private boolean right_strike = false;
	
	
	
	public void setRunning(boolean running) {
		this.running = running;
	}

	public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.gamePanel = gamePanel;
	}

	@Override
	public void run() {
		Canvas canvas;
		Long elapsed;
		initTimingElements();

		long beginTime;		// the time when the cycle begun
		long timeDiff;		// the time it took for the cycle to execute
		int sleepTime;		// ms to sleep (<0 if we're behind)
		int framesSkipped;	// number of frames being skipped 

		sleepTime = 0;

		Log.d(TAG, "Starting game loop");
		  while (running) {
		  canvas = null;
		  
		   // try locking the canvas for exclusive pixel editing on the surface
		  try {
			   
			
			randomize();  
			canvas = this.surfaceHolder.lockCanvas();
		    synchronized (surfaceHolder) {
		     // update game state
			beginTime = System.currentTimeMillis();
			framesSkipped = 0;	// resetting the frames skipped
			// update game state
			//this.gamePanel.update();
			// render state to the screen

			if (this.Shot){
				
				//this.gamePanel.clearBitmap();
				this.Shot = false;
				Log.d(TAG, "Shot");
			}	
			if (this.Left)
				leftDoorLogic();
			if (this.Mid)
				midDoorLogic();
			if (this.Right)
				rightDoorLogic();

			
			if (this.Left && this.gamePanel.isLeft()){
				elapsed = this.leftBaddie - this.gamePanel.timeLeft;
				
				Log.d(TAG, "Elapsed: "+ elapsed);
				if (/*elapsed > -5000 && elapsed < 0 &&*/ this.left_strike == true)
				{
					this.gamePanel.loadBitmapShot(80, 30);
					Log.d(TAG, "Left baddie shot: "+ elapsed);
					
					this.Shot = true;
					this.Left = false;
					this.gamePanel.setLeft(false);
					this.left_strike = false;
				}	

				
				
			}
			
			if (this.Mid && this.gamePanel.isMid()){
					
				if (this.mid_strike == true)
				{
					this.gamePanel.loadBitmapShot(-70 , 30);
										
					this.Shot = true;
					this.Mid = false;
					this.gamePanel.setMid(false);
					this.mid_strike = false;
				}	

			}
			
			if (this.Right && this.gamePanel.isRight()){
				
				if (this.right_strike == true)
				{
					this.gamePanel.loadBitmapShot(-220, 30);
										
					this.Shot = true;
					this.Right = false;
					this.gamePanel.setRight(false);
					this.right_strike = false;
				}	

			}
			//this.gamePanel.loadBitmap(130, 250);
/*			if (ran == 1){
				this.gamePanel.loadBitmap(130, 250);
				this.leftBaddie = System.currentTimeMillis();
				this.Left = true;
				this.Mid = false;
				this.Right = false;
				Log.d(TAG, "Render Left baddie");
			}
			else if (ran == 2){
				this.gamePanel.loadBitmap(285, 250);
				this.midBaddie = System.currentTimeMillis();
				this.Left = false;
				this.Mid = true;
				this.Right = false;
				Log.d(TAG, "Render Mid baddie");
			}
			else if (ran == 3){
				this.gamePanel.loadBitmap(430, 250);
				this.rightBaddie = System.currentTimeMillis();
				this.Left = false;
				this.Mid = false;
				this.Right = true;
				Log.d(TAG, "Render Right baddie at: " + this.rightBaddie);
			}
			if (this.Right)
				Log.d(TAG, "this.Right: IsTure");	
			
			if (this.Left && this.gamePanel.isLeft()){
				elapsed = this.leftBaddie - this.gamePanel.timeLeft;
				
				if (elapsed > -1100 && elapsed < 0)
				{
					this.gamePanel.loadBitmapShot(130, 250);
					Log.d(TAG, "Left baddie shot: "+ elapsed);
				}	
				this.Shot = true;
				this.Left = false;
				this.Mid = false;
				this.Right = false;
				this.gamePanel.setLeft(false);
				
				
			}
			if (this.Mid && this.gamePanel.isMid()){
				elapsed = this.midBaddie - this.gamePanel.timeMid;
				if (elapsed > -1100 && elapsed < 0)
				{
					this.gamePanel.loadBitmapShot(285, 250);
					Log.d(TAG, "Mid baddie shot: "+ elapsed);
				}
				this.Shot = true;
				this.Left = false;
				this.Mid = false;
				this.Right = false;
				this.gamePanel.setMid(false);
				
			}
			if (this.Right && this.gamePanel.isRight()){
				elapsed = this.rightBaddie - this.gamePanel.timeRight;
				Log.d(TAG, "Elapsed: "+ (this.rightBaddie - this.gamePanel.timeRight));
				if (elapsed > -1100 && elapsed < 0)
				{
					this.gamePanel.loadBitmapShot(430, 250);
					Log.d(TAG, "Right baddie shot: "+ elapsed);
				}
				this.Shot = true;  
				this.Left = false;
				this.Mid = false;
				this.Right = false;
				this.gamePanel.setRight(false);
				
			}*/

			
			// draws the canvas on the panel
				
			//Commented by ZA 
			//this.gamePanel.render(canvas);
			
			// calculate how long did the cycle take
			timeDiff = System.currentTimeMillis() - beginTime;
			// calculate sleep time
			sleepTime = (int)(FRAME_PERIOD - timeDiff);

			if (sleepTime > 0) {
				// if sleepTime > 0 we're OK
				try {
					// send the thread to sleep for a short period
					// very useful for battery saving
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {}
			}

			while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
				// we need to catch up
				//this.gamePanel.update(); // update without rendering
				sleepTime += FRAME_PERIOD;	// add frame period to check if in next frame
				framesSkipped++;
			}

			if (framesSkipped > 0) {
				Log.d(TAG, "Skipped:" + framesSkipped);
			}
			// for statistics
			framesSkippedPerStatCycle += framesSkipped;
			// calling the routine to store the gathered statistics
			storeStats();
	    	

		    //==============================	
		    // draws the canvas on the panel
		    this.gamePanel.onDraw(canvas);
		    }
		   } 
		   catch (Exception e){
			   e.printStackTrace();
		   }
		   finally {
		    // in case of an exception the surface is not left in
		    // an inconsistent state
		    if (canvas != null) {
		     surfaceHolder.unlockCanvasAndPost(canvas);
		    }
		   } // end finally
		  }
	}  
	private void storeStats() {
		frameCountPerStatCycle++;
		totalFrameCount++;

		// check the actual time
		statusIntervalTimer += (System.currentTimeMillis() - statusIntervalTimer);

		if (statusIntervalTimer >= lastStatusStore + STAT_INTERVAL) {
			// calculate the actual frames pers status check interval
			double actualFps = (double)(frameCountPerStatCycle / (STAT_INTERVAL / 1000));

			//stores the latest fps in the array
			fpsStore[(int) statsCount % FPS_HISTORY_NR] = actualFps;

			// increase the number of times statistics was calculated
			statsCount++;

			double totalFps = 0.0;
			// sum up the stored fps values
			for (int i = 0; i < FPS_HISTORY_NR; i++) {
				totalFps += fpsStore[i];
			}

			// obtain the average
			if (statsCount < FPS_HISTORY_NR) {
				// in case of the first 10 triggers
				averageFps = totalFps / statsCount;
			} else {
				averageFps = totalFps / FPS_HISTORY_NR;
			}
			// saving the number of total frames skipped
			totalFramesSkipped += framesSkippedPerStatCycle;
			// resetting the counters after a status record (1 sec)
			framesSkippedPerStatCycle = 0;
			statusIntervalTimer = 0;
			frameCountPerStatCycle = 0;

			statusIntervalTimer = System.currentTimeMillis();
			lastStatusStore = statusIntervalTimer;
//			Log.d(TAG, "Average FPS:" + df.format(averageFps));
			gamePanel.setAvgFps("FPS: " + df.format(averageFps));
		}
	}

	private void randomize(){
		int ran = r.nextInt(50-1);
		
		if (ran == 1){
			
			this.Left = true;
			//this.Mid = false;
			//this.Right = false;
			Log.d(TAG, "Render Left baddie");
		}
		else if (ran == 2){

			//this.Left = false;
			this.Mid = true;
			//this.Right = false;
			Log.d(TAG, "Render Mid baddie");
		}
		else if (ran == 3){

			//this.Left = false;
			//this.Mid = false;
			this.Right = true;
			Log.d(TAG, "Render Right baddie at: " + this.rightBaddie);
		}
	}
	
	
	
	
	private void initTimingElements() {
		// initialise timing elements
		fpsStore = new double[FPS_HISTORY_NR];
		for (int i = 0; i < FPS_HISTORY_NR; i++) {
			fpsStore[i] = 0.0;
		}
		Log.d(TAG + ".initTimingElements()", "Timing elements for stats initialised");
	}

	private void leftDoorLogic(){
		if (count == 0){
			this.gamePanel.loadBomber(1);
			count++;
			this.left_strike = false;
			this.Left = true;
			
		}
		else if (count == 1){
			
			this.gamePanel.loadBitmap(12, 250, count);
			this.left_strike = false;
			count++;
		}
		else if (count == 2){
			
			
			this.gamePanel.loadBitmap(12, 250, count);
			this.left_strike = false;
			count++;
		}
		else if (count == 3){
			
			this.gamePanel.loadBitmap(12, 250, count);
			this.left_strike = false;
			count++;
		}
		else if (count == 4){
			
			this.gamePanel.loadBitmap(12, 250, count);
			this.left_strike = true;
			count++;
		}
		else if (count == 5){
			
			this.gamePanel.loadBitmap(12, 250, count);
			this.left_strike = true;
			this.leftBaddie = System.currentTimeMillis();
			count++;
		}
		else if (count == 6){
			
			this.gamePanel.loadBitmap(12, 250, count);
			this.left_strike = true;
			count++;
		}
		else if (count == 7){
			
			this.gamePanel.loadBitmap(12, 250, count);
			this.left_strike = true;
			count++;
		}
		else if (count == 8){
			
			this.gamePanel.loadBitmap(12, 250, count);
			this.left_strike = true;
			count++;
		}
		else
		{	
			count = 0;
			//this.gamePanel.clearBitmap();
			this.gamePanel.loadBitmap(0, -50);
			this.left_strike = false;
			this.Left = false;
		}
		
	}
	
	private void midDoorLogic(){
		if (countMid == 0){
			this.gamePanel.loadBomber(2);
			countMid++;
			this.mid_strike = false;
			this.Mid = true;
			
		}
		else if (countMid == 1){
			countMid++;
			this.gamePanel.loadBitmap(160, 250, countMid);
			this.mid_strike = false;
			
		}
		else if (countMid == 2){
			
			countMid++;
			this.gamePanel.loadBitmap(160, 250, countMid);
			this.mid_strike = false;
			
		}
		else if (countMid == 3){
			countMid++;
			this.gamePanel.loadBitmap(160, 250, countMid);
			this.mid_strike = false;
			
		}
		else if (countMid == 4){
			countMid++;
			this.gamePanel.loadBitmap(160, 250, countMid);
			this.mid_strike = true;
			
		}
		else if (countMid == 5){
			countMid++;
			this.gamePanel.loadBitmap(160, 250, countMid);
			this.mid_strike = true;
			this.leftBaddie = System.currentTimeMillis();
			
		}
		else if (countMid == 6){
			countMid++;
			this.gamePanel.loadBitmap(160, 250, countMid);
			this.mid_strike = true;
			
		}
		else if (countMid == 7){
			countMid++;
			this.gamePanel.loadBitmap(160, 250, countMid);
			this.mid_strike = true;
			
		}
		else if (countMid == 8){
			countMid++;
			this.gamePanel.loadBitmap(160, 250, countMid);
			this.mid_strike = true;
		}
		else
		{	
			countMid = 0;
			this.gamePanel.clearBitmap();
			this.gamePanel.loadBitmap(0, -50);
			this.mid_strike = false;
			this.Mid = false;
		}
		
	}
	
	private void rightDoorLogic(){
		if (countRight == 0){
			this.gamePanel.loadBomber(3);
			countRight++;
			this.right_strike = false;
			this.Right = true;
			
		}
		else if (countRight == 1){
			countRight++;
			this.gamePanel.loadBitmap(310, 250, countRight);
			this.right_strike = false;
			
		}
		else if (countRight == 2){
			
			countRight++;
			this.gamePanel.loadBitmap(310, 250, countRight);
			this.right_strike = false;
			
		}
		else if (countRight == 3){
			countRight++;
			this.gamePanel.loadBitmap(310, 250, countRight);
			this.right_strike = false;
			
		}
		else if (countRight == 4){
			countRight++;
			this.gamePanel.loadBitmap(310, 250, countRight);
			this.right_strike = true;
			
		}
		else if (countRight == 5){
			countRight++;
			this.gamePanel.loadBitmap(310, 250, countRight);
			this.right_strike = true;
			this.leftBaddie = System.currentTimeMillis();
			
		}
		else if (countRight == 6){
			countRight++;
			this.gamePanel.loadBitmap(310, 250, countRight);
			this.right_strike = true;
			
		}
		else if (countRight == 7){
			countRight++;
			this.gamePanel.loadBitmap(310, 250, countRight);
			this.right_strike = true;
			
		}
		else if (countRight == 8){
			countRight++;
			this.gamePanel.loadBitmap(310, 250, countRight);
			this.right_strike = true;
		}
		else
		{	
			countRight = 0;
			this.gamePanel.clearBitmap();
			this.gamePanel.loadBitmap(0, -50);
			this.right_strike = false;
			this.Right = false;
		}
		
	}
	private void openLeftDoor(int x){
		if (x == 0){
			//this.gamePanel.loadBitmap(0, -50);
		}
		else if (x == 1){
			this.gamePanel.loadBitmap(122, 250, count);
		}
		else if (x == 2){
			this.gamePanel.loadBitmap(104, 250, count);
		}
		else if (x == 3){
			this.gamePanel.loadBitmap(90, 250, count);
		}
		else if (x == 4){
			this.gamePanel.loadBitmap(76, 250, count);
		}
		else if (x == 5){
			this.gamePanel.loadBitmap(50, 250, count);
		}
		else if (x == 6){
			this.gamePanel.loadBitmap(40, 250, count);
		}
		else if (x == 7){
			this.gamePanel.loadBitmap(25, 250, count);
		}
		else if (x == 8){
			this.gamePanel.loadBitmap(25, 250, count);
		}
		
	}
	
	private void openMidDoor(int x){
		if (x == 0){
			//this.gamePanel.loadBitmap(0, -50);
		}
		else if (x == 1){
			this.gamePanel.loadBitmap(122 + 50, 250, count);
		}
		else if (x == 2){
			this.gamePanel.loadBitmap(104 + 50, 250, count);
		}
		else if (x == 3){
			this.gamePanel.loadBitmap(90 + 50, 250, count);
		}
		else if (x == 4){
			this.gamePanel.loadBitmap(76 + 50, 250, count);
		}
		else if (x == 5){
			this.gamePanel.loadBitmap(50 + 50, 250, count);
		}
		else if (x == 6){
			this.gamePanel.loadBitmap(40 + 50, 250, count);
		}
		else if (x == 7){
			this.gamePanel.loadBitmap(25 + 50, 250, count);
		}
		else if (x == 8){
			this.gamePanel.loadBitmap(25 + 50, 250, count);
		}
		
	}
	
	private void openRightDoor(int x){
		if (x == 0){
			//this.gamePanel.loadBitmap(0, -50);
		}
		else if (x == 1){
			this.gamePanel.loadBitmap(122 + 100, 250, count);
		}
		else if (x == 2){
			this.gamePanel.loadBitmap(104 + 100, 250, count);
		}
		else if (x == 3){
			this.gamePanel.loadBitmap(90 + 100, 250, count);
		}
		else if (x == 4){
			this.gamePanel.loadBitmap(76 + 100, 250, count);
		}
		else if (x == 5){
			this.gamePanel.loadBitmap(50 + 100, 250, count);
		}
		else if (x == 6){
			this.gamePanel.loadBitmap(40 + 100, 250, count);
		}
		else if (x == 7){
			this.gamePanel.loadBitmap(25 + 100, 250, count);
		}
		else if (x == 8){
			this.gamePanel.loadBitmap(25 + 100, 250, count);
		}
		
	}
}	
	
	
	
