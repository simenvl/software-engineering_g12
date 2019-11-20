package gruppe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import gruppe.Data.DataHandler;
import gruppe.Json.WriteJson;
import gruppe.Model.Event;
import gruppe.Model.Ticket;
import gruppe.Model.User;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class TicketController {

    @FXML
    private Label eventTitle;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtMobile;
    @FXML
    private Label txtParticipant;
    @FXML
    private DatePicker datePicker;


    private Event buyTicketEvent;
    private int participantNumber;


    @FXML
    public void initialize() {

        if (MainJavaFX.getSelectedEvent() != null)
            setEventToAddTicket(MainJavaFX.getSelectedEvent());

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
                if (Integer.valueOf(current.getId()).equals(MainJavaFX.getCurrentUser().getId())) {
                    txtParticipant.setText("You're already signed");
                } else {
                    participantNumber = buyTicketEvent.getParticipants().size() == 0 ? 1 : buyTicketEvent.getParticipants().size() + 1;
                    txtParticipant.setText(Integer.toString(participantNumber));
                }
            }
        } else { // If user is a guest with no user
            txtName.setEditable(true);
            txtEmail.setEditable(true);
            txtMobile.setEditable(true);
            participantNumber = buyTicketEvent.getParticipants().size() == 0 ? 1 : buyTicketEvent.getParticipants().size() + 1;
            txtParticipant.setText(Integer.toString(participantNumber));
        }




    }


    public void switchToHovedLayout(ActionEvent actionEvent) throws IOException {
        MainJavaFX.setRoot("HovedLayout");
    }

    public void btnBuyEvent(ActionEvent actionEvent) throws IOException {
        if (Period.between(MainJavaFX.getCurrentUser().getBirthDate(), LocalDate.now()).getYears() < buyTicketEvent.getAgeRestrict()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("You are not old enough for this event");
            alert.showAndWait();
            System.out.println("You are not old enough for this event");
        } else {
            System.out.println("Ticket bought");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("A confirmation mail has been sendt to: " + MainJavaFX.getCurrentUser().getEmail());
            alert.showAndWait();
            Ticket newTicket = new Ticket (buyTicketEvent, MainJavaFX.getCurrentUser());
            User newUser = new User(MainJavaFX.getCurrentUser(), participantNumber);
            buyTicketEvent.addParticipants(newUser);
            System.out.println(newTicket);
            ArrayList<Event> arrayList = DataHandler.getEventList();
            WriteJson.addToJson(arrayList);
            MainJavaFX.setRoot("HovedLayout");

        }
    }
}
