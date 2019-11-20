package gruppe.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import gruppe.Json.ReadJson;
import gruppe.Model.Event;

import java.util.ArrayList;

public class DataHandler {

    private final static ObservableList<Event> eventList = FXCollections.observableArrayList();

    public static ObservableList<Event> getEventData() {
        eventList.clear();
        eventList.addAll(ReadJson.getList());
        return eventList;
    }

    public static ArrayList<Event> getEventList(){
        ArrayList<Event> eventArrayList = new ArrayList<>();
        eventArrayList.addAll(eventList);
        return eventArrayList;
    }

}
