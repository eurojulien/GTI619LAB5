package controllers.modelsaccess;
import java.util.Calendar;

import models.Roles;
import models.Users;

public class Role {

	private static Role instance = null;
	
	private Role(){};
	
	public static Role Role(){
		if (instance == null){
			instance = new Role();
		}
		
		return instance;
	}
	
	// Creation d'un nouveau role
	public void addRole(String rolename){
		
		Roles role = new Roles();
		
		role.role 		= rolename;
		role.timestamp	= TimeStamp.getTimeStamp();
		role.save();
	}
	
	// Obtention d'un role
	public String getRoleName(int index){
		
		Roles role = Roles.find.byId((long) index);
		
		if (role != null){
			return role.role;
		}
		
		else{
			return null;
		}
	}
	
	// Obtention d'un role
	public String getRoleName(Users user){
		
		return user.role.role;
	}
}
