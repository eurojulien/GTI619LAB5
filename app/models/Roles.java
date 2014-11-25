package models;

import java.util.*;

import javax.persistence.*;

import org.h2.util.Task;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import java.sql.Timestamp;;

@Entity 
public class Roles extends Model {

  @Id
  @Constraints.Required
  public Long id;
  
  @Constraints.Required
  public String role;
  
  public Timestamp timestamp;
  
  public static Finder<Long,Task> find = new Finder<Long,Task>(
    Long.class, Task.class
  );
  
}