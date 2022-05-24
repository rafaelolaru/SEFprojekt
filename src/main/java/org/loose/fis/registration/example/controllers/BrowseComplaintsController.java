package org.loose.fis.registration.example.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.registration.example.model.User;
import org.loose.fis.registration.example.services.UserService;

public class BrowseComplaintsController extends LogInController{

    @FXML
    private ListView listField;
    @FXML
    private Text complaintField;


    @FXML
    public void initialize()
    {

        ObservableList<String> list= FXCollections.observableArrayList(UserService.getComplaints(selectedUser.getCode()));
        listField.setItems(list);

        listField.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                User client=UserService.checkUserFullName(listField.getSelectionModel().getSelectedItem().toString());
                complaintField.setText(client.getComplaints().getMessage());
            }
        });

    }
    @FXML
    public void handleGoBackAction(javafx.event.ActionEvent actionEvent){
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menu_admin.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}