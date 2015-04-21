package controllers;

import models.UserEntity;
import controllers.HttpManager;
import android.content.Context;
import android.app.Application;
public class application extends android.app.Application{
	private static Context context;
	private static HttpManager userController;

    public void onCreate(){
        super.onCreate();
        application.context = getApplicationContext();
        application.userController = HttpManager.getInstance();
    }
    public static void HttpManager(HttpManager userController) {
		application.userController = userController;
	}
    public static Context getAppContext() {
        return application.context;
    }
    
    public static HttpManager getUserController(){
    	return application.userController;
    }

}
