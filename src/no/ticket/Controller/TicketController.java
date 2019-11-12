package no.ticket.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import no.ticket.Data.DataHandler;
import no.ticket.Json.WriteJson;
import no.ticket.MainJavaFX;
import no.ticket.Model.Event;
import no.ticket.Model.Person;
import no.ticket.Model.Ticket;
import no.ticket.Model.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class TicketController {


    @FXML
    private Button buy;
    @FXML
    private Label eventTitle;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtMobile;
    @FXML
    private TextField txtParticipant;
    @FXML
    private DatePicker datePicker;


    private Event buyTicketEvent;
    private int participantNumber;


    @FXML
    public void initialize() {

        buy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Period.between(MainJavaFX.getCurrentUser().getBirthDate(), LocalDate.now()).getYears() < buyTicketEvent.getAgeRestrict()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("You are not old enough for this event");
                    alert.showAndWait();
                    System.out.println("You are not old enough for this event");
                } else {
                    System.out.println("Ticket bought");
                    Ticket newTicket = new Ticket (buyTicketEvent, MainJavaFX.getCurrentUser());
                    User newUser = new User(MainJavaFX.getCurrentUser(), participantNumber);
                    buyTicketEvent.addParticipants(newUser);

                    ArrayList<Event> arrayList = DataHandler.getEventList();
                    WriteJson.addToJson(arrayList);
                    //User newCustomer = new User(name,1000, date, email, phone, position);

                    /*
                    if (buyTicketEvent.getSeats(seatN, rowN) == 0) {
                        new Ticket(buyTicketEvent, newCustomer);

                        //new AlertBox("Confirmation", "A mail has been sent to " + txtEmail.getText(), "", 2);
                        MainJavaFX.getInstance().setHovedLayout();

                        ArrayList<Event> arrayList = DataHandler.getEventList();
                        WriteJson.addToJson(arrayList);

                        buyTicketEvent.printSeats();
                    } else {
                        System.out.println("Your number has been taken");
                        //new AlertBox("Error", "Your seat has been taken, please choose another one", "", 2);
                    }*/


                }

            }
        });

    }

    public void btnCancelTicket() {
        MainJavaFX.getInstance().setHovedLayout();
    }

    public void setEventToAddTicket(Event buyTicketEvent) {
        this.buyTicketEvent = buyTicketEvent;

        if (MainJavaFX.getCurrentUser() != null) {
            eventTitle.setText(buyTicketEvent.getTitle());
            txtName.setText(MainJavaFX.getCurrentUser().getName());
            txtEmail.setText(MainJavaFX.getCurrentUser().getEmail());
            txtMobile.setText(Integer.toString(MainJavaFX.getCurrentUser().getPhone()));
            datePicker.setValue(MainJavaFX.getCurrentUser().getBirthDate());
            datePicker.setEditable(false);
            for (User current : buyTicketEvent.getParticipants()) {
                System.out.println(current.getId() + " " + MainJavaFX.getCurrentUser().getId());
                if (Integer.valueOf(current.getId()).equals(MainJavaFX.getCurrentUser().getId())) {
                    txtParticipant.setText("You're already signed");
                    break;
                } else {
                    participantNumber = buyTicketEvent.getParticipants().size() + 1;
                    txtParticipant.setText(Integer.toString(participantNumber));
                }
            }
        } else { // If user is a guest with no user
            txtName.setEditable(true);
            txtEmail.setEditable(true);
            txtMobile.setEditable(true);
            participantNumber = buyTicketEvent.getParticipants().size() + 1;
            txtParticipant.setText(Integer.toString(participantNumber));
        }




    }


}
