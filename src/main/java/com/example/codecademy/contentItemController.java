package com.example.codecademy;

import com.example.codecademy.Domain.*;
import com.example.codecademy.Domain.Module;
import com.example.codecademy.Repository.*;
import com.example.codecademy.Repository.CourseRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class contentItemController implements Initializable {
    @FXML
    private ComboBox courses = new ComboBox();
    @FXML
    private ComboBox students = new ComboBox();
    @FXML
    private Stage stage;
    @FXML
    private TableView<Module> modules = new TableView<>();
    @FXML
    private TableView<Webcast> webcasts = new TableView<>();
    @FXML
    private TableColumn mTitle = new TableColumn();
    @FXML
    private TableColumn mVersion = new TableColumn();
    @FXML
    private TableColumn mDescription = new TableColumn();
    @FXML
    private TableColumn mEmailContactPerson = new TableColumn();
    @FXML
    private TableColumn mPercentage = new TableColumn();
    @FXML
    private TableColumn wTitle = new TableColumn();
    @FXML
    private TableColumn wURL = new TableColumn();
    @FXML
    private TableColumn wDate = new TableColumn();
    @FXML
    private TableColumn wNameSpeaker = new TableColumn();
    @FXML
    private TableColumn wOrganisation = new TableColumn();
    @FXML
    private TableColumn wDescription = new TableColumn();
    @FXML
    private TableColumn wPercentage = new TableColumn();
    ArrayList<Course> courseList;
    ArrayList<Student> studentList;

    ArrayList<String> courseStrings = new ArrayList<>();
    ArrayList<String> studentStrings = new ArrayList<>();


    ObservableList<String> oListCourses;
    ObservableList<String> oListStudent;


    @FXML
    //deze methode stuurt je terug naar het hoofd menu
    protected void toMain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(courseController.class.getResource("main-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        stage.setScene(scene);
        stage.show();
    }
    public void showTopViewed(){
        mTitle.setCellValueFactory(new PropertyValueFactory<Module, String>("title"));
        mVersion.setCellValueFactory(new PropertyValueFactory<Module, String>("version"));
        mDescription.setCellValueFactory(new PropertyValueFactory<Module, String>("description"));
        mEmailContactPerson.setCellValueFactory(new PropertyValueFactory<Module, String>("emailContactPerson"));
        mPercentage.setCellValueFactory(new PropertyValueFactory<Module, String>("percentage"));



        wTitle.setCellValueFactory(new PropertyValueFactory<Webcast, String>("title"));
        wURL.setCellValueFactory(new PropertyValueFactory<Webcast, String>("URL"));
        wDate.setCellValueFactory(new PropertyValueFactory<Webcast, String>("date"));
        wNameSpeaker.setCellValueFactory(new PropertyValueFactory<Webcast, String>("nameSpeaker"));
        wOrganisation.setCellValueFactory(new PropertyValueFactory<Webcast, String>("organisation"));
        wDescription.setCellValueFactory(new PropertyValueFactory<Webcast, String>("description"));
        wPercentage.setCellValueFactory(new PropertyValueFactory<Webcast, String>("percentage"));

        ArrayList<ContentItemWatchCount> contentItemWatchCounts = ContentItemProgressRepository.getContentItemsWatchCount();
        ArrayList<Webcast> topViewedW = new ArrayList<>();
        ArrayList<Module> topViewedM = new ArrayList<>();
        for (ContentItemWatchCount contentItemWatchCount : contentItemWatchCounts) {

            ContentItem contentItem = ContentItemRepository.getContentItemsByID(contentItemWatchCount.getContentItemID());
            if(contentItem.getWebcastID() != null){
                topViewedW.add(WebcastRepository.getWebcastByID(contentItem.getWebcastID()));
            }
            if(contentItem.getModuleID() != null){
                topViewedM.add(ModuleRepository.getModuleByID(contentItem.getModuleID()));
            }
        }
        while(topViewedW.size() > 3){
            topViewedW.remove(topViewedW.size()-1);
        }
        while(topViewedM.size() > 3){
            topViewedM.remove(topViewedM.size()-1);
        }

        modules.setItems(FXCollections.observableArrayList(topViewedM));
        webcasts.setItems(FXCollections.observableArrayList(topViewedW));
    }

    public void searchContentItems(){
        String selectedCourse = (String) courses.getSelectionModel().getSelectedItem();
        String selectedStudent = (String) students.getSelectionModel().getSelectedItem();


        mTitle.setCellValueFactory(new PropertyValueFactory<Module, String>("title"));
        mVersion.setCellValueFactory(new PropertyValueFactory<Module, String>("version"));
        mDescription.setCellValueFactory(new PropertyValueFactory<Module, String>("description"));
        mEmailContactPerson.setCellValueFactory(new PropertyValueFactory<Module, String>("emailContactPerson"));
        mPercentage.setCellValueFactory(new PropertyValueFactory<Module, String>("percentage"));



        wTitle.setCellValueFactory(new PropertyValueFactory<Webcast, String>("title"));
        wURL.setCellValueFactory(new PropertyValueFactory<Webcast, String>("URL"));
        wDate.setCellValueFactory(new PropertyValueFactory<Webcast, String>("date"));
        wNameSpeaker.setCellValueFactory(new PropertyValueFactory<Webcast, String>("nameSpeaker"));
        wOrganisation.setCellValueFactory(new PropertyValueFactory<Webcast, String>("organisation"));
        wDescription.setCellValueFactory(new PropertyValueFactory<Webcast, String>("description"));
        wPercentage.setCellValueFactory(new PropertyValueFactory<Webcast, String>("percentage"));
        if(selectedStudent == null && selectedCourse != null) {
            modules.setItems(FXCollections.observableArrayList(averageModuleProgress(selectedCourse)));
            webcasts.setItems(FXCollections.observableArrayList(averageWebcastProgress(selectedCourse)));
        }else if(selectedStudent != null && selectedCourse == null){
            studentContentItemByEmail(selectedStudent);
        }else if(selectedStudent != null && selectedCourse != null){
            studentContentItemByEmailAndCourse(selectedStudent, selectedCourse);
        }
    }

    private ArrayList<Module> averageModuleProgress(String selectedCourse) {
        ArrayList<Module> modulesList = new ArrayList<>();
        ArrayList<ContentItem> contentItems = ContentItemRepository.getContentItemsByCourse(selectedCourse);
        for (ContentItem contentItem : contentItems) {
            if (contentItem.getModuleID() != null) {
                Module module = ModuleRepository.getModuleByID(contentItem.getModuleID());
                if (module != null) {
                    ArrayList<ContentItemProgress> contentItemProgresses = ContentItemProgressRepository.getContentItemProgressByContentItemID(contentItem.getContentItemID());
                    int count = RegistrationRepository.getNumberOfRegistrationsByCourse(selectedCourse);
                    int percentage = 0;
                    for (ContentItemProgress contentItemProgress : contentItemProgresses) {
                        percentage += contentItemProgress.getPercentage();
                    }
                    if (percentage != 0 && count != 0) {
                        percentage = percentage / count;
                        module.setPercentage(percentage);
                    } else {
                        if (count == 0) {
                            module.setPercentage(null);
                        } else {
                            module.setPercentage(0);

                        }
                    }

                    modulesList.add(module);
                }
            }
        }
        return modulesList;
    }
    private ArrayList<Webcast> averageWebcastProgress(String selectedCourse) {
        ArrayList<Webcast> webcastsList = new ArrayList<>();
        ArrayList<ContentItem> contentItems = ContentItemRepository.getContentItemsByCourse(selectedCourse);
        for (ContentItem contentItem : contentItems) {
        if(contentItem.getWebcastID() != null) {
            Webcast webcast = WebcastRepository.getWebcastByID(contentItem.getWebcastID());
            if (webcast != null) {
                ArrayList<ContentItemProgress> contentItemProgresses = ContentItemProgressRepository.getContentItemProgressByContentItemID(contentItem.getWebcastID());
                int count = RegistrationRepository.getNumberOfRegistrationsByCourse(selectedCourse);
                int percentage = 0;
                for (ContentItemProgress contentItemProgress : contentItemProgresses) {
                    percentage += contentItemProgress.getPercentage();
                }
                if (percentage != 0 && count != 0) {
                    percentage = percentage / count;
                    webcast.setPercentage(percentage);
                } else {
                    if (count == 0) {
                        webcast.setPercentage(null);
                    } else {
                        webcast.setPercentage(0);
                    }
                }
                webcastsList.add(webcast);
            }
        }
        }
        return webcastsList;
    }
    private void studentContentItemByEmail(String selectedStudent){
        ArrayList<Module> modulesList = new ArrayList<>();
        ArrayList<Webcast> webcastList = new ArrayList<>();
        ArrayList<Registration> registrations = RegistrationRepository.getRegistrationsByEmail(selectedStudent);
        ArrayList<Course> courses = new ArrayList<>();
        for (Registration registration : registrations) {
            Course course = new Course();
            course.setCourseName(registration.getCourseName());
            if(!courses.contains(course)){
                courses.add(CourseRepository.getCourseByCourseName(registration.getCourseName()));
            }
        }
        for (Course course : courses) {
            for (ContentItem contentItem : ContentItemRepository.getContentItemsByCourse(course.getCourseName())) {
              if(contentItem.getWebcastID() != null){
                  Webcast webcast = WebcastRepository.getWebcastByID(contentItem.getWebcastID());
                  ContentItemProgress contentItemProgress = ContentItemProgressRepository.getContentItemProgressByContentItemIDAndStudentEmail(contentItem.getContentItemID(), selectedStudent);
                  if(contentItemProgress == null){
                      webcast.setPercentage(0);
                  }else{
                      webcast.setPercentage(contentItemProgress.getPercentage());
                  }
                  webcastList.add(webcast);
              }
              if(contentItem.getModuleID() != null){
                  Module module = ModuleRepository.getModuleByID(contentItem.getModuleID());
                  ContentItemProgress contentItemProgress = ContentItemProgressRepository.getContentItemProgressByContentItemIDAndStudentEmail(contentItem.getContentItemID(), selectedStudent);
                  if(contentItemProgress == null){
                      module.setPercentage(0);
                  }else{
                      module.setPercentage(contentItemProgress.getPercentage());
                  }
                  modulesList.add(module);
              }
            }
        }
        modules.setItems(FXCollections.observableArrayList(modulesList));
        webcasts.setItems(FXCollections.observableArrayList(webcastList));



    }

    private void studentContentItemByEmailAndCourse(String selectedStudent, String selectedCourse){
        ArrayList<Module> modulesList = new ArrayList<>();
        ArrayList<Webcast> webcastList = new ArrayList<>();
        Registration registration = RegistrationRepository.getRegistrationsByEmailAndCourse(selectedStudent, selectedCourse);
        if(registration != null){
            for (ContentItem contentItem : ContentItemRepository.getContentItemsByCourse(selectedCourse)) {
                if(contentItem.getWebcastID() != null){
                    Webcast webcast = WebcastRepository.getWebcastByID(contentItem.getWebcastID());
                    ContentItemProgress contentItemProgress = ContentItemProgressRepository.getContentItemProgressByContentItemIDAndStudentEmail(contentItem.getContentItemID(), selectedStudent);
                    if(contentItemProgress == null){
                        webcast.setPercentage(0);
                    }else{
                        webcast.setPercentage(contentItemProgress.getPercentage());
                    }
                    webcastList.add(webcast);
                }
                if(contentItem.getModuleID() != null){
                    Module module = ModuleRepository.getModuleByID(contentItem.getModuleID());
                    ContentItemProgress contentItemProgress = ContentItemProgressRepository.getContentItemProgressByContentItemIDAndStudentEmail(contentItem.getContentItemID(), selectedStudent);
                    if(contentItemProgress == null){
                        module.setPercentage(0);
                    }else{
                        module.setPercentage(contentItemProgress.getPercentage());
                    }
                    modulesList.add(module);
                }
            }


        }
        modules.setItems(FXCollections.observableArrayList(modulesList));
        webcasts.setItems(FXCollections.observableArrayList(webcastList));
    }
    public void clearContentItems(){
        courses.valueProperty().set(null);
        students.valueProperty().set(null);
        modules.setItems(null);
        webcasts.setItems(null);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseList = CourseRepository.getCourses();
        if(courses != null){
            courseList.forEach(course -> {
                courseStrings.add(course.getCourseName());
            });
            oListCourses = FXCollections.observableArrayList(courseStrings);
            courses.setItems(oListCourses);
        }

        studentList = StudentRepository.getStudents();
        if(students != null){
            studentList.forEach(student -> {
                studentStrings.add(student.getEmailAddress());
            });
            oListStudent = FXCollections.observableArrayList(studentStrings);
            students.setItems(oListStudent);
        }
    }
}
