package com.example.codecademy;

import com.example.codecademy.Domain.Address;
import com.example.codecademy.Repository.AddressRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class addressController implements Initializable {
    @FXML
    Stage stage;
    @FXML
    private TableView<Address> tableView = new TableView<>();
    @FXML
    private TableColumn eMail = new TableColumn<>();
    @FXML
    private TableColumn streetName = new TableColumn<>();
    @FXML
    private TableColumn houseNumber = new TableColumn<>();
    @FXML
    private TableColumn houseNumberAdd = new TableColumn<>();
    @FXML
    private TableColumn zipCode = new TableColumn<>();
    @FXML
    private TableColumn city = new TableColumn<>();
    @FXML
    private TableColumn country = new TableColumn<>();


    @FXML
    //Hierdoor gaan je terug naar het hoofdmenu
    protected void toMain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(addressController.class.getResource("main-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 600, 700);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    //hierdoor wordt bij het "opstarten" van deze controller deze code uitgevoerd, die data ophaald en ze in de tabel zet door de property van de address te koppelen aan een tablecolumn
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Address> addresses = AddressRepository.getAddresses();
        tableView.getItems().clear();

        eMail.setCellValueFactory(new PropertyValueFactory<Address,String>("emailAddress"));
        streetName.setCellValueFactory(new PropertyValueFactory<Address,String>("streetName"));
        houseNumber.setCellValueFactory(new PropertyValueFactory<Address,String>("houseNumber"));
        houseNumberAdd.setCellValueFactory(new PropertyValueFactory<Address,String>("houseNumberAdd"));
        zipCode.setCellValueFactory(new PropertyValueFactory<Address,String>("zipCode"));
        city.setCellValueFactory(new PropertyValueFactory<Address,String>("city"));
        country.setCellValueFactory(new PropertyValueFactory<Address,String>("country"));

        tableView.setItems(FXCollections.observableArrayList(addresses));
    }
}
