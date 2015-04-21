package controllers;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import view.HomeActivity;
import view.MainActivity;
import models.UserEntity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class UserController {

	private static UserEntity currentActiveUser;
	private static UserController userController;

	public static UserController getInstance() {
		if (userController == null)
			userController = new UserController();
		return userController;
	}

	private UserController() {
	}

	public void login(String userName, String password) {

		new Connection().execute(
				"http://1-dot-mahmoud20120366.appspot.com/rest/LoginService", userName,
				password, "LoginService");
	}

	public void signUp(String userName, String email, String password) {
		new Connection().execute(
				"http://1-dot-mahmoud20120366.appspot.com/rest/RegistrationService", userName,
				email, password, "RegistrationService");
	}
	public static String fun(String ...params){
		String uri = params[0];
		
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(uri);
		StringEntity s = null;
		//serviceType = params[params.length-1];
		String urlParameters = "";
		try {
			urlParameters = "uname=" + params[1] + "&password=" + params[2];
		s = new StringEntity(urlParameters);
		} catch (UnsupportedEncodingException e1) {
			return "UnsupportedEncoding";
		}
		
		s.setContentEncoding("UTF-8");
		s.setContentType("application/x-www-form-urlencoded");
		request.setEntity(s);
		try {
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String ss = EntityUtils.toString(entity);
		return ss;
		}catch(Exception e){
			return "response failed";
			
		}finally{
			
		}
	}
	static private class Connection extends AsyncTask<String, String, String> {

		//String serviceType;
		
		@Override
		protected String doInBackground(String... params) {
			return fun(params);
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
//			super.onPostExecute(result);
//			System.out.println("\n"+result);
//			try {
//				JSONObject object = new JSONObject(result);
//				
//				if(!object.has("Status") || object.getString("Status").equals("Failed")){
//					Toast.makeText(Application.getAppContext(), "Error occured", Toast.LENGTH_LONG).show();
//					return;
//				}
//				
//				if (serviceType.equals("LoginService")) {
//					
//					currentActiveUser = UserEntity.createLoginUser(result);
//					
//					Intent homeIntent = new Intent(Application.getAppContext(),
//							HomeActivity.class);
//					System.out.println("--- " + serviceType + "IN LOGIN " + object.getString("Status"));
//					
//					homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					/* here you should initialize user entity */
//					homeIntent.putExtra("status", object.getString("Status"));
//					homeIntent.putExtra("name", object.getString("name"));
//					
//					Application.getAppContext().startActivity(homeIntent);
//				}
//				else{
//					Intent homeIntent = new Intent(Application.getAppContext(),
//							HomeActivity.class);
//					homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					homeIntent.putExtra("status", "Registered successfully");
//					Application.getAppContext().startActivity(homeIntent);
//				}
//
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}

	}

}
