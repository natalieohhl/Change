/**
 *
 * @author natalie.oh
 * @date: 2020-06-14
 */
 
public class Change {
    private String userName; 
    private int coinChange;

 //default constructor     
 public Change() {
    userName = "No name yet";
    coinChange = 0;
 }    
    
//constructor with two parameters
public Change(String name, int change) {
 if (name.length() > 0)
      userName = name;
    else
      userName = "No name yet.";
 coinChange =change; 
}
 
 /**
  * This method sets the name of the user.
  * @param name This is the name to set for the object. 
  * precondition: name must be at least 1 character 
  */ 
public void setName(String name) {
  if (name.length() > 0)  
   userName = name; 
}

 /**
  * This method sets the age of the user.
  * @param change This is the change to set for the object.
  *  precondition: change is not zero, and is divisible by 5.
  */ 
public void setChange(int change) {
  coinChange = change;
}

 /**
  * This method gets the name of the user.
  * @return user's name.
  */ 
public String getName() {
    return userName;   
}

/**
  * This method gets the change of the user.
  * @return user's change.
  */ 
public int getChange() {
    return coinChange;   
}

/**
  * This method compares two user names to see whether it is the same
  * @return true if same, false if not same.
  */ 
public boolean equals(Change other) {
    if (this.userName.compareToIgnoreCase(other.userName) == 0) 
      return true;

    return false;
  }

}

