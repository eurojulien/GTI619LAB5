package controllers.modelsaccess;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.h2.util.Task;

import com.avaje.ebean.Ebean;

import models.Roles;
import models.Users;
import models.Passwords;


public class PasswordReInit {
	
	private static PasswordReInit instance = null;
	
	private PasswordReInit(){};
	
	public static PasswordReInit Password(){
		if (instance == null){
			instance = new PasswordReInit();
		}
		
		return instance;
	}
	
	public boolean addPasswordReInitialisation(
			String login,
			String email,
			int availableTimeInSeconds,
			String passphrase)
	{
		
		Users user = Ebean.find(Users.class)
		.where()
		.eq("login", login)
		.eq("email", email)
		.findUnique();
		
		if (user == null){
			return false;
		}
		
		Passwords password = new Passwords();
		
		password.user = user;
		password.
		
		return true;
	}
	
	
}
