package games.project.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainMenuActivity extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menus);
        
        String[] items = {getResources().getString(R.string.menu_item_play),
        		getResources().getString(R.string.menu_item_setting),
        		getResources().getString(R.string.menu_item_score),
        		getResources().getString(R.string.menu_item_help)};
        
        ListView menuList = (ListView)findViewById(R.id.ListView_menu);
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,R.layout.menu_item,items);
        menuList.setAdapter(adapt);
        
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() 
        	{
        		public void onItemClick(AdapterView<?> parent,View itemClicked,int position, long id)
        		{
        			TextView textView = (TextView) itemClicked;
        			String strText = textView.getText().toString();
        			if(strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_play)))
        			{
        				startActivity(new Intent(MainMenuActivity.this,PlayGameActivity.class));
        			}
        			else if(strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_setting)))
        			{
        				startActivity(new Intent(MainMenuActivity.this,SettingActivity.class));
        			}
        			else if(strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_help)))
        			{
        				startActivity(new Intent(MainMenuActivity.this,HelpActivity.class));
        			}
        			else if(strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_score)))
        			{
        				startActivity(new Intent(MainMenuActivity.this,ScoreActivity.class));
        			}
        	}
		});
    }
    

}
