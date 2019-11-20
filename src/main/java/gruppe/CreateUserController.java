package gruppe;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import gruppe.Data.ServiceStubs;
import gruppe.Model.Manager;
import gruppe.Model.User;

import java.io.IOException;
import java.time.LocalDate;


public class CreateUserController {
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

    private ToggleGroup radioGroup = new ToggleGroup();
    private ServiceStubs database = MainJavaFX.database;


    public void initialize() {

        managerRadio.setUserData("0");
        contestantRadio.setUserData("1");
        manager.setDisable(true);
        managerRadio.setToggleGroup(radioGroup);
        contestantRadio.setToggleGroup(radioGroup);

        managerRadio.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    manager.setDisable(false);
                } else {
                    manager.setDisable(true);
                }
            }
        });

    }

    public void createUser(ActionEvent actionEvent) throws IOException {
        if (name.getText().isEmpty() && email.getText().isEmpty()) {
            System.out.println("Empty");


        } else {
            LocalDate birthDay = datePicker.getValue();
            switch (radioGroup.getSelectedToggle().getUserData().toString()) {
                case "0":
                    Manager newManager = new Manager("Admin", name.getText(), birthDay, email.getText(), 998877);
                    database.addAdmin(newManager);
                    MainJavaFX.setCurrentPerson(newManager, true);
                    MainJavaFX.setRoot("HovedLayout");
                    break;
                case "1":
                    User newUser = new User(name.getText(), birthDay, email.getText(), 998877);
                    System.out.println("ID : " + newUser.getId());
                    database.addUser(newUser);
                    MainJavaFX.setCurrentPerson(newUser, false);
                    MainJavaFX.setRoot("HovedLayout");
                    break;
            }

        }

    }

    public void switchToLogin(ActionEvent actionEvent) throws IOException {
        MainJavaFX.setRoot("Login");
    }
}
