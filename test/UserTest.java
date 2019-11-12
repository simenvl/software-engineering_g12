import no.ticket.Data.ServiceStubs;
import no.ticket.Model.Event;
import no.ticket.Model.Manager;
import no.ticket.Model.Ticket;
import no.ticket.Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    static ServiceStubs database = new ServiceStubs();
    static Event newEvent;
    static ArrayList<Manager> newManager;
    static ArrayList<User> newUsers;

    @BeforeAll
    public static void init(){
        database.initialize();
        newManager = database.getAdmins();
        newUsers = database.getUsers();
        newEvent = new Event("Ski", LocalDate.now(), 123456, 10, "Fredrikstad", 10, "This is a ski-run", 10, 10);
    }

    @Test
    void createNewUserCheckID() {
        Manager newManager = new Manager("Admin", "Test", 112233, LocalDate.now(), "test@test.com", 998877);
        database.addAdmin(newManager);
        User newUser = new User("Testson", LocalDate.now(), "test@hiof.no", 99887766);
        database.addUser(newUser);
        Assertions.assertEquals(112233, database.getAdmins().get(2).getId());
        Assertions.assertEquals(220000, database.getUsers().get(5).getId());
    }

    @Test
    void checkIfEventHasCorrectManager() {
        Assertions.assertEquals(123456, database.getAdmins().get(1).getId());
    }

    @Test
    void managerCreatesEvent() {
        newEvent.addParticipants(newUsers.get(0));
    }
}
