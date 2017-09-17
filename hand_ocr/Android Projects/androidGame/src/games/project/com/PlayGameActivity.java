package games.project.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PlayGameActivity extends Activity 
{
	private EditText get_input;
	String check;
	public static String var1 = "";
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        
        get_input = (EditText)findViewById(R.id.editText1);
        Button click = (Button)findViewById(R.id.aa);
        
        click.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("a");
        	}
        });
        
        Button b = (Button) findViewById(R.id.b);
        b.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("b");
        	}
        });
        
        Button c = (Button) findViewById(R.id.c);
        c.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("c");
        	}
        });
        
        Button d = (Button) findViewById(R.id.d);
        d.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("d");
        	}
        });
        Button e = (Button) findViewById(R.id.e);
        e.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("e");
        	}
        });
        Button f = (Button) findViewById(R.id.f);
        f.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("f");
        	}
        });
        Button g = (Button) findViewById(R.id.g);
        g.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("g");
        	}
        });
        Button h = (Button) findViewById(R.id.h);
        h.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("h");
        	}
        });
        Button i = (Button) findViewById(R.id.i);
        i.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("i");
        	}
        });
        Button j = (Button) findViewById(R.id.j);
        j.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("j");
        	}
        });
        Button k = (Button) findViewById(R.id.k);
        k.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("k");
        	}
        });
        Button l = (Button) findViewById(R.id.l);
        l.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("l");
        	}
        });
        Button m = (Button) findViewById(R.id.m);
        m.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("m");
        	}
        });
        Button n = (Button) findViewById(R.id.n);
        n.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("n");
        	}
        });
        Button o = (Button) findViewById(R.id.o);
        o.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("o");
        	}
        });
        Button p = (Button) findViewById(R.id.p);
        p.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("p");
        	}
        });
        Button q = (Button) findViewById(R.id.q);
        q.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("q");
        	}
        });
        Button r = (Button) findViewById(R.id.r);
        r.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("r");
        	}
        });
        Button s = (Button) findViewById(R.id.s);
        s.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("s");
        	}
        });
        Button t = (Button) findViewById(R.id.t);
        t.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("t");
        	}
        });
        Button u = (Button) findViewById(R.id.u);
        u.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("u");
        	}
        });
        Button v = (Button) findViewById(R.id.v);
        v.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("v");
        	}
        });
        Button w = (Button) findViewById(R.id.w);
        w.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("w");
        	}
        });
        Button x = (Button) findViewById(R.id.x);
        x.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("x");
        	}
        });
        Button y = (Button) findViewById(R.id.y);
        y.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("y");
        	}
        });
        Button z = (Button) findViewById(R.id.z);
        z.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		get_input.append("z");
        	}
        });
        
        Button Clear = (Button) findViewById(R.id.clear);
        Clear.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		check = get_input.getText().toString();
        		if(check != "")
        		{
        			get_input.setText("");
        		}
        	}
        });
        
        
        Button space = (Button) findViewById(R.id.space);
        space.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		String a = get_input.getText().toString();
        		a+= " ";
        		get_input.setText(a);
        		a="";
        	}
        });
        
        Button back = (Button) findViewById(R.id.backSpace);
        back.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		String a = get_input.getText().toString();
        		String c = "";
        		int length;
        		
        		length = a.length();
        		length--;
        		for(int i=0; i<length; i++)
        		{
        			c += a.charAt(i);
        		}
        		get_input.setText(c);
        		a="";
        		c="";
        	}
        });
        
        Button OK = (Button) findViewById(R.id.ok);
        OK.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		var1 = get_input.getText().toString();
        		if(var1.isEmpty() == true)
        		{
        			get_input.setText("Please Write Your name!");
        		}
        		else
        		{
        			startActivity(new Intent(PlayGameActivity.this,PlayGameActivity2.class));
        			PlayGameActivity.this.finish();
        		}
        	}
        });
    }
}
