package controllers.modelsaccess;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.h2.util.Task;

import com.avaje.ebean.Ebean;

import models.Roles;
import models.Users;
import models.Passwords;

public class User {

	private static User instance = null;
	
	private User(){};
	
	public static User User(){
		if (instance == null){
			instance = new User();
		}
		
		return instance;
	}
	
	public boolean addUser(	String firstName, 
							String lastName, 
							Roles role,
							String login,
							String password,
							String email){
		
		Users userAllReadyCreated = Ebean.find(Users.class)
		.where()
		.or(
				com.avaje.ebean.Expr.eq("login", login),
				com.avaje.ebean.Expr.eq("email", email)
		).findUnique();
		
		if (userAllReadyCreated != null){
			return false;
		}
		
		Users user = new Users();
		
		user.firstname 	= firstName;
		user.lastname	= lastName;
		user.role		= role;
		// TODO : Hash the password
		user.password	= password;
		user.email		= email;
		user.isactive	= true;
		user.timestamp 	= TimeStamp.getTimeStamp();
		
		user.save();
		
		return true;
	}
	
	public boolean changePassword(Users user, String newPassword, String oldPassword){
		
		if (user.password.equals(oldPassword)){
			user.password 	= newPassword;
			user.timestamp 	= TimeStamp.getTimeStamp();
			user.save();
			
			return true;
		}
		
		
		return false;
	}
	
	public boolean resetPassword(String login, String email, String newPassword, String codeFromAdmin){
		
		Passwords password = Ebean.find(Passwords.class)
		.where()
		.le("enddateTime", TimeStamp.getTimeStamp())
		.eq("passphrase", codeFromAdmin)
		.eq("isvalid", true)
		.findUnique();
		
		if (password == null){
			return false;
		}
		
		Users user = Ebean.find(Users.class)
		.where()
		.eq("login", login)
		.eq("email", email)
		.findUnique();
		
		try{
			user.password = newPassword;
			user.timestamp = TimeStamp.getTimeStamp();
			user.save();
		}
		catch(Exception e){
			return false;
		}
		
		return true;
	}
	
}