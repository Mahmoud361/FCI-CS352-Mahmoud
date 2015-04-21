package view;
import models.UserEntity;
import controllers.application;
import controllers.UserController;
import controllers.HttpManager;

import com.test.R;

import android.support.v7.app.ActionBarActivity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
public class MainActivity extends ActionBarActivity {
	TextView output;
	EditText text1;
	EditText text2;
	ProgressBar bar;
	private String email;
	private String  password;
	private Switch gotoRegister;
	@Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gotoRegister = (Switch) findViewById(R.id.GOTO_SIGNUP);
		  
		  //set the switch to ON 
        gotoRegister.setChecked(false);
		  //attach a listener to check for changes in state
        gotoRegister.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		 
		   @Override
		   public void onCheckedChanged(CompoundButton buttonView,
		     boolean isChecked) {
			   
			   if(isChecked){
				   Intent in = new Intent(MainActivity.this , RegisterationActivity.class);
				   startActivity(in);
			    }
		   }
		  });
        output = (TextView)findViewById(R.id.TextView);
        bar = (ProgressBar)findViewById(R.id.progressBar1);
        bar.setVisibility(View.INVISIBLE);
        text1 = (EditText)findViewById(R.id.editText1);
        text2 = (EditText)findViewById(R.id.editText2);
        Button login = (Button)findViewById(R.id.button1);
        login.setOnClickListener(
        		
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    	
                        email = String.valueOf(text1.getText());
                        password= String.valueOf(text2.getText());
            			if(isOnline()){
                            if(email.equals("") || email.equals(null)
    								|| password.equals("") || password.equals(null)){
                            	updateDisply("all information required");
                            }else{
	            				bar.setVisibility(View.VISIBLE);
	            				HttpManager manager = new HttpManager();
	            				//manager = application.getUserController();
	            				UserEntity currentUser = null;
	            				String json = manager.login(
	            						new Pair("url" , "http://1-dot-mahmoud20120366.appspot.com/rest/LoginService")
	            						,new Pair ("uname" , email) , new Pair("password" , password));
	            				if(json.equals("UnsupportedEncoding")){
	            					updateDisply("Connection error");
	            				}else if(json.equals("response failed")){
	            					updateDisply("check connectivity");
	            				}else{
	            					currentUser = UserEntity.createLoginUser(json);
	            				}
	            				
	            				if(currentUser == null){
	            					updateDisply("invaled name or password");
	            					bar.setVisibility(View.INVISIBLE);
	            				}else{
		            				Intent in = new Intent(MainActivity.this , HomeActivity.class);
		            				in.putExtra("json", json);
		            				//in.putExtra("email", currentUser.getEmail());
		            				//in.putExtra("password", currentUser.getPass());
		            				
		            				startActivity(in);
		            				
	            				}
            				}
            				
            			}else{
            				Toast.makeText(getBaseContext(), "Network isn't available", Toast.LENGTH_LONG).show();
            			}
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
		}
		return false;
	}

//	private void requestData(String... params) {
//		Login login  = new Login();
//		login.execute(params);
//	}
	public void updateDisply(String message){
		output.append(message + "\n");
	}
	protected boolean isOnline(){
		ConnectivityManager cm = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		
		if(netInfo != null &&netInfo.isConnectedOrConnecting()){
			return true;
		}
		return false;
	}
}