package no.ticket.Data;

import no.ticket.Model.Manager;
import no.ticket.Model.User;

import java.time.LocalDate;

public class ServiceStubs implements Services {
    @Override
    public Manager[] getAdmins() {
        Manager Mats = new Manager("admin", "Mats Lindh", 654321, LocalDate.of(1970, 5, 7), "test@hiof.no", 99887766);
        Manager Lars = new Manager("admin", "Lars-Erik Aabech", 123456, LocalDate.of(1970, 7, 5), "test@hiof.no", 99887766);
        Manager[] admins = {Mats, Lars};
        return admins;
    }

    @Override
    public User[] getUsers() {
        User Thomas = new User("Thomas C",173178, LocalDate.of(1990,1,2), "test@hiof.no", 99887766, "Sarpsborg");
        User Quang = new User("Quang L",173006, LocalDate.of(1990,3,4), "test@hiof.no", 99887766, "Fredrikstad");
        User Omar = new User("Omar C",173007, LocalDate.of(1990,5,6), "test@hiof.no", 99887766, "Moss");
        User Simen = new User("Simen L",163279, LocalDate.of(1990,7,8), "test@hiof.no", 99887766, "Oslo");
        User Christopher = new User("Christopher L",121171, LocalDate.of(1990,9,10), "test@hiof.no", 99887766, "Fredrikstad");
        User[] users = {Thomas, Quang, Omar, Simen, Christopher};
        return users;
    }
}
