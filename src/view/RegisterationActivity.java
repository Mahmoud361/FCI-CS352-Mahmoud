package view;

import models.UserEntity;

import com.test.R;

import controllers.HttpManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class RegisterationActivity extends ActionBarActivity{
	private Switch gotoLogin;
	private TextView nameView;
	private TextView mailView;
	private TextView passView;
	private TextView responseView;
	private EditText name;
	private EditText mail;
	private EditText pass;
	private Button register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registeration);
	
	nameView = (TextView)findViewById(R.id.NAME);
	mailView = (TextView)findViewById(R.id.MAIL);
	passView = (TextView)findViewById(R.id.PASS);
	responseView = (TextView)findViewById(R.id.RESPONSE);
	name = (EditText)findViewById(R.id.USER_NAME);
	mail = (EditText)findViewById(R.id.EMAIL);
	pass = (EditText)findViewById(R.id.PASSWORD);
	
	register = (Button)findViewById(R.id.SUBMIT);
		nameView.setText("username:");
		mailView.setText("email:");
		passView.setText("password:");
		
		register.setOnClickListener(
				new Button.OnClickListener(){
					public void onClick(View v) {
						
						String userName = String.valueOf(name.getText());
						String email = String.valueOf(mail.getText());
						String password = String.valueOf(pass.getText());
						if(isOnline()){
							if(userName.equals("") || userName.equals(null)
									|| email.equals("") || email.equals(null)
									|| password.equals("") || password.equals(null)){
								
								responseView.setText("all information required");
							}else{
								HttpManager manager = new HttpManager();
								String json = manager.login(
		        						new Pair("url" , "http://1-dot-mahmoud20120366.appspot.com/rest/RegistrationService")
		        						,new Pair ("uname" , userName) ,new Pair("email" , email), new Pair("password" , password));
		        				//System.out.println("\n"+json);
								if(json.contains("Faild")){
									responseView.setText("can't register or email arleady exist");
								}else if(json.contains("OK")){
									responseView.setText("done");
									name.setText("");
									mail.setText("");
									pass.setText("");
								}
							}
						}else{
							Toast.makeText(getBaseContext(), "Network isn't available", Toast.LENGTH_LONG).show();
						}
					};
				}
				);
		
		
		gotoLogin = (Switch) findViewById(R.id.GOTO_LOGIN);
		  
		  //set the switch to ON 
		gotoLogin.setChecked(true);
		  //attach a listener to check for changes in state
		gotoLogin.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		 
		   @Override
		   public void onCheckedChanged(CompoundButton buttonView,
		     boolean isChecked) {
			   
			   if(!isChecked){
				   Intent in = new Intent(RegisterationActivity.this , MainActivity.class);
				   startActivity(in );
			    }
		   }
		  });
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
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
