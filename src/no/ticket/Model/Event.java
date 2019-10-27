package no.ticket.Model;

import no.ticket.Json.ReadJson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Time;
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
    private int seat;
    private int row;
    private int price;
    private int seats[][];
    private int stand;


    public Event() {

    }

    public Event(String title, LocalDate date, int managerId, int agerestrict, String place, int price, int seat, int row, int stand, String description, int time) {
        this.title = title;
        this.date = date;
        this.managerId = managerId;
        this.ageRestrict = agerestrict;
        this.place = place;
        this.capacity = seat * row;
        this.price = price;
        this.seat = seat;
        this.row = row;
        this.seats = new int[row][seat];
        this.description = description;
        this.stand = stand;
        this.time = time;
    }

    @Override
    public String toString() {
        return getTitle() + " - Price: " + getPrice() + "kr";/*"Event{" +
                "EventId=" + eventId +
                ", ManagerId=" + managerId +
                ", Title=" + title +
                ", Date=" + date +
                ", Agerestrict=" + ageRestrict +
                ", place='" + place + '\'' +
                ", Capacity=" + capacity +
                ", Price=" + price +
                '}';*/
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

    public int getCapacity() {
        return capacity;
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

    public int getStand() {
        return stand;
    }

    public void setStand(int stand) {
        this.stand = stand;
    }

    public int getSeat() {
        return seat;
    }

    public int getRow() {
        return row;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setSeats(int seat, int row) {
        this.seats[row][seat] = 1;
    }

    public int getSeats(int seat, int row) {
        return seats[row][seat];
    }

    public ObservableList<Integer> getRowList(int rows) {
        ObservableList<Integer> rowList = FXCollections.observableArrayList();
        for (int i = 0; i < seat; i++) {
            if (seats[rows][i] < 1)
                rowList.addAll(i + 1);
        }
        return rowList;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void printSeats() {
        for (int I = 0; I < row; I++) {
            System.out.println("");
            for (int Y = 0; Y < seat; Y++) {
                System.out.print(getSeats(Y, I) + " ");
            }
        }
    }
}
