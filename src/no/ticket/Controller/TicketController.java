package no.ticket.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import no.ticket.MainJavaFX;
import no.ticket.Model.Event;
import no.ticket.Model.User;

import java.time.LocalDate;
import java.time.Period;

public class TicketController {

    @FXML
    private Button buy;
    @FXML
    private Label eventTitle;
    @FXML
    private ComboBox seat, row, stand;
    @FXML
    private DatePicker age;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtMobile;
    @FXML
    private TextField txtPosition;


    private Event buyTicketEvent;


    @FXML
    public void initialize() {

        age.setEditable(false);

        buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Period period = Period.between(age.getValue(), LocalDate.now());

                if (period.getYears() < buyTicketEvent.getAgeRestrict()) {
                    //  new AlertBox("Too young", "You are not old enough for this event", "", 2);
                } else {
                    String name = txtName.getText();
                    LocalDate date = age.getValue();
                    String email = txtEmail.getText();
                    int phone = Integer.parseInt(txtMobile.getText());
                    String position = txtPosition.getText();
                    User newCustomer = new User(name, date, email, phone, position);


                }

            }
        });

    }

    public void btnCancelTicket() {
        MainJavaFX.getInstance().setHovedLayout();
    }

    public void setEventToBeEdited(Event buyTicketEvent) {
        this.buyTicketEvent = buyTicketEvent;


    }

}


