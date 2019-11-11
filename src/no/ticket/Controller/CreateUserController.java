package no.ticket.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import no.ticket.MainJavaFX;


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
            switch (radioGroup.getSelectedToggle().getUserData().toString()) {
                case "0":
                    System.out.println("Toggle : " + radioGroup.getSelectedToggle().getUserData());
                    break;
                case "1":
                    System.out.println("Toggle : " + radioGroup.getSelectedToggle().getUserData());
                    break;
            }

        }

    }
}
