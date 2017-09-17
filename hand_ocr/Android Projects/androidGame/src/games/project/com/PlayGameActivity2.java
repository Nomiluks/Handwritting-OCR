package games.project.com;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PlayGameActivity2 extends Activity
{	
	 private EditText get_input2;
	 private EditText get_input3;
	 static String  a = "";
	 String maker = "";
	 int  c = 0;
	 static int inc = 0;
	 Random rand = new Random();
	 
	 public void onCreate(Bundle savedInstanceState) 
	 {
		 	inc = 0;
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.play);
	        get_input2 = (EditText)findViewById(R.id.editText1);
	        get_input3 = (EditText)findViewById(R.id.editText2);
	        get_input3.setText(inc+"");
	        
	        c = rand.nextInt(16);
	        if(c == 0)
	        {
	        	c = 1;
	        }
	        maker = "ran";
	        maker += c;
	        //maker = "ran16";
	        Button ran1 = (Button) findViewById(R.id.ran1);
	        ran1.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran1"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a); 
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	        });

	        Button ran2 = (Button) findViewById(R.id.ran2);
	        ran2.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran2"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
		        		if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran3 = (Button) findViewById(R.id.ran3);
	        ran3.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran3"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran4 = (Button) findViewById(R.id.ran4);
	        ran4.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran4"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran5 = (Button) findViewById(R.id.ran5);
	        ran5.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran5"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!");
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran6 = (Button) findViewById(R.id.ran6);
	        ran6.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran6"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran7 = (Button) findViewById(R.id.ran7);
	        ran7.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran7"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran8 = (Button) findViewById(R.id.ran8);
	        ran8.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran8"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran9 = (Button) findViewById(R.id.ran9);
	        ran9.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran9"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran10 = (Button) findViewById(R.id.ran10);
	        ran10.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran10"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran11 = (Button) findViewById(R.id.ran11);
	        ran11.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran11"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran12 = (Button) findViewById(R.id.ran12);
	        ran12.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran12"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran13 = (Button) findViewById(R.id.ran13);
	        ran13.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran13"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran14 = (Button) findViewById(R.id.ran14);
	        ran14.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran14"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!");
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran15 = (Button) findViewById(R.id.ran15);
	        ran15.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran15"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        	}
	       	});
	        
	        Button ran16 = (Button) findViewById(R.id.ran16);
	        ran16.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View v)
	        	{
	        		inc++;
	        		get_input3.setText(inc+"");
	        		if(maker.equals("ran16"))
	        		{
	        			a += PlayGameActivity.var1.toString();
	        			get_input2.setText(a);  
	        			a = "";
	        			if(inc <= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,HelpActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}
	        		else
	        		{
	        			get_input2.setText("Wrong attempt try again!"); 
	        			get_input2.setText(maker);
	        			if(inc >= 5)
	        	        {
	        	        	startActivity(new Intent(PlayGameActivity2.this,SettingActivity.class));
	                		PlayGameActivity2.this.finish();
	        	        }
	        		}       		
	        	}
	       	});
	 }
}
