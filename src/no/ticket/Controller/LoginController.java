package no.ticket.Controller;

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

public class LoginController {
    @FXML
    public Button loginButton;
    @FXML
    public Button guestButton;
    @FXML
    public PasswordField idField;
    @FXML
    public AnchorPane rootPane;
    @FXML
    public Label messageLabel;
    @FXML
    public ComboBox txtAdmin, txtUser;


    ObservableList<Manager> adminList;
    public void initialize() {
        ServiceStubs database = new ServiceStubs();

        for (Manager admin : database.getAdmins()) {
            adminList.add(admin);
        }
        //adminList.addAll(database.getAdmins());
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) { //her blir verdien som ble tastet inn sendt videre om den matcher med ID-en til en manager
                for (int i = 0; i < MainJavaFX.managerList().size(); i++) {
                    if (Integer.toString(MainJavaFX.managerList().get(i).getId()).equals(idField.getText())) {
                        MainJavaFX.managerList().get(i).getId();
                        MainJavaFX.setCurrentPassword(MainJavaFX.managerList().get(i).getId());
                        MainJavaFX.getInstance().setHovedLayout();
                    } else {
                        messageLabel.setText("Wrong ID! Try 123456");
                    }
                    System.out.println(idField.getText());
                }
            }

        });

        guestButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String guest = guestButton.getText();
                MainJavaFX.getInstance().setHovedLayout();
            }
        });

        txtAdmin.setItems(adminList);
    }

    public void guestButtonClicked(javafx.event.ActionEvent actionEvent) {

    }
}
