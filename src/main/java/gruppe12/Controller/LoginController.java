package gruppe12.Controller;

import gruppe12.Data.ServiceStubs;
import gruppe12.MainJavaFX;
import gruppe12.Model.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import gruppe12.Model.User;

public class LoginController {
    @FXML
    public Button adminButton;
    @FXML
    public Button userButton;
    @FXML
    public Button creatuserBtn;
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
         database = MainJavaFX.database;

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

        creatuserBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainJavaFX.getInstance().setCreateUserLayout();
            }
        });

    }

    public void guestButtonClicked(javafx.event.ActionEvent actionEvent) {

    }

    public void onClick(boolean ifAdmin){
        //her blir verdien som ble tastet inn sendt videre om den matcher med ID-en til en manager
            if (ifAdmin) {
                for (Manager admin : database.getAdmins()) {
                    if (Integer.toString(admin.getId()).equals(Integer.toString(comboAdmin.getValue().getId()))){
                        MainJavaFX.setCurrentPerson(admin, true);
                        MainJavaFX.getInstance().setHovedLayout();
                    } else {
                        messageLabel.setText("Wrong admin ID!");
                    }
                }
            } else {
                for (User guest : database.getUsers()) {
                    if (Integer.valueOf(guest.getId()).equals(comboUser.getValue().getId())){
                        MainJavaFX.setCurrentPerson(guest, false);
                        MainJavaFX.getInstance().setHovedLayout();
                    } else {
                        messageLabel.setText("Wrong user ID!");
                    }
                }
            }
        }

        //public static void
    }
