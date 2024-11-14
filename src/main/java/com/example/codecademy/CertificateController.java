package com.example.codecademy;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.codecademy.Domain.Certificate;
import com.example.codecademy.Domain.Student;
import com.example.codecademy.Repository.CertificateRepository;
import com.example.codecademy.Repository.StudentRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CertificateController extends CertificateRepository implements Initializable {
    @FXML
    private ComboBox cID = new ComboBox<>();
    @FXML
    private ComboBox eMail = new ComboBox<>();
    @FXML
    private TextField grd;
    @FXML
    private TextField nameEmp;
    @FXML
    private Stage stage;
    @FXML
    private TableColumn csID = new TableColumn<>();
    @FXML
    private TableColumn csGrade = new TableColumn<>();
    @FXML
    private TableColumn csNameEmployer = new TableColumn<>();
    @FXML
    private TableColumn certificateID = new TableColumn<>();
    @FXML
    private TableColumn grade = new TableColumn<>();
    @FXML
    private TableColumn nameEmployer = new TableColumn<>();

    String item;
    String[] certId;

    ArrayList<String> certifacates = new ArrayList<>();
    ArrayList<String> mails = new ArrayList<>();
    String[] part;
    String[] name;
    String[] splits;
    ArrayList<String> nums=new ArrayList<>();
    ArrayList<String> emails = new ArrayList<>();
    ObservableList<Certificate> oListCertificate;
    @FXML
    private TableView<Certificate> tableView = new TableView<Certificate>();
    @FXML
    private TableView<Certificate> zoekView = new TableView<Certificate>();

    @FXML
    //door deze methode wordt er een nieuwe certificate aangemaakt en wordt je terug gestuurd naar de hoofdpagina van certificate
    protected void createCertificate(ActionEvent event) throws IOException{
        if(Validation.validateCertificaatCijfer(Integer.parseInt(grd.getText()))) {
                try {
                    CertificateRepository.createCertificate(Integer.parseInt(grd.getText()), nameEmp.getText());
                } catch (Exception ex) {
                    System.out.println("Problem occurred at createCourse operation : " + ex);
                }

                FXMLLoader fxmlLoader = new FXMLLoader(CertificateController.class.getResource("Certificate/Certificate-View.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                stage.setScene(scene);
                stage.show();
            }
        else{
            Validation.showAlert(Alert.AlertType.ERROR, "Form Error!", "Please add a valid grade");
        }
    }
    @FXML
    //door deze methode wordt er een bestaande certificate aangepast en wordt je terug gestuurd naar de hoofdpagina van certificate
    protected void updateCertificate(ActionEvent event) throws  SQLException, IOException{
        if(cID.getSelectionModel().getSelectedItem() == null || cID.getSelectionModel().getSelectedItem() == ""){
            System.out.println("Certificate ID is verplicht");
        }else {
            try {
                CertificateRepository.updateCertificate((Integer) cID.getSelectionModel().getSelectedItem(), Integer.parseInt((grd.getText())), nameEmp.getText());
            } catch (Exception ex) {
                System.out.println("Problem occurred at createCourse operation : " + ex);
            }

            FXMLLoader fxmlLoader = new FXMLLoader(CertificateController.class.getResource("Certificate/Certificate-View.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    //deze methode haalt alle certificates op van de meegegeven emailadres en zet de certificates in de tabel
    protected void Search(){
        ArrayList<Certificate> certificates = CertificateRepository.getCertificatesById((String) eMail.getSelectionModel().getSelectedItem());
        zoekView.getItems().clear();
        csID.setCellValueFactory(new PropertyValueFactory<Certificate, Integer>("certificateId"));
        csGrade.setCellValueFactory(new PropertyValueFactory<Certificate, Integer>("grade"));
        csNameEmployer.setCellValueFactory(new PropertyValueFactory<Certificate, String>("nameEmployer"));
        zoekView.setItems(FXCollections.observableArrayList(certificates));
    }
    @FXML
    //deze methode stuurt de gebruiker naar de search pagina van alle certificates van een specifiek account
    protected void toSearch(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("Certificate/Certificate-search.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    //deze methode stuurt je naar de update pagina om een certificate bij te werken
    protected void toUpdate(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(courseController.class.getResource("Certificate/CertificateUpdate-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 511, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    //deze methode stuurt je naar de create pagina om een nieuwe certificate te maken
    protected void toCreate(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(courseController.class.getResource("Certificate/CertificateCreate-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 511, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    //deze methode verwijderd de meegegeven certificate
    protected void deleteCertificate() throws SQLException{
        CertificateRepository.deleteCertificate(Integer.parseInt(certId[0]));
        tableView.getItems().clear();
        getList();
    }
    @FXML
    //deze methode stuurt je terug naar het hoofd menu
    protected void toMain(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(courseController.class.getResource("main-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    //deze methode stuurt je terug naar de hoofdpagina van certificate
    protected void BackToCertifficate(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(courseController.class.getResource("Certificate/Certificate-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    //deze methode haalt data op uit de database en zet deze in een tabel
    protected void getList() throws SQLException{
        ArrayList<Certificate> certificates = CertificateRepository.getCertificates();
        tableView.getItems().clear();
        oListCertificate = tableView.getItems();
        certificates.forEach(certificate -> {
            oListCertificate.add(certificate);
        });
        certificateID.setCellValueFactory(new PropertyValueFactory<Certificate, Integer>("certificateId"));
        grade.setCellValueFactory(new PropertyValueFactory<Certificate, Integer>("grade"));
        nameEmployer.setCellValueFactory(new PropertyValueFactory<Certificate, String>("nameEmployer"));
        tableView.setItems(oListCertificate);
    }

    @Override
    //deze methode voert bij het opstarten van deze controller de code uit
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            getList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Certificate>() {
            @Override
            public void changed(ObservableValue<? extends Certificate> observable, Certificate oldValue, Certificate newValue) {
                item = String.valueOf(tableView.getSelectionModel().getSelectedItem());
                certId = item.split(" ");
            }
        });
        if(cID != null){
            ArrayList<Certificate> certs = CertificateRepository.getCertificates();
            certs.forEach(cert ->{
                certifacates.add(cert.toString());
            });
            certifacates.forEach(cert ->{
                part = cert.split(" ");
                nums.add(part[0]);
            });

            cID.setItems(FXCollections.observableArrayList(nums));
        }
        if(eMail != null){
            ArrayList<Student> persons = StudentRepository.getStudents();
            persons.forEach(person ->{
                mails.add(person.toString());
            });
            mails.forEach(mail ->{
                splits = mail.split(" ");
                name = splits[0].split("'");
                emails.add(name[1]);
            });

            eMail.setItems(FXCollections.observableArrayList(emails));
        }
    }
}
