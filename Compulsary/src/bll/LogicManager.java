package bll;

import be.Attendance;
import be.Student;
import dal.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public class LogicManager {

    private DataManager dataManager;

    private ObservableList<Attendance> attendances;
    private ObservableList<Student> allStudents;

    public LogicManager(){
        dataManager = new DataManager();
    }

    public int getUserStatus(String username, String passwd) throws Exception {
        int status = dataManager.getUserStatus(username, passwd);
        return status;
    }

    public int getUserID(String fname, String lname) throws Exception {
        int id = dataManager.getUserID(fname,lname);
        return id;
    }

    public int getUserNameID(String username, String password) throws Exception {
        int id = dataManager.getUserNameID(username,password);
        return id;
    }

    public String getFullName(String username, String passwd) throws Exception {
        String fullName = dataManager.getFullName(username, passwd);
        return fullName;
    }


    public int getAbsence(int userID) throws Exception {
        int absence = dataManager.getAbsence(userID);
        return absence;
    }

    public int getPresence(int userID) throws Exception {
        int presence = dataManager.getPresence(userID);
        return presence;
    }

    public ObservableList getAttendance(int userID) throws Exception {
        attendances = FXCollections.observableArrayList();
        attendances.addAll(dataManager.getAttendance(userID));
        return attendances;
    }

    public ObservableList getAllStudents() throws Exception {
        allStudents = FXCollections.observableArrayList();
        allStudents.addAll(dataManager.getAllStudents());
        return allStudents;
    }

    public void addPresence(int userID) throws Exception {
        dataManager.addPresence(userID);
    }

    public void removePresence(int userID) throws Exception {
        dataManager.removePresence(userID);
    }

    public void addAbsence(int userID) throws Exception {
        dataManager.addAbsence(userID);
    }

    public void removeAbsence(int userID) throws Exception {
        dataManager.removeAbsence(userID);
    }
}
