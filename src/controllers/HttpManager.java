package controllers;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import models.UserEntity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import view.HomeActivity;
import view.MainActivity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Toast;
public class HttpManager {
	//UserEntity currentActiveUser ;
	private static UserEntity user;
	private static HttpManager userController;

	public static HttpManager getInstance() {
		if (userController == null)
			userController = new HttpManager();
		return userController;
	}

	public HttpManager() {
	}
	public String login(Pair<String , String>...params){
		Thread con  = new Thread();
		
		con.execute(params);
		try {
			String json = con.get();
			return json;
		} catch (InterruptedException e) {
			return null;
		} catch (ExecutionException e) {
			return null;
		}
	}
	public static String connect(Pair<String , String>...params){
		String uri = params[0].second;
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(uri);
		StringEntity se = null;
      String urlParameters = "";
		try {
			for(int i = 1;i<params.length;i++){
				urlParameters += params[i].first+"=" + params[i].second+"&";
			}
//			urlParameters = params[1].first+"=" + params[1].second + 
//					"&"+params[2].first+"=" + params[2].second;
		se = new StringEntity(urlParameters);
		} catch (UnsupportedEncodingException e1) {
			return "UnsupportedEncoding";
		}
		
		se.setContentEncoding("UTF-8");
		se.setContentType("application/x-www-form-urlencoded");
		request.setEntity(se);
		try {
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String retConnection = EntityUtils.toString(entity);
		return retConnection;
		}catch(Exception e){
			return "response failed";
			
		}finally{
			
		}
	}
	private class Thread extends AsyncTask<Pair<String , String>, Pair<String , String>, String>{
		@Override
		protected String doInBackground(
				Pair<String, String>... params) {
			String content = HttpManager.connect(params);
			
			return content;
		}
		@Override
		protected void onPostExecute(String result) {
			
		}
	}
}
