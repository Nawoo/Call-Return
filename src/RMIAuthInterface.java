import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIAuthInterface<Token>  extends Remote{
	public int login(String userID, String password)
	throws RemoteException;
	
	public int logout(int token)
	throws RemoteException;
}
