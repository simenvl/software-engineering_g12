import gruppe.Data.ServiceStubs;
import gruppe.Model.Event;
import gruppe.Model.*;
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
    void createNewUsersCheckID() {
        User newUser = new User("Testson", LocalDate.now(), "test@hiof.no", 99887766);
        User newUser2 = new User("Testson", LocalDate.now(), "test@hiof.no", 99887766);
        database.addUser(newUser);
        database.addUser(newUser2);

        Assertions.assertEquals(220000, database.getUsers().get(5).getId());
        Assertions.assertEquals(220001, database.getUsers().get(6).getId());
    }

    @Test
    void userJoinsEvent() {
        Event event = new Event();
        event.setCapacity(1);
        User newUser = new User(newUsers.get(0), 1);
        event.addParticipants(newUser);
        assertEquals(0, event.getCapacity());
    }


}
