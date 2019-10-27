package no.ticket.Model;
import java.time.LocalDate;

public class Person {

    private String name;
    private int id;
    private LocalDate birthDate;
    private String email;
    private int phone;

    public Person(String name, LocalDate birthDate, String email, int phone) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
    }

    public Person(String name, int id, LocalDate birthDate, String email, int phone) {
        this.name = name;
        this.id = id;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + ", " + birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

}
