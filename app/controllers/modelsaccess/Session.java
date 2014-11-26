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
	public boolean addSession(Users user, String macAddress, int timeLapsInSecond){
		
		Sessions sessionInActivity = Ebean.find(Sessions.class)
		.where()
		.eq("macaddress", macAddress)
		.findUnique();
		
		// La session existe deja, mais est innactive
		if (!sessionInActivity.isactive){
			return false;
		}
		
		// La session est deja en cours sous l'ordinateur présent
		if (sessionInActivity != null){
			refreshSession(sessionInActivity);
			return true;
		}
		
		Sessions userSession = Ebean.find(Sessions.class)
		.where()
		.eq("user", user)
		.findUnique();
		
		// Le meme utilisateur a demandé une nouvelle session, a depuis un autre ordinateur !!!
		if (userSession != null){
			return false;
		}
		
		Sessions session = new Sessions();
		
		session.user 				= user;
		session.isactive 			= true;
		//session.superpassphrase		= macAddress;
		session.maximumtimeelapse	= timeLapsInSecond;
		session.startdatetime		= TimeStamp.getTimeStamp();
		session.timeStamp			= TimeStamp.getTimeStamp();
		
		session.save();
		
		return true;
	}
	
	public void refreshSession(Sessions session){
		session.timeStamp = TimeStamp.getTimeStamp();
		session.save();
	}
	
	public void checkActiveStateOfAllSessions(){
		
		List<Sessions> sessions = Sessions.find.all();
			
		for (Sessions session : sessions){
			
			Timestamp pastTime = TimeStamp.getTimeInPast(session.maximumtimeelapse);
			
			if(pastTime.after(session.timeStamp)){
				session.isactive = false;
				session.save();
			}
		}
	}
	
	public void deleteAllInactiveSessions(){

		List<Sessions> sessions = Sessions.find.all();
		
		for (Sessions session : sessions){
			
			if(!session.isactive) session.delete();
		}
	}
	
	public boolean isSessionIsActive(Sessions session){
		return session.isactive;
	}
}
