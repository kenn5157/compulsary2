package view;

import be.Student;
import bll.LogicManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherController implements Initializable {

    LogicManager logicManager;

    public TableView tableView;
    public PieChart pieChart;

    private int absence = 0;
    private int presence = 0;

    private ObservableList<PieChart.Data> pieChartData;

    public TeacherController(){
        logicManager = new LogicManager();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pieChartData = FXCollections.observableArrayList();
        pieChart.setData(pieChartData);

        try {
            tableView.setEditable(true);
            tableView.setItems(logicManager.getAllStudents());
        } catch (Exception e) {
            e.printStackTrace();
        }

        TableColumn<Student, String> fname = new TableColumn("First Name");
        fname.prefWidthProperty().bind(tableView.widthProperty().multiply(0.45));
        fname.setCellValueFactory(new PropertyValueFactory<>("fname"));

        TableColumn<Student, String> lname = new TableColumn("Last Name");
        lname.prefWidthProperty().bind(tableView.widthProperty().multiply(0.45));
        lname.setCellValueFactory(new PropertyValueFactory<>("lname"));

        tableView.getColumns().addAll(fname, lname);
    }

    public void getSelectedPerson(MouseEvent mouseEvent) {
        try {
            Student student = (Student) tableView.getSelectionModel().getSelectedItem();
            String fname = student.getFname();
            String lname = student.getLname();

            int userID = logicManager.getUserID(fname,lname);

            absence = logicManager.getAbsence(userID);
            presence = logicManager.getPresence(userID);

            addData("Remaining", absence + presence - 200);
            addData("Absence", absence);
            addData("Presence", presence);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void naiveAddData(String name, int value){
        pieChartData.add(new PieChart.Data(name, value));
    }

    private void addData(String name, int value){
        for(PieChart.Data data : pieChartData){
            if (data.getName().equals(name)){
                data.setPieValue(value);
                return;
            }
        }
        naiveAddData(name, value);
    }
}
