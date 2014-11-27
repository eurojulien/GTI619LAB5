package controllers.modelsaccess;
import java.sql.Timestamp;
import java.util.List;

import com.avaje.ebean.Ebean;

import models.Users;
import models.Sessions;

public class Session {

	public static final int ADMIN_TIMEOUT = 120; // 2 minutes
	public static final int USER_TIMEOUT  = 300; // 5 minutes
	
	private static Session instance = null;
	
	private Session(){};
	
	public static Session Session(){
		if (instance == null){
			instance = new Session();
		}
		
		return instance;
	}
	
	// Creation d'une nouvelle session
	public String addSessionAndGetToken(Users user, int timeLapsInSecond){
		
		List<Sessions> sessions = Ebean.find(Sessions.class)
		.where()
		.eq("user", user)
		.findList();
		
		for (Sessions session : sessions){
			session.delete();
		}
		
		Sessions session = new Sessions();
		
		session.user 				= user;
		session.isactive 			= true;
		session.token				= Token.nextSessionId();
		session.maximumtimeelapse	= timeLapsInSecond;
		session.startdatetime		= TimeStamp.getTimeStamp();
		session.timeStamp			= TimeStamp.getTimeStamp();
		
		session.save();
		
		return session.token;
	}
	
	public boolean checkSession_RefreshOrDelete(String token){
		
		Sessions session = Ebean.find(Sessions.class)
		.where()
		.eq("token", token)
		.findUnique();
		
		// Session n'existe pas
		if (session == null){
			return false;
		}
		
		// Session est expiree
		Timestamp pastTime = TimeStamp.getTimeInPast(session.maximumtimeelapse);
		
		if(pastTime.after(session.timeStamp)){
			session.delete();
			return false;
		}
		
		session.timeStamp = TimeStamp.getTimeStamp();
		session.save();
		
		return true;
	}
}
