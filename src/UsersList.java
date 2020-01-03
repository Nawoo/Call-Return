import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UsersList {
	protected ArrayList vUser;
	
	public UsersList(String sUserFileName) throws FileNotFoundException, IOException {
		 
		BufferedReader objUserFile = new BufferedReader(new FileReader(sUserFileName));
		
		this.vUser = new ArrayList();
		
		while(objUserFile.ready()) {
			String userInfo = objUserFile.readLine();
			if(!userInfo.equals("")) {
				this.vUser.add(new User(userInfo));
			}
		}
}
}