import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class User implements Serializable{
	protected String userId;
    protected String password;
    protected ArrayList completedUserList;
    
    public User(String inputString) {
    	StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.userId = stringTokenizer.nextToken();
    	this.password = stringTokenizer.nextToken();
    	
    	while (stringTokenizer.hasMoreTokens()) {
    		this.completedUserList.add(stringTokenizer.nextToken());
    	}
    }
    public boolean match(String userId,String password) {
        return this.userId.equals(userId);
    }
    public String toString() {
    	String stringReturn = this.userId + " " + this.password;

        for (int i = 0; i < this.completedUserList.size(); i++) {
            stringReturn = stringReturn + " " + this.completedUserList.get(i).toString();
        }

        return stringReturn;
    }
	public String getID() {
		return this.userId;
	}
}
