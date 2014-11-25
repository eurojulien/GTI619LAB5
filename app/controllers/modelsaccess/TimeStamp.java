package controllers.modelsaccess;

import java.sql.Timestamp;
import java.util.Calendar;

public class TimeStamp {

	public static Timestamp getTimeStamp(){
		return new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
	}
	
	public static Timestamp getTimeInPast(long numberOfSecondsFromNow){
		
		Calendar cal = Calendar.getInstance();
		
		if (numberOfSecondsFromNow > 0) numberOfSecondsFromNow *= (-1);
		
		cal.add(Calendar.SECOND, (int)numberOfSecondsFromNow);
		
		return new java.sql.Timestamp(cal.getTime().getTime());
	}
}
