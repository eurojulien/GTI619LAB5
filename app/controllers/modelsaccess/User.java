package controllers.modelsaccess;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.h2.util.Task;

import com.avaje.ebean.Ebean;

import models.Roles;
import models.Users;
import models.Passwords;

public class User {

	private int PASSWORD_MIN_LENGTH = 12;
	private String RE_PATTERN_TINY 	= "\\p{Lower}";
	private String RE_PATTERN_CAPS 	= "\\p{Upper}";
	private String RE_PATTERN_INT	= "\\p{Digit}";
	private String RE_PATTERN_SPE	= "\\p{Punct}";
	
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
	
	private boolean checkIfPasswordMeetRequirements(String password){
		
		// Taille doit être de 12 caractères minimum
		if(password.length() < PASSWORD_MIN_LENGTH) return false;
		
		// Mot de passe doit contenir au moins une minuscule
		if(!Pattern.matches(RE_PATTERN_TINY, password)) return false;
		
		// Mot de passe doit contenir au moins une majuscule
		if(!Pattern.matches(RE_PATTERN_CAPS, password)) return false;
				
		// Mot de passe doit contenir au moins un chiffre
		if(!Pattern.matches(RE_PATTERN_INT, password)) return false;
			
		// Mot de passe doit contenir au moins un symbole
		if(!Pattern.matches(RE_PATTERN_SPE, password)) return false;
				
		return true;
	}
	
	private String hashPassword(String password){
		
		final Random r = new SecureRandom();
		byte[] salt = new byte[32];
		r.nextBytes(salt);
		
		return null;
	}
	
}