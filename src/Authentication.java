import java.io.File;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
//��Ʈ��ũ�� ������ �ִ� ��ü�� Remote��ü(�Ǵ� ���ݰ�ü)�� �ϰ� �̸� ȣ���ϴ� ���� local ��ü(�Ǵ� ���� ��ü)

public class Authentication<Token> extends UnicastRemoteObject implements RMIAuthInterface{
	
	
	protected Authentication() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	private static final String AUTH_NAME = "Authentication";
	private static final String CLIENT_NAME = "Client";
	
	public int token = 0;
	private ArrayList<Token> items;
	private TokenPool Token;
	File file = new File("AuthLog.txt");
	Logger logger = new Logger(file);
	String userId;
	
	public int login(String userID, String password) {
		if(userID.equals("admin") && password.equals("admin")) {
			userId = userID;
			token++;
			
			String sPass = "pass";
			String result = "�α��εǾ����ϴ�.";
			logger.logLogIn(userId, sPass, result);
			return token;
		}else {
			String sPass = "nonpass";
			String result = "�α��ε��� �ʾҽ��ϴ�.";
			
			logger.logLogIn(userId, sPass, result);
			return 0;
		}
		}
	
	@Override
	public int logout(int token) throws RemoteException {
		if(token !=0) {
		String result = "�α׾ƿ��Ǿ����ϴ�.";
		logger.logLogOut(userId, result);
		return 0;
	}
		return token;
	}
	
	public static void main(String args[]) {
        if (args.length != 0) {
            System.out.println("Incorrect number of parameters");
            System.out.println("Usage: java Authentication");
            System.exit(1);
        }

        try {
        	Authentication  objAuth = new Authentication();
        	 Naming.rebind(AUTH_NAME, objAuth);
        	 System.out.println("Auth node is ready to serve.");

             System.out.println("Press Enter to terminate.");
             System.in.read();

             Naming.unbind(AUTH_NAME);
             System.out.println("Auth node is leaving, bye. Press ctrl-c if the delay is too long.");
        }
        catch (java.rmi.ConnectException e) {
            System.err.println("Java RMI error: check if rmiregistry is running.");
	}
        catch (java.rmi.NotBoundException e) {
            System.err.println("Java RMI error: check if client node is running.");
        }

        catch (Exception e) {
            System.out.println("Unexpected exception at " + AUTH_NAME);
            e.printStackTrace();
        }
}
	
}