package no.ticket.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import no.ticket.Json.ReadJson;
import no.ticket.Model.Event;

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
