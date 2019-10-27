package no.ticket.Controller;

import com.sun.tools.javac.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import no.ticket.Data.ServiceStubs;
import no.ticket.MainJavaFX;
import no.ticket.Model.Manager;
import no.ticket.Model.Person;
import no.ticket.Model.User;

public class LoginController {
    @FXML
    public Button adminButton;
    @FXML
    public Button userButton;
    @FXML
    public Label messageLabel;
    @FXML
    public ComboBox<Manager> comboAdmin;
    @FXML
    public ComboBox<User> comboUser;


    private ObservableList<Manager> adminList = FXCollections.observableArrayList();
    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ServiceStubs database;

    public void initialize() {

         database = new ServiceStubs();


        adminList.addAll(database.getAdmins());
        userList.addAll(database.getUsers());


        adminButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onClick(true);
            }
        });
        userButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onClick(false);
            }
        });



        comboAdmin.setItems(adminList);
        comboUser.setItems(userList);
        comboAdmin.getSelectionModel().select(0);
        comboUser.getSelectionModel().select(0);


    }

    public void guestButtonClicked(javafx.event.ActionEvent actionEvent) {

    }



    public void onClick(boolean ifAdmin){
        //her blir verdien som ble tastet inn sendt videre om den matcher med ID-en til en manager
            if (ifAdmin) {
                for (Manager admin : database.getAdmins()) {
                    if (Integer.toString(admin.getId()).equals(Integer.toString(comboAdmin.getValue().getId()))){
                        System.out.println(comboAdmin.getValue().getName());
                        MainJavaFX.setCurrentPerson(admin, true);
                        MainJavaFX.getInstance().setHovedLayout();
                    } else {
                        messageLabel.setText("Wrong admin ID!");
                    }
                }
            } else {
                for (User guest : database.getUsers()) {
                    if (Integer.valueOf(guest.getId()).equals(comboUser.getValue().getId())){
                        System.out.println(comboUser.getValue().getName());
                        MainJavaFX.setCurrentPerson(guest, false);
                        MainJavaFX.getInstance().setHovedLayout();
                    } else {
                        messageLabel.setText("Wrong user ID!");
                    }
                }
            }
        }
    }
