/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * @author Hwi Ahn, Pilsu Jung, Jungho Kim
 * @date 2013-08-16
 * @version 1.0
 * @description
 *     모든 학생들과 과목들의 정보를 가지고 있는 Component
 */
public class Data extends UnicastRemoteObject implements RMIDataInterface {

    private static final String DATA_NAME = "Data";

    protected StudentsList studentsList;

    protected CoursesList coursesList;

    public Data(String sStudentFileName, String sCourseFileName)
           throws RemoteException, FileNotFoundException, IOException {

        this.studentsList = new StudentsList(sStudentFileName);
        this.coursesList  = new CoursesList(sCourseFileName);
    }
    File file = new File("DataLog.txt");
	Logger logger = new Logger(file);
    String userId;
    /**
     * 紐⑤뱺 �븰�깮�젙蹂대�� 諛섑솚�븳�떎. 
     * @return
     */
    public ArrayList getAllStudentRecords() {
    	String result = "모든학생정보를 불러옵니다...";
    	logger.logGetAllStudents(userId, result);
        return this.studentsList.vStudent;
    }

    /**
     * 紐⑤뱺 怨쇰ぉ�젙蹂대�� 諛섑솚�븳�떎.
     * @return
     */
    public ArrayList getAllCourseRecords() {
    	String result = "모든과목정보를 불러옵니다...";
    	logger.logGetAllCourses(userId, result);
        return this.coursesList.vCourse;
    }

    /**
     * 입력받은 학번을 가진 학생정보를 반환한다.
     * @return
     */
    public Student getStudentRecord(String sSID) {
        for (int i=0; i<this.studentsList.vStudent.size(); i++) {
            Student objStudent = (Student) this.studentsList.vStudent.get(i);
            if (objStudent.match(sSID)) {
            	String result = "해당학생을 불러옵니다";
            	logger.logGetStudentRecord(sSID,result);
                return objStudent;
            }
        }
        return null;
    }
    
    /**
     * �엯�젰諛쏆� �븰踰덉쓣 媛�吏� �븰�깮�쓽 �씠由꾩쓣 諛섑솚�븳�떎.
     * @return
     */
    public String getStudentName(String sSID) {
        for (int i=0; i<this.studentsList.vStudent.size(); i++) {
            Student objStudent = (Student) this.studentsList.vStudent.get(i);
            if (objStudent.match(sSID)) {
            	String result = "해당학생이름을 불러옵니다";
            	logger.logGetStudentName(sSID,result);
                return objStudent.getName();
                
            }
        }
        return null;
    }

    /**
     * �엯�젰諛쏆� ID瑜� 媛�吏� 怨쇰ぉ�젙蹂대�� 諛섑솚�븳�떎.
     * @return
     */
    public Course getCourseRecord(String sCID) {
        for (int i=0; i<this.coursesList.vCourse.size(); i++) {
            Course objCourse = (Course) this.coursesList.vCourse.get(i);
            if (objCourse.match(sCID)) {
            	String result = "해당과목을 불러옵니다";
            	logger.logGetCourseRecord(sCID,result);
                return objCourse;
            }
        }
        return null;
    }
    
    /**
     * �엯�젰諛쏆� ID瑜� 媛�吏� 怨쇰ぉ�쓽 �씠由꾩쓣 諛섑솚�븳�떎.
     * @return
     */
    public String getCourseName(String sCID) {
        for (int i=0; i<this.coursesList.vCourse.size(); i++) {
            Course objCourse = (Course) this.coursesList.vCourse.get(i);
            if (objCourse.match(sCID)) {
            	String result = "해당과목이름을 불러옵니다";
            	logger.logCourseName(sCID,result);
                return objCourse.getName();
            }
        }
        return null;
    }

    /**
     * �엯�젰諛쏆� �븰踰덉쓣 媛�吏� �븰�깮�뿉寃� �엯�젰諛쏆� ID�쓣 媛�吏� 怨쇰ぉ�쓣 �벑濡앺븳�떎.
     */
    public void makeARegistration(String sSID, String sCID) {
        Student objStudent = this.getStudentRecord(sSID);
        Course  objCourse  = this.getCourseRecord(sCID);

        if (objStudent != null && objCourse != null) {
            objStudent.registerCourse(objCourse);
            objCourse.registerStudent(objStudent);
            String result = "과목등록이완료되었습니다.";
            logger.logMakeARegistration(sSID, sCID, "", userId, result);
        }
    }
    public void getUser(String userID) {
    	userId = userID;
    }
    public static void main(String args[]) {
        if (args.length != 2) {
            System.err.println("Incorrect number of parameters");
            System.err.println("Usage: java Data <StudentFile> <CourseFile>");
            System.exit(1);
        }

        if (!new File(args[0]).exists()) {
            System.err.println("Could not find " + args[0]);
            System.exit(1);
        }
        if (new File(args[1]).exists() == false) {
            System.err.println("Could not find " + args[1]);
            System.exit(1);
        }

        try {
            Data objData = new Data(args[0], args[1]);
            Naming.bind(DATA_NAME, objData);
            System.out.println("Data node is ready to serve.");

            System.out.println("Press Enter to terminate.");
            System.in.read();

            Naming.unbind(DATA_NAME);
            System.out.println("Data node is leaving, bye. Press ctrl-c if the delay is too long.");
        }
        catch (java.rmi.ConnectException e) {
            System.err.println("Java RMI error: check if rmiregistry is running.");
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Unexpected exception at " + DATA_NAME);
            e.printStackTrace();
            System.exit(1);
        }
       
    }

	@Override
	public void giveUser(String userID) throws RemoteException {
		userId = userID;
		
	}
}
