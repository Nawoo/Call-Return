/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-16
 * @version 1.0
 */
public interface RMILogicInterface extends Remote {
    public String getAllStudents(int token) 
        throws RemoteException;

    public String getAllCourses(int token) 
        throws RemoteException;

    public String getRegisteredStudents(String sCID, int token) 
        throws RemoteException;

    public String getRegisteredCourses(String sSID, int token) 
        throws RemoteException;

    public String getCompletedCourses(String sSID, int token) 
        throws RemoteException;

    public String makeARegistration(String sSID, String sCID, int token) 
        throws RemoteException;

	public void giveUser(String userID)
		throws RemoteException;

}
