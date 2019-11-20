package gruppe;

import gruppe.Data.DataHandler;
import gruppe.Json.WriteJson;
import gruppe.Model.Event;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static gruppe.MainJavaFX.setRoot;
import static gruppe.MainJavaFX.setSelectedEvent;


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
    @FXML
    private ListView<Event> pastEventsList;


    ObservableList<String> sortMethods = FXCollections.observableArrayList(
            "Alfabetical ascending",
            "Alfabetical descending",
            "Date ascending",
            "Date descending",
            "Price ascending",
            "Price descending"
    );

    ObservableList<Event> currentEvents = FXCollections.observableArrayList();
    ObservableList<Event> pastEvents = FXCollections.observableArrayList();


    @FXML
    public void initialize() {

        if (MainJavaFX.getCurrentUser() == null)
            logOutBtn.setText("Login");
        else
            logOutBtn.setText("Logout");

        for(Event event : DataHandler.getEventData()){
            if (event.getDate().compareTo(LocalDate.now()) >= 0)
                currentEvents.add(event);
            else
                pastEvents.add(event);
        }
        eventListView.setItems(currentEvents);
        pastEventsList.setItems(pastEvents);
        sortBy.setItems(sortMethods);

        if (MainJavaFX.getIsUserAdmin())
            newEvent.setVisible(true);
        else
            newEvent.setVisible(false);

        sortBy.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                if (newValue == "Alfabetical descending") {
                    Comparator<Event> comparator = Comparator.comparing(Event::getTitle);
                    FXCollections.sort(currentEvents, comparator.reversed());
                    FXCollections.sort(pastEvents, comparator.reversed());
                } else if (newValue == "Alfabetical ascending") {
                    Comparator<Event> comparator = Comparator.comparing(Event::getTitle);
                    FXCollections.sort(currentEvents, comparator);
                    FXCollections.sort(pastEvents, comparator);
                } else if (newValue == "Date descending") {
                    Comparator<Event> comparator = Comparator.comparing(Event::getDate);
                    FXCollections.sort(currentEvents, comparator.reversed());
                    FXCollections.sort(pastEvents, comparator.reversed());
                } else if (newValue == "Date ascending") {
                    Comparator<Event> comparator = Comparator.comparing(Event::getDate);
                    FXCollections.sort(currentEvents, comparator);
                    FXCollections.sort(pastEvents, comparator);
                } else if (newValue == "Price descending") {
                    Comparator<Event> comparator = Comparator.comparing(Event::getPrice);
                    FXCollections.sort(currentEvents, comparator.reversed());
                    FXCollections.sort(pastEvents, comparator.reversed());
                } else if (newValue == "Price ascending") {
                    Comparator<Event> comparator = Comparator.comparing(Event::getPrice);
                    FXCollections.sort(currentEvents, comparator);
                    FXCollections.sort(pastEvents, comparator);
                }


            }
        });

        eventListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observable, Event oldValue, Event newValue) {
                if (!pastEventsList.getSelectionModel().isEmpty())
                    pastEventsList.getSelectionModel().clearSelection(0); // INdex out of bound... JavaFX

                if (newValue != null) {
                    eventDetails(newValue);
                }
                buyTicketBtn.setVisible(true);
            }
        });
        pastEventsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observable, Event oldValue, Event newValue) {
                if(!eventListView.getSelectionModel().isEmpty())
                    eventListView.getSelectionModel().clearSelection(0);
                if (newValue != null) {
                    eventDetails(newValue);
                }
                buyTicketBtn.setVisible(false);
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

        if (MainJavaFX.getIsUserAdmin() && event.getManagerId() == MainJavaFX.getCurrentUser().getId()) {
            editEvent.setVisible(true);
            btnDelete.setVisible(true);
        } else {
            editEvent.setVisible(false);
            btnDelete.setVisible(false);
        }

        if (MainJavaFX.getIsUserAdmin())
            newEvent.setVisible(true);
    }



    public void onClickDelete(ActionEvent actionEvent) {
        Event selectedEvent = eventListView.getSelectionModel().getSelectedItem();

        WriteJson.addToJson(deleteEventFromList(selectedEvent, DataHandler.getEventList()));
        currentEvents.remove(selectedEvent);
        eventListView.refresh();
    }

    static public ArrayList<Event> deleteEventFromList(Event selectedEvent, List<Event> list) {
        list.remove(selectedEvent);
        ArrayList<Event> eventList = new ArrayList<>(list);
        return eventList;
    }

    public void switchToTicketLayout(ActionEvent actionEvent) throws IOException {
        Event buyTicket = eventListView.getSelectionModel().getSelectedItem();

        if (buyTicket != null) {
            setSelectedEvent(buyTicket);
            setRoot("TicketLayout");
        } else {
            System.out.println("No event selected");
        }
    }

    public void switchToEventLayout(ActionEvent actionEvent) throws IOException {
        MainJavaFX.setSelectedEvent(null);
        setRoot("EventLayout");
    }

    public void switchToEditEventLayout(ActionEvent actionEvent) throws IOException {
        Event editEvent = eventListView.getSelectionModel().getSelectedItem();

        // Sjekker om eventet inneholder info
        if (editEvent != null) {
            MainJavaFX.setSelectedEvent(editEvent);
            setRoot("EventLayout");
        } else {

        }
    }

    public void switchToLogout(ActionEvent actionEvent) throws IOException {
        if(logOutBtn.getText().equals("Login"))
            setRoot("Login");
        else if (logOutBtn.getText().equals("Logout")){
            MainJavaFX.setCurrentPerson(null,false);
            setRoot("HovedLayout");
        }
    }
}
