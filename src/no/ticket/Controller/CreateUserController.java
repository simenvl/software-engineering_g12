package no.ticket.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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


    public void initialize() {

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage primaryStage = MainJavaFX.primaryStage;
                MainJavaFX.getInstance().start(primaryStage);
                MainJavaFX.setCurrentPerson(null,false);
            }
        });

    }

}
