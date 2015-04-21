package view;

import models.UserEntity;

import com.test.R;

import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.content.Intent;
import android.media.MediaMuxer.OutputFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
public class HomeActivity extends ActionBarActivity {
	 private TextView output;
	 //private Button button;
	 private Switch logout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		callBackLogout();
		output = (TextView)findViewById(R.id.textView1);
	    String json = getIntent().getExtras().getString("json");
	    UserEntity currentUser = UserEntity.createLoginUser(json);
	    String name = currentUser.getName();
	    output.setText("Hello " + name);
//	    //TextView link = (TextView) findViewById(R.id.textView2);
//	    String linkText = "Visit the <a href='http://1-dot-mahmoud20120366.appspot.com/'>refrence</a> web page."
//	    		+ "<input type = "+"text"+"></input>";
//	    link.setText(Html.fromHtml(linkText));
//	    link.setMovementMethod(LinkMovementMethod.getInstance());
	}
	private void callBackLogout() {
		  logout = (Switch) findViewById(R.id.switch1);
		  //set the switch to ON 
		  logout.setChecked(false);
		  //attach a listener to check for changes in state
		  logout.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		 
		   @Override
		   public void onCheckedChanged(CompoundButton buttonView,
		     boolean isChecked) {
			   
			   if(isChecked){
				   Intent in = new Intent(HomeActivity.this , MainActivity.class);
				   startActivity(in );
			    }else{
			    	
			    }
		   }
		  });
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
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