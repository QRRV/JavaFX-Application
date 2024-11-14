package com.example.codecademy;

import com.example.codecademy.Domain.Course;
import com.example.codecademy.Domain.Student;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class studentController extends StudentRepository implements Initializable {
    @FXML
    Stage stage;
    @FXML
    private TextField email;
    @FXML
    private TextField name;
    @FXML
    private TextField birthday;
    @FXML
    private ComboBox gender;
    @FXML
    private ComboBox studentBox;
    @FXML
    private TextField newName;
    @FXML
    private TextField newEmail;
    @FXML
    private TextField newBirthday;
    @FXML
    private ComboBox newGender;
    @FXML
    private TableView<Student> tableView = new TableView<Student>();
    @FXML
    private TableColumn eMail = new TableColumn<>();
    @FXML
    private TableColumn Name = new TableColumn<>();
    @FXML
    private TableColumn Birthday =new TableColumn<>();
    @FXML
    private TableColumn Gender = new TableColumn<>();

    ObservableList oListStudents;
    ArrayList<String> items = new ArrayList<String>();
    ArrayList<Student> selItems;
    ObservableList<String> oListName;
    ArrayList<String> names = new ArrayList<String>();
    String item;
    String[] Item;
    String[] mailAddress;
    String[] selName;
    String[] selEmail;

    @FXML
    //deze methode stuurt je naar het hoofdmenu
    protected void toMain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(studentController.class.getResource("main-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    //deze methode stuurt je naar de hoofdpagina van student
    protected void toStudent(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(studentController.class.getResource("Student/student-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    //deze methode stuurt je naar de create pagina van student
    protected void toCreate(ActionEvent event) throws IOException{
            FXMLLoader fxmlLoader = new FXMLLoader(studentController.class.getResource("Student/studentCreate-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 511, 600);
            stage.setScene(scene);
            stage.show();

    }


    @FXML
    //deze methode stuurt je naar de update pagina van student
    protected void toUpdate(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(studentController.class.getResource("Student/studentUpdate-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 511, 600);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    //deze methode haalt data op uit de database en zet deze in een tabel
    protected void getList() throws SQLException {
        ArrayList<Student> students = StudentRepository.getStudents();
        tableView.getItems().clear();
        oListStudents = tableView.getItems();
        students.forEach(student -> {
            oListStudents.add(student);
        });
        eMail.setCellValueFactory(new PropertyValueFactory<Course,String>("emailAddress"));
        Name.setCellValueFactory(new PropertyValueFactory<Course,String>("name"));
        Birthday.setCellValueFactory(new PropertyValueFactory<Course,String>("birthDay"));
        Gender.setCellValueFactory(new PropertyValueFactory<Course,String>("gender"));

        tableView.setItems(oListStudents);
    }

    @FXML
    //deze methode maakt een nieuwe student aan
    protected void createStudent(ActionEvent event) throws IOException {
        if(Validation.validateEmail(email.getText())){
            if(Validation.validateDateOfBirth(birthday.getText())){
                StudentRepository.createStudent(email.getText(), name.getText(), birthday.getText(), (String) gender.getSelectionModel().getSelectedItem());
                FXMLLoader fxmlLoader = new FXMLLoader(studentController.class.getResource("Student/student-view.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                stage.setScene(scene);
                stage.show();
            }else{
                Validation.showAlert(Alert.AlertType.ERROR, "Form Error!", "Please add a valid date");
            }
        }else{
            Validation.showAlert(Alert.AlertType.ERROR, "Form Error!", "Please add a valid email");
        }
    }

    @FXML
    //deze methode past een bestaande student aan
    protected void updateStudent(ActionEvent event) throws IOException {

        if(Validation.validateEmail(newEmail.getText())){
            if (Validation.validateDateOfBirth(newBirthday.getText())){
                StudentRepository.updateStudent((String) studentBox.getSelectionModel().getSelectedItem(), newEmail.getText(), newName.getText(), newBirthday.getText(), (String) newGender.getSelectionModel().getSelectedItem());

                FXMLLoader fxmlLoader = new FXMLLoader(studentController.class.getResource("Student/student-view.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                stage.setScene(scene);
                stage.show();
            }else{
                Validation.showAlert(Alert.AlertType.ERROR, "Form Error!", "Please add a valid date");
            }
        }else{
            Validation.showAlert(Alert.AlertType.ERROR, "Form Error!", "Please add a valid email");
        }

    }

    @FXML
    //deze methode verwijderd de meegegeven student
    protected void deleteStudent() throws SQLException {
        StudentRepository.deleteStudent(mailAddress[1]);
        tableView.getItems().clear();
        getList();
    }

    @Override
    //deze methode voert bij het opstarten van deze controller de code uit
    public void initialize(URL arg0, ResourceBundle arg1) {

        try {
            getList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue<? extends Student> observable, Student oldValue, Student newValue) {
                item = String.valueOf(tableView.getSelectionModel().getSelectedItem());
                Item = item.split(" ");
                mailAddress = Item[0].split("'");
                System.out.println(mailAddress[1]);
            }
        });
        selItems = StudentRepository.getStudents();
        if(studentBox != null){
            selItems.forEach(student -> {
                items.add(student.toString());
            });
            items.forEach(item ->{
                selName = item.split(" ");
                selEmail = selName[0].split("'");
                names.add(selEmail[1]);
            });
            oListName = FXCollections.observableArrayList(names);
            studentBox.setItems(oListName);
        }
    }
}
