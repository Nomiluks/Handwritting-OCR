package com.fyp.ocr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SelectActivity extends Activity {
	/** Called when the activity is first created. */ 
	@Override public void onCreate(Bundle savedInstanceState) {     
		super.onCreate(savedInstanceState);  
		startActivity(new Intent(SelectActivity.this,SegmentActivity.class));
		SelectActivity.this.finish();
		}
}