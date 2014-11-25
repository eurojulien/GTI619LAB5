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
  public String macaddress;
  
  @Constraints.Required
  public boolean isactive = true;
  
  public Timestamp timeStamp;
  
  public static Finder<Long,Task> find = new Finder<Long,Task>(
    Long.class, Task.class
  );
  
  public static Finder<String,Task> findt = new Finder<String,Task>(
    String.class, Task.class
  );
}