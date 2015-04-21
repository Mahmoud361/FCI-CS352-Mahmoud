package models;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.api.datastore.Entity;

public class UserEntity {
	private String name;
	private String email;
	private String password;
	private String ID;
	private String pageName;

	/**
	 * Constructor accepts user data
	 * 
	 * @param name
	 *            user name
	 * @param email
	 *            user email
	 * @param password
	 *            user provided password
	 */
	
	private UserEntity() {
		// TODO Auto-generated constructor stub
	}
	private UserEntity(String name, String email, String password , 
			String ID , String pageName) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.ID = ID;
		this.pageName = pageName;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return password;
	}

	/**
	 * 
	 * This static method will form UserEntity class using json format contains
	 * user data
	 * 
	 * @param json
	 *            String in json format contains user data
	 * @return Constructed user entity
	 */
	public static UserEntity createLoginUser(String json) {
			JSONObject object;
			try {
				object = new JSONObject(json);
				return  new UserEntity(object.get("name").toString(), object.get(
						"email").toString(), object.get("password").toString(),
						object.get("id").toString() , object.get("pageName").toString());
				
			
			} catch (JSONException e) {
				return null;
			}

	}

	public static UserEntity getInstance() {
		// TODO Auto-generated method stub
		return new UserEntity();
	}
}
