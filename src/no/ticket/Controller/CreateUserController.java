package no.ticket.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import no.ticket.Data.ServiceStubs;
import no.ticket.MainJavaFX;
import no.ticket.Model.Manager;
import no.ticket.Model.User;

import java.time.LocalDate;


public class CreateUserController {

    @FXML
    private Button creatuserBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private RadioButton managerRadio;
    @FXML
    private RadioButton contestantRadio;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField manager;
    @FXML
    private TextField email;
    @FXML
    private TextField name;

    ToggleGroup radioGroup = new ToggleGroup();
    ServiceStubs database = MainJavaFX.database;


    public void initialize() {

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage primaryStage = MainJavaFX.primaryStage;
                MainJavaFX.getInstance().start(primaryStage);
                MainJavaFX.setCurrentPerson(null,false);
            }
        });

        managerRadio.setUserData("0");
        contestantRadio.setUserData("1");

        managerRadio.setToggleGroup(radioGroup);
        contestantRadio.setToggleGroup(radioGroup);


    }

    public void createUser(ActionEvent actionEvent) {
        if(name.getText().isEmpty() && email.getText().isEmpty()){
            System.out.println("Empty");


        } else {
            LocalDate birthDay = datePicker.getValue();
            switch (radioGroup.getSelectedToggle().getUserData().toString()) {
                case "0":
                    Manager newManager = new Manager("Admin", name.getText(), 000, birthDay, email.getText(),998877);
                    database.addAdmin(newManager);
                    break;
                case "1":
                    User newUser = new User(name.getText(), 001, birthDay, email.getText(), 998877);
                    database.addUser(newUser);
                    break;
            }

        }

    }
}
