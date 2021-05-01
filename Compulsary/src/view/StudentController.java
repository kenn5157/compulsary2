package view;

import bll.ConfigMan;
import bll.LogicManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

public class StudentController implements Initializable {

    @FXML private PieChart pieChart;

    private ConfigMan configMan;
    private LogicManager logicManager;

    private int userID = 0;

    private int absence = 0;
    private int presence = 0;

    private ObservableList<PieChart.Data> pieChartData;

    public StudentController(){
        configMan = new ConfigMan();
        logicManager = new LogicManager();
        userID = configMan.loggedInUserID;
    }

    @FXML private void addPresence(javafx.event.ActionEvent actionEvent) throws Exception {
        logicManager.addPresence(userID);
        updatePie();
    }

    @FXML private void removePresence(javafx.event.ActionEvent actionEvent) throws Exception {
        logicManager.removePresence(userID);
        updatePie();
    }

    @FXML private void addAbsence(javafx.event.ActionEvent actionEvent) throws Exception {
        logicManager.addAbsence(userID);
        updatePie();
    }

    @FXML private void removeAbsence(javafx.event.ActionEvent actionEvent) throws Exception {
        logicManager.removeAbsence(userID);
        updatePie();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pieChartData = FXCollections.observableArrayList();
        pieChart.setData(pieChartData);

        int userID = configMan.loggedInUserID;
        try {
            absence = logicManager.getAbsence(userID);
            presence = logicManager.getPresence(userID);

            addData("Presence", presence);
            addData("Absence", absence);
            addData("Remaining", absence + presence - 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updatePie(){
        int userID = configMan.loggedInUserID;
        try {
            absence = logicManager.getAbsence(userID);
            presence = logicManager.getPresence(userID);

            addData("Presence", presence);
            addData("Absence", absence);
            addData("Remaining", absence + presence - 200);
        } catch (Exception e){
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
