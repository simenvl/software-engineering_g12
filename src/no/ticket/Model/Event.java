package no.ticket.Model;

import java.time.LocalDate;


public class Event {


    private static int eventId;
    private String description;
    private int managerId;
    private String title;
    private LocalDate date;
    private int time;
    private int ageRestrict;
    private String place;
    private int capacity;
    private int price;

    public Event(String description, int managerId, String title, LocalDate date, int time, int ageRestrict, String place, int capacity, int price) {
        this.description = description;
        this.managerId = managerId;
        this.title = title;
        this.date = date;
        this.time = time;
        this.ageRestrict = ageRestrict;
        this.place = place;
        this.capacity = capacity;
        this.price = price;
    }

    public static int getEventId() {
        return eventId;
    }

    public static void setEventId(int eventId) {
        Event.eventId = eventId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
