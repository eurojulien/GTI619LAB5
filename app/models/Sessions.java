package models;

import java.util.*;

import javax.persistence.*;

import org.h2.util.Task;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import java.sql.Timestamp;;

@Entity 
public class Sessions extends Model {
	
  @Id
  @Constraints.Required
  public Long id;
  
  @Constraints.Required
  public Users user;
  
  @Constraints.Required
  public Timestamp startdatetime;
  
  @Constraints.Required
  public long maximumtimeelapse;
  
  @Constraints.Required
  public String token;
  
  @Constraints.Required
  public boolean isactive = true;
  
  public Timestamp timeStamp;
  
  public static Finder<Long,Sessions> find = new Finder<Long,Sessions>(
    Long.class, Sessions.class
  );
  
  public static Finder<String,Sessions> findt = new Finder<String,Sessions>(
    String.class, Sessions.class
  );
}