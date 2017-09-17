package skew.pr;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class SkewTestActivity extends Activity {
 
 private final String imageInSD = "/sdcard/er.PNG";
 
 ImageView myImageView;
 Spinner spinnerScale;
 SeekBar seekbarRotate;
 SeekBar seekbarSkewX, seekbarSkewY;
 TextView textSkewX, textSkewY;
 
 private static final String[] strScale
  = {"0.2x", "0.5x", "1.0x", "2.0x", "5.0x"};
 private static final Float[] floatScale
  = {0.2F, 0.5F, 1F, 2F, 5F};
 private final int defaultSpinnerScaleSelection = 2;
 
 private ArrayAdapter<String> adapterScale;
 
 private float curScale = 1F;
 private float curRotate = 0F;
 private float curSkewX = 0F;
 private float curSkewY = 0F;
 
 Bitmap bitmap;
 int bmpWidth, bmpHeight;
 
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.main);
      
       myImageView = (ImageView)findViewById(R.id.imageview);
      
       spinnerScale = (Spinner)findViewById(R.id.scale);
       seekbarRotate = (SeekBar)findViewById(R.id.rotate);
       seekbarSkewX = (SeekBar)findViewById(R.id.skewx);
       seekbarSkewY = (SeekBar)findViewById(R.id.skewy);
       textSkewX = (TextView)findViewById(R.id.textskewx);
       textSkewY = (TextView)findViewById(R.id.textskewy);
      
       adapterScale = new ArrayAdapter<String>(this,
               android.R.layout.simple_spinner_item, strScale);
       adapterScale.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spinnerScale.setAdapter(adapterScale);
       spinnerScale.setSelection(defaultSpinnerScaleSelection);
       curScale = floatScale[defaultSpinnerScaleSelection];
      
       //bitmap = BitmapFactory.decodeFile(imageInSD);
       bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.testrot);
       bmpWidth = bitmap.getWidth();
       bmpHeight = bitmap.getHeight();
      
       drawMatrix();
      
       spinnerScale.setOnItemSelectedListener(spinnerScaleOnItemSelectedListener);
       seekbarRotate.setOnSeekBarChangeListener(seekbarRotateSeekBarChangeListener);
       seekbarSkewX.setOnSeekBarChangeListener(seekbarSkewXSeekBarChangeListener);
       seekbarSkewY.setOnSeekBarChangeListener(seekbarSkewYSeekBarChangeListener);
   }
  
   private void drawMatrix(){
    
    Matrix matrix = new Matrix();
       matrix.postScale(curScale, curScale);
       matrix.postRotate(curRotate);
       matrix.postSkew(curSkewX, curSkewY);
       
       Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bmpWidth, bmpHeight, matrix, true);
       myImageView.setImageBitmap(resizedBitmap);
    
   }
  
   private SeekBar.OnSeekBarChangeListener seekbarSkewYSeekBarChangeListener
    = new SeekBar.OnSeekBarChangeListener() {
   
   @Override
   public void onStopTrackingTouch(SeekBar seekBar) {
    // TODO Auto-generated method stub
    
   }
   
   @Override
   public void onStartTrackingTouch(SeekBar seekBar) {
    // TODO Auto-generated method stub
    
   }
   
   @Override
   public void onProgressChanged(SeekBar seekBar, int progress,
     boolean fromUser) {
    // TODO Auto-generated method stub
    curSkewY = (float)(progress-100)/100;
    textSkewY.setText("Skew-Y: " + String.valueOf(curSkewY));
    drawMatrix();
   }
  };
  
   private SeekBar.OnSeekBarChangeListener seekbarSkewXSeekBarChangeListener
    = new SeekBar.OnSeekBarChangeListener(){

   @Override
   public void onProgressChanged(SeekBar seekBar, int progress,
     boolean fromUser) {
    // TODO Auto-generated method stub
    curSkewX = (float)(progress-100)/100;
    textSkewX.setText("Skew-X: " + String.valueOf(curSkewX));
    drawMatrix();
   }

   @Override
   public void onStartTrackingTouch(SeekBar seekBar) {
    // TODO Auto-generated method stub
    
   }

   @Override
   public void onStopTrackingTouch(SeekBar seekBar) {
    // TODO Auto-generated method stub
    
   }};
  
   private SeekBar.OnSeekBarChangeListener seekbarRotateSeekBarChangeListener
    = new SeekBar.OnSeekBarChangeListener(){

   @Override
   public void onProgressChanged(SeekBar seekBar, int progress,
     boolean fromUser) {
    // TODO Auto-generated method stub
    curRotate = (float)progress;
    drawMatrix();
   }

   @Override
   public void onStartTrackingTouch(SeekBar seekBar) {
    // TODO Auto-generated method stub
    
   }

   @Override
   public void onStopTrackingTouch(SeekBar seekBar) {
    // TODO Auto-generated method stub
    
   }};
  
   private Spinner.OnItemSelectedListener spinnerScaleOnItemSelectedListener
    = new Spinner.OnItemSelectedListener(){

   @Override
   public void onItemSelected(AdapterView<?> arg0, View arg1,
     int arg2, long arg3) {
    // TODO Auto-generated method stub
    curScale = floatScale[arg2];
    drawMatrix();
   }

   @Override
   public void onNothingSelected(AdapterView<?> arg0) {
    // TODO Auto-generated method stub
    spinnerScale.setSelection(defaultSpinnerScaleSelection);
    curScale = floatScale[defaultSpinnerScaleSelection];
   }};
   
}