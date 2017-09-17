package pkg.ocr.app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HelpActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        ImageButton b5 = (ImageButton)findViewById(R.id.backme);
        b5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent (HelpActivity.this, MenuActivity.class));
                HelpActivity.this.finish();
                finish();
            }
        }); 
    }
}
