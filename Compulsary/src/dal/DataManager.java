package dal;

import be.Attendance;
import be.Student;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataManager {

    private final DatabaseConnector databaseConnector;

    public DataManager() {
        databaseConnector = new DatabaseConnector();
    }

    public ArrayList<Student> getAllStudents() throws Exception{
        ArrayList<Student> allStudents = new ArrayList<>();
        try (Connection connection = databaseConnector.getConnection()){
            String sql = "SELECT * FROM mock_data_users WHERE status=(1) ORDER BY absence DESC";

            Statement statement = connection.createStatement();
            if(statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    int status = resultSet.getInt("status");
                    String fname = resultSet.getString("first_name");
                    String lname = resultSet.getString("last_name");
                    String passwd = resultSet.getString("password");
                    int absence = resultSet.getInt("absence");
                    int precence = resultSet.getInt("precence");

                    Student student = new Student(id, status, fname, lname, passwd, absence, precence);
                    allStudents.add(student);
                }
            }
        }
        return allStudents;
    }

    public int getUserStatus(String username, String passwd) throws Exception{
        int id = 0;
        try (Connection connection = databaseConnector.getConnection()){
            String sql = "SELECT status FROM mock_data_users WHERE first_name=('" + username + "') AND [password]=('" + passwd + "');";

            Statement statement = connection.createStatement();
            if (statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    id = resultSet.getInt("status");
                }
            }
        }
        return id;
    }

    public int getUserNameID(String username, String passwd) throws Exception{
        int id = 0;
        try (Connection connection = databaseConnector.getConnection()){
            String sql = "SELECT id FROM mock_data_users WHERE first_name=('" + username + "') AND [password]=('" + passwd + "');";

            Statement statement = connection.createStatement();
            if (statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    id = resultSet.getInt("id");
                    System.out.println(id);
                }
            }
        }
        return id;
    }

    public int getUserID(String fname, String lname) throws Exception{
        int id = 0;
        try (Connection connection = databaseConnector.getConnection()){
            String sql = "SELECT id FROM mock_data_users WHERE first_name=('" + fname + "') AND last_name=('" + lname + "');";

            Statement statement = connection.createStatement();
            if (statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    id = resultSet.getInt("id");
                    System.out.println(id);
                }
            }
        }
        return id;
    }

    public String getFullName(String username, String passwd) throws Exception {
        String fullName = "";
        try (Connection connection = databaseConnector.getConnection()){
            String sql = "SELECT first_name,last_name FROM mock_data_users WHERE first_name=('" + username + "') AND [password]=('" + passwd + "');";

            Statement statement = connection.createStatement();
            if (statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    String fname = resultSet.getString("first_name");
                    String lname = resultSet.getString("last_name");

                    fullName = fname + " " + lname;
                }
            }
        }
        return fullName;
    }

    public int getPresence(int userID) throws Exception {
        int absence = 0;
        try (Connection connection = databaseConnector.getConnection()){
            String sql = "SELECT precence FROM mock_data_users WHERE id=('" + userID + "')";

            Statement statement = connection.createStatement();
            if (statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    absence = resultSet.getInt("precence");
                }
            }
        }
        return absence;
    }

    public int getAbsence(int userID) throws Exception {
        int presence = 0;
        try (Connection connection = databaseConnector.getConnection()){
            String sql = "SELECT absence FROM mock_data_users WHERE id=('" + userID + "')";

            Statement statement = connection.createStatement();
            if (statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    presence = resultSet.getInt("absence");
                }
            }
        }
        return presence;
    }

    public ArrayList<Attendance> getAttendance(int userID) throws Exception {
        ArrayList<Attendance> attendances = new ArrayList<>();
        try (Connection connection = databaseConnector.getConnection()){
            String sql = "SELECT precence,absence FROM mock_data_users WHERE id=('" + userID + "')";

            Statement statement = connection.createStatement();
            if (statement.execute(sql)){
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()){
                    int presence = resultSet.getInt("precence");
                    int absence = resultSet.getInt("absence");

                    Attendance attendance = new Attendance(presence,absence);
                    attendances.add(attendance);
                }
            }
        }
        return attendances;
    }

    public void addPresence(int userID) throws Exception {
        int preAdd = getPresence(userID);
        int afterAdd = preAdd + 1;

        try (Connection connection = databaseConnector.getConnection()){
            String sql = "UPDATE mock_data_users SET [precence] =(" + afterAdd + ") WHERE id=(" + userID + ");";

            Statement statement = connection.createStatement();
            statement.execute(sql);
        }
    }

    public void removePresence(int userID) throws Exception {
        int preAdd = getPresence(userID);
        int afterAdd = preAdd - 1;

        try (Connection connection = databaseConnector.getConnection()){
            String sql = "UPDATE mock_data_users SET [precence] =(" + afterAdd + ") WHERE id=(" + userID + ");";

            Statement statement = connection.createStatement();
            statement.execute(sql);
        }
    }

    public void addAbsence(int userID) throws Exception {
        int preAdd = getAbsence(userID);
        int afterAdd = preAdd + 1;

        try (Connection connection = databaseConnector.getConnection()){
            String sql = "UPDATE mock_data_users SET [absence] =(" + afterAdd + ") WHERE id=(" + userID + ");";

            Statement statement = connection.createStatement();
            statement.execute(sql);
        }
    }

    public void removeAbsence(int userID) throws Exception {
        int preAdd = getAbsence(userID);
        int afterAdd = preAdd - 1;

        try (Connection connection = databaseConnector.getConnection()){
            String sql = "UPDATE mock_data_users SET [absence] =(" + afterAdd + ") WHERE id=(" + userID + ");";

            Statement statement = connection.createStatement();
            statement.execute(sql);
        }
    }
}
