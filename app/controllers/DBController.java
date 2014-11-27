package controllers;
import java.util.ArrayList;

import models.*;

public class DBController {

	private static DBController instance = null;
	private ArrayList<Users> 	users;
	private ArrayList<Roles> 	roles;
	private ArrayList<Sessions> sessions;
	
	private DBController(){
		users 		= new ArrayList<Users>();
		roles 		= new ArrayList<Roles>();
		sessions 	= new ArrayList<Sessions>();
	}
	
	// Singleton protection
	public static DBController DBController(){
		
		if (instance == null){
			instance = new DBController();
		}
		
		return instance;
	}
	
	public Users login(String login, String password){
		
		for (Users user : users){
			
			if (user.isactive){
				
				if(user.login.equals(login) && user.password.equals(password)){
					return user;
				}
			}
		}
		
		return null;
	}
	
	private void startNewSession(Users user){
		
		
		
		sessions.add(new Sessions().id);
	}
}
