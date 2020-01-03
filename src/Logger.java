import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Logger {
	private File file;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private PrintWriter printWriter;
	
	public Logger(File file){
		this.file = file;
		try  {
			fileWriter = new FileWriter(this.file);
			bufferedWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(bufferedWriter, true);
		}
		catch (IOException ioe) {
			System.out.println("IO Excetion");
		}
	}
public void dateInitialize() {
	
}
	public void logGetAllStudents(String userID, String result) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		printWriter.println("Time"+time);
		printWriter.println(userID + ": getAllStudents");
		printWriter.println("   Result: " + result);
	}
    public void logGetAllCourses(String userID, String result) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		printWriter.println("Time"+time);
    	printWriter.println(userID + ": getAllCourses");
    	printWriter.println("   Result: " + result);
    }
    public void logGetRegisteredStudents(String sCID, String sSection, String userID, String result) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		printWriter.println("Time"+time);
    	printWriter.println(userID + ": getRegisteredStudents(CID=" + sCID + ", Section=" + sSection + ")");
    	printWriter.println("   Result: " + result);
    }
    public void logGetRegisteredCourses(String sSID, String userID, String result) {
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		printWriter.println("Time"+time);
    	printWriter.println(userID + ": getRegisteredCourses(SID=" + sSID + ")");
    	printWriter.println("   Result: " + result);
    }
    public void logGetCompletedCourses(String sSID, String userID, String result){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		printWriter.println("Time"+time);
    	printWriter.println(userID + ": getCompletedCourses(SID=" + sSID + ")");
    	printWriter.println("   Result: " + result);
    }
	public void logMakeARegistration(String sSID, String sCID, String sSection, String userID, String result) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		printWriter.println("Time"+time);
		printWriter.println(userID + ": makeARegistration(SID=" + sSID + ", CID=" + sCID + ", Section=" + sSection + ")");
		printWriter.println("   Result: " + result);
	}
 	public void logLogIn(String sSID, String sPass, String result) {
 		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		printWriter.println("Time"+time);
 		printWriter.println("LogIn(SID=" + sSID + ", Pass=" + sPass + ")");
 		printWriter.println("   Result: " + result);
 	}
	public void logLogOut(String userID, String result){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		printWriter.println("Time"+time);
		printWriter.println(userID + ": LogOut");
		printWriter.println("   Result: " + result);
	}

	public void logGetStudentRecord(String sSID, String result) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		printWriter.println("Time"+time);
		printWriter.println(" :GetStudentRecord(SID = "+sSID + ")");
		printWriter.println("   Result: " + result);
		
	}

	public void logGetStudentName(String sSID, String result) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		printWriter.println("Time"+time);
		printWriter.println(" :getStudentName(SID = "+sSID + ")");
		printWriter.println("   Result: " + result);
		
	}
	public void logGetCourseRecord(String sCID, String result) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		printWriter.println("Time"+time);
		printWriter.println(": getCourseRecord(CID=" + sCID);
    	printWriter.println("   Result: " + result);
		
	}
	public void logCourseName(String sCID, String result) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String time = format.format(date);
		printWriter.println("Time"+time);
		printWriter.println(" :getStudentName(SID = "+sCID + ")");
		printWriter.println("   Result: " + result);
		
	}
}
