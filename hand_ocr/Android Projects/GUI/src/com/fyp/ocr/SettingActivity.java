 package com.fyp.ocr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class SettingActivity<MyAndroidAppActivity> extends Activity {
    /** Called when the activity is first created. */
	private RadioGroup radio;
	private RadioButton btn1;
	private RadioButton btn2;
    private ImageButton okBtn;
    private ImageButton cancel;

	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
   
        btn1 = (RadioButton) findViewById(R.id.radio1step);
        btn2 = (RadioButton) findViewById(R.id.radio2step);
        radio = (RadioGroup) findViewById(R.id.radioGroup1);
        String str = OCRguiActivity.readFromFile();
    	Toast.makeText(this, "lenght " + str.length(), Toast.LENGTH_LONG).show();
       if(str.length()>0)
       {
        if (str.contains("1"))
        {
        btn1.setChecked(true);
        }
        else if (str.contains("2"))
        {
        	btn2.setChecked(true);
        }
       }
       else{
    	   btn2.setChecked(true);
       }
        	
        	

        
        okBtn = (ImageButton) findViewById(R.id.accept);
        cancel = (ImageButton) findViewById(R.id.cancel);

        addListenerOnButton();      

    }    
        public void addListenerOnButton() {
        	          
        	okBtn.setOnClickListener(new OnClickListener() {
         int id;
        		@Override
        		public void onClick(View v) {
        			if (btn1.isChecked())
        			{
        				id =1 ;
        			}
        			else if (btn2.isChecked())
        			{
        				id = 2;
        			}
         
        		        // get selected radio button from radioGroup
        			//int selectedId = radio.getCheckedRadioButtonId();
         
        			// find the radiobutton by returned id
        		       // btn1 = (RadioButton) findViewById(selectedId);
         
        			try {
        				File myFile = new File("/sdcard/mysdfile.txt");
        				myFile.createNewFile();
        				FileOutputStream fOut = new FileOutputStream(myFile);
        				OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
        				myOutWriter.append(""+id);
        				myOutWriter.flush();
        				myOutWriter.close();
        				fOut.close();
        				Toast.makeText(getBaseContext(),"Settings Saved", Toast.LENGTH_SHORT).show();
        			
        			} 
        			catch (Exception e) {
        				Toast.makeText(getBaseContext(), e.getMessage(),
        						Toast.LENGTH_SHORT).show();
        			}
        			startActivity(new Intent (SettingActivity.this, MenuActivity.class));
            		SettingActivity.this.finish();
        		}
         
        	}); 
        	cancel.setOnClickListener(new OnClickListener() {
                
        		@Override
        		public void onClick(View v) {
         
        		        // get selected radio button from radioGroup
        			
        			startActivity(new Intent (SettingActivity.this, MenuActivity.class));
            		SettingActivity.this.finish();
        		}
         
        	}); 
        	
       
    }
        /*private String readFromFile() {
   		 
    		//Get the text file 
    		File file = new File(Environment.getExternalStorageDirectory(),"mysdfile.txt"); 
    		 
    		//Read text from file 
    		StringBuilder text = new StringBuilder(); 
    		 
    		try { 
    		    BufferedReader br = new BufferedReader(new FileReader(file)); 
    		    String line; 
    		 
    		    while ((line = br.readLine()) != null) { 
    		        text.append(line); 
    		       // text.append('\n'); 
    		    } 
    		    
    		} 
    		catch (IOException e) { 
    		    //You'll need to add proper error handling here 
    		}
    		String str = text.toString();
    		OCRguiActivity.seting=str.toString();
    		return str;		 
     }*/

 }