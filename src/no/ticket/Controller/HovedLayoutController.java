package no.ticket.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ticket.Data.DataHandler;
import no.ticket.Json.WriteJson;
import no.ticket.MainJavaFX;
import no.ticket.Model.Event;
import no.ticket.Model.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class HovedLayoutController {

    @FXML
    private ListView<Event> eventListView;

    @FXML
    private Button buyTicketBtn;

    @FXML
    private Button newEvent;

    @FXML
    private Button editEvent;

    @FXML
    private Button logOutBtn;

    @FXML
    private Text titleLabel;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea placeTextArea;

    @FXML
    private TextArea capacityTextArea;

    @FXML
    private ComboBox sortBy;

    @FXML
    private TextField timeOfEvent;

    @FXML
    private Button btnDelete;

    private Person person;

    ObservableList<String> sortMethods = FXCollections.observableArrayList(
            "Alfabetical ascending",
            "Alfabetical descending",
            "Date ascending",
            "Date descending",
            "Price ascending",
            "Price descending",
            "Capacity ascending",
            "Capacity descending"
    );

    ObservableList<Event> listWithEvents = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
//        Liste over valgt manager
//        for (int i = 0; i < DataHandler.getEventData().size(); i++){
//            if (MainJavaFX.getCurrentPassword() == DataHandler.getEventData().get(i).getManagerId() && MainJavaFX.getCurrentPassword() != 0){
//                listWithEvents.add(DataHandler.getEventData().get(i));
//
//            }
//        }
        if (!MainJavaFX.getIsUserAdmin()) {
            listWithEvents.addAll(DataHandler.getEventData());
            newEvent.setVisible(false);
            editEvent.setVisible(false);
            btnDelete.setVisible(false);
        }
        if (MainJavaFX.getIsUserAdmin()) {
            for (Event event : DataHandler.getEventData()){
                if (Integer.valueOf(event.getManagerId()).equals(MainJavaFX.getCurrentUser().getId()))
                    listWithEvents.add(event);
            }

        }
        eventListView.setItems(listWithEvents);
        sortBy.setItems(sortMethods);

        capacityTextArea.setEditable(false);
        placeTextArea.setEditable(false);
        capacityTextArea.setEditable(false);
        descriptionTextArea.setEditable(false);
        datePicker.setEditable(false);

        sortBy.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                if (newValue == "Alfabetical descending") {
                    Comparator<Event> comparator = Comparator.comparing(Event::getTitle);
                    FXCollections.sort(listWithEvents, comparator.reversed());
                } else if (newValue == "Alfabetical ascending") {
                    Comparator<Event> comparator = Comparator.comparing(Event::getTitle);
                    FXCollections.sort(listWithEvents, comparator);
                } else if (newValue == "Date descending") {
                    Comparator<Event> comparator = Comparator.comparing(Event::getDate);
                    FXCollections.sort(listWithEvents, comparator.reversed());
                } else if (newValue == "Date ascending") {
                    Comparator<Event> comparator = Comparator.comparing(Event::getDate);
                    FXCollections.sort(listWithEvents, comparator);
                } else if (newValue == "Price descending") {
                    Comparator<Event> comparator = Comparator.comparing(Event::getPrice);
                    FXCollections.sort(listWithEvents, comparator.reversed());
                } else if (newValue == "Price ascending") {
                    Comparator<Event> comparator = Comparator.comparing(Event::getPrice);
                    FXCollections.sort(listWithEvents, comparator);
                }


            }
        });

        eventListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observable, Event oldValue, Event newValue) {
                if (newValue != null) {
                    eventDetails(newValue);
                }
            }
        });

        editEvent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Event editEvent = eventListView.getSelectionModel().getSelectedItem();

                // Sjekker om eventet inneholder info
                if (editEvent != null) {
                    MainJavaFX.getInstance().setEventLayout(editEvent);


                } else {

                }
            }
        });

        newEvent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                newEvent(event);
                MainJavaFX.getInstance().setNewEventLayout();
            }
        });

        buyTicketBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Event buyTicket = eventListView.getSelectionModel().getSelectedItem();

                if (buyTicket != null) {
                    MainJavaFX.getInstance().setTicketLayout(buyTicket);
                } else {

                }
            }
        });

        logOutBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage primaryStage = MainJavaFX.primaryStage;
                MainJavaFX.getInstance().start(primaryStage);
                MainJavaFX.setCurrentPerson(null,false);
            }
        });
    }

    private void eventDetails(Event event) {
        titleLabel.setText(event.getTitle());
        descriptionTextArea.setText(event.getDescription());
        datePicker.setValue(event.getDate());
        timeOfEvent.setText(String.valueOf(event.getTime()));
        placeTextArea.setText(event.getPlace());
        capacityTextArea.setText(Integer.toString(event.getCapacity()));
    }

    public void newEvent(ActionEvent actionEvent) {
        //Event newEvent = new Event();

       // MainJavaFX.getInstance().setEventLayout(newEvent);
    }


    public void onClickDelete(ActionEvent actionEvent) {
        Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();
        WriteJson.addToJson(deleteEventFromList(selectedEvent, listWithEvents));
    }

    static public ArrayList<Event> deleteEventFromList(Event selectedEvent, List<Event> list) {
        list.remove(selectedEvent);
        ArrayList<Event> eventList = new ArrayList<>(list);
        return eventList;
    }

}
