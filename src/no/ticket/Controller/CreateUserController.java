package no.ticket.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import no.ticket.Data.ServiceStubs;
import no.ticket.MainJavaFX;
import no.ticket.Model.Manager;

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
        if(name.getText().trim().isEmpty()){
            System.out.println("Empty");


        } else {
            LocalDate birthDay = datePicker.getValue();
            switch (radioGroup.getSelectedToggle().getUserData().toString()) {
                case "0":
                    Manager newManager = new Manager("Admin", name.getText(), 000, birthDay, email.getText(), 998877);
                    System.out.println("Toggle : " + radioGroup.getSelectedToggle().getUserData());
                    database.addAdmin(newManager);

                    System.out.println("Toggle : " + database.getAdmins());
                    break;
                case "1":
                    System.out.println("Toggle : " + radioGroup.getSelectedToggle().getUserData());
                    break;
            }

        }

    }
}
