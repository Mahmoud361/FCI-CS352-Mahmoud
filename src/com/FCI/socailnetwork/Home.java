package com.FCI.socailnetwork;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Home extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        
        Button login = (Button) findViewById(R.id.button1);
        login.setOnClickListener(
        		new OnClickListener(){
        			@Override
        			public void onClick(View v) {
        				//EditText text1 = (EditText)findViewById(R.layout.Text1);
        				EditText text1 = (EditText)findViewById(R.id.editText1);
        				//String value = String.valueOf(text1.getText());
        				text1.setText("Hi ");
        			}
        		}
        		);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
