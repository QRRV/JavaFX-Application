package com.example.codecademy;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.codecademy.Domain.Certificate;
import com.example.codecademy.Domain.Registration;
import com.example.codecademy.Repository.CertificateRepository;
import com.example.codecademy.Repository.RegistrationRepository;
import com.example.codecademy.Repository.StudentRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    private Stage stage;

    @FXML
    private VBox buttonVBox;

    @FXML
    private Button bCourse;
    @FXML
    private Button bStudent;
    @FXML
    private Button bContentItem;
    @FXML
    private Button bRegistration;
    @FXML
    private Button bAddress;
    @FXML
    private Button bCertificate;
    @FXML
    private Text titleText;

    @FXML
    private ComboBox gender;
    @FXML
    private PieChart piechart;

    @FXML
    //met deze methode ga je naar de course pagina
    protected void toCourse(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(courseController.class.getResource("Course/course-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    //met deze methode ga je naar de student pagina
    protected void toStudent(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(courseController.class.getResource("Student/student-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    //met deze methode ga je naar de registration pagina
    protected void toRegistration(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(courseController.class.getResource("Registration/registration-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    //met deze methode ga je naar de adres pagina
    protected void toAddress(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(courseController.class.getResource("address-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1100, 600);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    //met deze methode ga je naar de certificate pagina
    protected void certificateBtn(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("Certificate/Certificate-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void toContentItem(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(courseController.class.getResource("ContentItem/contentItem-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1250, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonVBox.setStyle("-fx-background-color: #91959c");
        bCourse.setStyle("-fx-border-radius: 0");
        bCourse.setStyle("-fx-font-weight: bolder");
        bCourse.setStyle("-fx-border-color: black");


        bStudent.setStyle("-fx-border-radius: 0");
        bStudent.setStyle("-fx-font-weight: bolder");
        bStudent.setStyle("-fx-border-color: black");

        bContentItem.setStyle("-fx-border-radius: 0");
        bContentItem.setStyle("-fx-font-weight: bolder");
        bContentItem.setStyle("-fx-border-color: black");

        bRegistration.setStyle("-fx-border-radius: 0");
        bRegistration.setStyle("-fx-font-weight: bolder");
        bRegistration.setStyle("-fx-border-color: black");

        bAddress.setStyle("-fx-border-radius: 0");
        bAddress.setStyle("-fx-font-weight: bolder");
        bAddress.setStyle("-fx-border-color: black");

        bCertificate.setStyle("-fx-border-radius: 0");
        bCertificate.setStyle("-fx-font-weight: bolder");
        bCertificate.setStyle("-fx-border-color: black");

        titleText.setStyle("-fx-font-weight: bolder");
        titleText.setStyle("-fx-font-size: 20");

        gender.getSelectionModel().select("Man");
        int[] results = certificatePercentagePerGender("Man");
        int Achieved = results[1];
        int NotAchieved = results[0] - results[1];
        int percentageAchieved = (int) (((double)results[1] / (double)results[0]) * 100d);
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Certificaat Behaald: " + percentageAchieved + "%", Achieved),
                        new PieChart.Data("Certificaat Niet Behaald: " + (100 - percentageAchieved) + "%", NotAchieved));

        piechart.setData(pieChartData);
        gender.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            int[] newResults = certificatePercentagePerGender(newValue.toString());
            int newAchieved = newResults[1];
            int newNotAchieved = newResults[0] - newResults[1];
            int newPercentageAchieved = (int) (((double)newResults[1] / (double)newResults[0]) * 100d);
            ObservableList<PieChart.Data> pieChartData2 =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Certificaat Behaald: " + newPercentageAchieved + "%", newAchieved),
                            new PieChart.Data("Certificaat Niet Behaald: " + (100 - newPercentageAchieved), newNotAchieved));

            piechart.setData(pieChartData2);
        });


    }

    private int[] certificatePercentagePerGender(String gender){
        ArrayList<String> emails = new ArrayList<>();
        switch (gender){
            case "Man":
                    emails = StudentRepository.getStudentsEmailsByGender(gender);
                break;
            case "Vrouw":
                emails = StudentRepository.getStudentsEmailsByGender(gender);
                break;
            case "Overig":
                emails = StudentRepository.getStudentsEmailsByGender(gender);
                break;
        }
        int registrationCount = 0;
        int count = 0;
        for (String email : emails) {
            ArrayList<Registration> registrations = RegistrationRepository.getRegistrationsByEmail(email);
            for (Registration registration : registrations) {
                registrationCount ++;
                if(registration.getCertificateID() != 0){
                    count ++;
                }
            }
        }
        System.out.println(registrationCount + " " + count );
        int[] arr = {registrationCount, count};
        return arr;
    }
}
