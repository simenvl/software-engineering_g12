package no.ticket.Model;

import no.ticket.Json.ReadJson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;


public class Event {

    private static int eventId;
    private String description;
    private int managerId;
    private String title;
    private LocalDate date;
    private int time;
    private int capacity;
    private int ageRestrict;
    private String place;
    private int price;
    private ArrayList<User> participants = new ArrayList<>();


    public Event() {

    }

    public Event(String title, LocalDate date, int managerId, int agerestrict, String place, int price, String description, int time, int capacity) {
        this.title = title;
        this.date = date;
        this.managerId = managerId;
        this.ageRestrict = agerestrict;
        this.place = place;
        this.price = price;
        this.description = description;
        this.time = time;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return title + " - price: " + ((price == 0) ? "FREE" : price + "kr");
    }


    public int getEventId() {
        return eventId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAgeRestrict() {
        return ageRestrict;
    }

    public void setAgeRestrict(int ageRestrict) {
        this.ageRestrict = ageRestrict;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public void addParticipants(User newUser) {
        this.participants.add(newUser);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
