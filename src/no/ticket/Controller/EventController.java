package no.ticket.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import no.ticket.Data.DataHandler;
import no.ticket.Json.WriteJson;
import no.ticket.MainJavaFX;
import no.ticket.Model.Event;
import no.ticket.Model.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventController {

    @FXML
    public ListView<User> ParticipantsListView;
    public TextField txtName;
    public TextField txtRankTitle;
    public TextField txtRankNumber;
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

    ObservableList<User> participantsList = FXCollections.observableArrayList();
    ArrayList<Event> arrayList = new ArrayList<>();

    @FXML
    public void initialize() {
        agePicker.valueProperty().addListener(((observable, oldValue, newValue) -> {
            txtAge.setText(Integer.toString(newValue.intValue()));
        }));

        ParticipantsListView.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> param) {
                ListCell<User> cell = new ListCell<User>() {

                    @Override
                    protected void updateItem(User user, boolean empty) {
                        super.updateItem(user, empty);
                        if (user != null) {
                            setText(user.getRankNumber() + " - " + user.getName());
                        } else {
                            setText("");   // <== clear the now empty cell.
                        }
                    }
                };
                return cell;
            }
        });

        ParticipantsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                if (newValue != null) {
                    txtName.setText(newValue.getName());
                    txtRankNumber.setText(Integer.toString(newValue.getRankNumber()));
                    txtRankTitle.setText(newValue.getRank());
                }
            }
        });

    }

    public void btnAddEvent(MouseEvent mouseEvent) {

        String title = txtTitle.getText();
        String description = txtDescription.getText();
        int capacity = Integer.parseInt(txtCapacity.getText());
        LocalDate date = datePicker.getValue();
        String place = txtLocation.getText();
        arrayList = DataHandler.getEventList();

        int agerestrict = 0, time = 0, price = 0;

        try {
            agerestrict = (int) Math.round(agePicker.getValue());

            time = Integer.parseInt(timeOfEvent.getText());
            price = Integer.parseInt(txtPrice.getText());
        } catch (NumberFormatException ex) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Input Error");
            alert.setContentText("Please use numbers and text correctly");
            alert.showAndWait();
            return;
        }

        if (!editNewEvent) {
            int managerID = MainJavaFX.getCurrentUser().getId();
            Event newEvent = new Event(title,date, managerID, agerestrict, place,price, description, time, capacity);
            arrayList.add(newEvent);

            WriteJson.addToJson(arrayList);
        } else {
            eventToBeEdited.setTitle(title);
            eventToBeEdited.setDescription(description);
            eventToBeEdited.setDate(date);
            eventToBeEdited.setAgeRestrict(agerestrict);
            eventToBeEdited.setPlace(place);
            eventToBeEdited.setPrice(price);
            eventToBeEdited.setTime(time);
            eventToBeEdited.setCapacity(capacity);

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
            txtCapacity.setText(Integer.toString(eventToBeEdited.getCapacity()));

            txtPrice.setText(String.valueOf(eventToBeEdited.getPrice()));
            timeOfEvent.setText(String.valueOf(eventToBeEdited.getTime()));
            txtAge.setText(String.valueOf(eventToBeEdited.getAgeRestrict()));
            participantsList.addAll(eventToBeEdited.getParticipants());
            ParticipantsListView.setItems(participantsList);
        }
    }




    public void btnSaveParticipants(ActionEvent actionEvent) {

        String name = txtName.getText();
        String rankTitle = txtRankTitle.getText();
        int rank = Integer.parseInt(txtRankNumber.getText());

        User selectedUser = ParticipantsListView.getSelectionModel().getSelectedItem();
        selectedUser.setParticipantNumber(rank);
        selectedUser.setRank(rankTitle);

        for (User user : eventToBeEdited.getParticipants()){
            if (user.getName().equals(name)){
                user.setRank(rankTitle);
                user.setRankNumber(rank);
            }
        }
        participantsList.sorted();
        ParticipantsListView.refresh();
        WriteJson.addToJson(arrayList);
    }


}
