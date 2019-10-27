package no.ticket.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import no.ticket.Data.DataHandler;
import no.ticket.Json.WriteJson;
import no.ticket.MainJavaFX;
import no.ticket.Model.Event;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventController {

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtPrice;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Slider agePicker;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtCapacity;
    @FXML
    private TextField txtLocation;
    @FXML
    private TextField timeOfEvent;
    @FXML
    private TextArea txtDescription;


    private Event eventToBeEdited;
    private Boolean editNewEvent = false;

    @FXML
    public void initialize() {
        agePicker.valueProperty().addListener(((observable, oldValue, newValue) -> {
            txtAge.setText(Integer.toString(newValue.intValue()));
        }));

    }

    public void btnAddEvent(MouseEvent mouseEvent) {

        String title = txtTitle.getText();
        String description = txtDescription.getText();
        int capacity = Integer.parseInt(txtCapacity.getText());
        LocalDate date = datePicker.getValue();
        String place = txtLocation.getText();
        ArrayList<Event> arrayList = DataHandler.getEventList();

        int agerestrict = 0, seat = 0, stand = 0, row = 0, time = 0, price = 0;

        try {
            agerestrict = (int) Math.round(agePicker.getValue());

            time = Integer.parseInt(timeOfEvent.getText());
            price = Integer.parseInt(txtPrice.getText());
        } catch (NumberFormatException ex) {
           // new AlertBox("INPUT ERROR", "Please use numbers and text correctly", "Age, seat, stand, row, time and price must be numbers.", 1);
            return;
        }

        if (!editNewEvent) {
            int managerID = MainJavaFX.getCurrentPassword();
            //Event newEvent = new Event(description, managerID, title, date, time, agerestrict, place, capacity,price);
            //arrayList.add(newEvent);

            WriteJson.addToJson(arrayList);
        } else {
            eventToBeEdited.setTitle(title);
            eventToBeEdited.setDescription(description);
            eventToBeEdited.setDate(date);
            eventToBeEdited.setAgeRestrict(agerestrict);
            eventToBeEdited.setPlace(place);
            eventToBeEdited.setPrice(price);
            eventToBeEdited.setTime(time);

            WriteJson.addToJson(arrayList);
        }
        MainJavaFX.getInstance().setHovedLayout();
    }

    public void btnCloseEvent() {
        MainJavaFX.getInstance().setHovedLayout();
    }

    public void setEventToBeEdited(Event eventToBeEdited) {

        this.eventToBeEdited = eventToBeEdited;
        this.editNewEvent = true;

        if (eventToBeEdited != null) {
            txtDescription.setText(eventToBeEdited.getDescription());
            datePicker.setValue(eventToBeEdited.getDate());
            txtTitle.setText(eventToBeEdited.getTitle());
            txtDescription.setText(eventToBeEdited.getDescription());
            agePicker.setValue(eventToBeEdited.getAgeRestrict());
            txtLocation.setText(eventToBeEdited.getPlace());

            txtPrice.setText(String.valueOf(eventToBeEdited.getPrice()));
            timeOfEvent.setText(String.valueOf(eventToBeEdited.getTime()));
            txtAge.setText(String.valueOf(eventToBeEdited.getAgeRestrict()));
        }
    }


}
