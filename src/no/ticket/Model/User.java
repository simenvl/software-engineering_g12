package no.ticket.Model;

import java.time.LocalDate;

public class User extends Person{

    private String position;

    public User(String name, LocalDate birthDate, String email, int phone, String position) {
        super(name, birthDate, email, phone);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
