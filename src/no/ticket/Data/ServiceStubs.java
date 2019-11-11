package no.ticket.Data;

import no.ticket.Model.Manager;
import no.ticket.Model.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class ServiceStubs implements Services {
    ArrayList<Manager> admins = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    @Override
    public ArrayList<Manager> getAdmins() {
        Manager Mats = new Manager("admin", "Mats Lindh", 654321, LocalDate.of(1970, 5, 7), "test@hiof.no", 99887766);
        Manager Lars = new Manager("admin", "Lars-Erik Aabech", 123456, LocalDate.of(1970, 7, 5), "test@hiof.no", 99887766);
        admins.add(Mats);
        admins.add(Lars);
        return admins;
    }

    @Override
    // User[] byttet til ArrayList for å enkelt kunne legge til nye midlertidige brukere.
    public ArrayList<User> getUsers() {
        User Thomas = new User("Thomas C",173178, LocalDate.of(1990,1,2), "test@hiof.no", 99887766);
        User Quang = new User("Quang L",173006, LocalDate.of(1990,3,4), "test@hiof.no", 99887766);
        User Omar = new User("Omar C",173007, LocalDate.of(1990,5,6), "test@hiof.no", 99887766);
        User Simen = new User("Simen L",163279, LocalDate.of(1990,7,8), "test@hiof.no", 99887766);
        User Christopher = new User("Christopher L",121171, LocalDate.of(1990,9,10), "test@hiof.no", 99887766);
        users.add(Thomas);
        users.add(Quang);
        users.add(Omar);
        users.add(Simen);
        users.add(Christopher);

        return users;
    }
}
