package models;

import java.util.*;

import javax.persistence.*;

import org.h2.util.Task;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import java.sql.Timestamp;;

@Entity 
public class Users extends Model {

  @Id
  @Constraints.Required
  public Long id;
  
  @Constraints.Required
  public Roles role;
  
  @Constraints.Required
  public String firstname;
  
  @Constraints.Required
  public String lastname;
  
  @Constraints.Required
  @Constraints.Email
  public String email;
  
  @Constraints.Required
  public boolean isactive;
  
  public Timestamp timestamp;
  
  public static Finder<Long,Task> find = new Finder<Long,Task>(
    Long.class, Task.class
  );
  
  public static Finder<String,Task> findt = new Finder<String,Task>(
    String.class, Task.class
  );
  
}