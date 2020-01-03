
/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-16
 * @version 1.0
 * @description 사용자의 입력에 따라 학생과 과목을 조회, 등록을 할 수 있는 Text-based User Interface
 */
public class Client {

	private static final String CLIENT_NAME = "Client";
	private static final String LOGIC_NAME = "Logic";
	private static final String AUTH_NAME = "Authentication";

	protected RMILogicInterface rmiLogicNode;
	protected RMIAuthInterface rmiAuthNode;

	/**
	 * Constructor. Client 객체가 생성될 때, 입력값으로 받은 string name을 rmi로부터 lookup한다.
	 * 
	 * @param sLogicName
	 */

	public Client(String sLogicName, String sAuthName)
			throws NotBoundException, MalformedURLException, RemoteException {
		this.rmiLogicNode = (RMILogicInterface) Naming.lookup(sLogicName);
		this.rmiAuthNode = (RMIAuthInterface) Naming.lookup(sAuthName);
	}

	/**
	 * 사용자의 입력에 따라 학생과 과목을 조희, 등록할 수 있다.
	 */
	int token;

	public void execute() throws RemoteException, IOException {
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			// 사용자가 입력가능한 메뉴 출력
			System.out.println("0. Login");
			System.out.println("1. List Students");
			System.out.println("2. List Courses");
			System.out.println("3. List students who registered for a course");
			System.out.println("4. List courses a student has registered for");
			System.out.println("5. List courses a student has completed");
			System.out.println("6. Register a student for a course");
			System.out.println("7. Logout");
			System.out.println("\n 10. Quit the system");
			String sChoice = objReader.readLine().trim();

			if (sChoice.equals("0")) {
				System.out.println("\nEnter ID and press return >> ");
				String userID = objReader.readLine().trim();
				System.out.println("\nEnter password and press return >> ");
				String password = objReader.readLine().trim();

				token = this.rmiAuthNode.login(userID, password);
				this.rmiLogicNode.giveUser(userID);
				System.out.println("환영합니다."+userID+"님!");
				continue;
			}
			if (sChoice.equals("1")) {
				System.out.println("\n" + this.rmiLogicNode.getAllStudents(token));
				continue;
			}
			if (sChoice.equals("2")) {
				System.out.println("\n" + this.rmiLogicNode.getAllCourses(token));
				continue;
			}
			if (sChoice.equals("3")) {
				System.out.println("\nEnter course ID and press return >> ");
				String sCID = objReader.readLine().trim();

				System.out.println("\n" + this.rmiLogicNode.getRegisteredStudents(sCID, token));
				continue;
			}

			if (sChoice.equals("4")) {
				System.out.println("\nEnter student ID and press return >> ");
				String sSID = objReader.readLine().trim();

				System.out.println("\n" + this.rmiLogicNode.getRegisteredCourses(sSID, token));
				continue;
			}

			if (sChoice.equals("5")) {
				System.out.println("\nEnter student ID and press return >> ");
				String sSID = objReader.readLine().trim();

				System.out.println("\n" + this.rmiLogicNode.getCompletedCourses(sSID, token));
				continue;
			}

			if (sChoice.equals("6")) {
				System.out.println("\nEnter student ID and press return >> ");
				String sSID = objReader.readLine().trim();
				System.out.println("\nEnter course ID and press return >> ");
				String sCID = objReader.readLine().trim();

				System.out.println("\n" + this.rmiLogicNode.makeARegistration(sSID, sCID, token));
				continue;
			}

			if (sChoice.equals("7")) {
				System.out.println("\n" + this.rmiAuthNode.logout(token));
				token = this.rmiAuthNode.logout(token);
				continue;
			}
			if (sChoice.equalsIgnoreCase("10")) {
				break;
			}
		}

		objReader.close();
	}

	public static void main(String args[]) {
		if (args.length != 0) {
			System.out.println("Incorrect number of parameters");
			System.out.println("Usage: java Client");
			System.exit(1);
		}

		try {
			Client objClient = new Client(LOGIC_NAME, AUTH_NAME);
			objClient.execute();
		} catch (java.rmi.ConnectException e) {
			System.err.println("Java RMI error: check if rmiregistry is running.");
			// System.exit(1);
		} catch (java.rmi.NotBoundException e) {
			System.err.println("Java RMI error: check if logic node is running.");
			// System.exit(1);
		}

		catch (Exception e) {
			System.out.println("Unexpected exception at " + CLIENT_NAME);
			e.printStackTrace();
			// System.exit(1);
		}
	}
}
