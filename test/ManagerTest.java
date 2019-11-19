import no.ticket.Data.ServiceStubs;
import no.ticket.MainJavaFX;
import no.ticket.Model.Event;
import no.ticket.Model.Manager;
import no.ticket.Model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ManagerTest {
    static ArrayList<Manager> newManagers;
    static Manager manager;
    static ServiceStubs database;

    @BeforeAll
    static void init(){
        manager = new Manager("", "Test", 123456, LocalDate.of(1990,2,2), "test", 999);
        database = new ServiceStubs();
        database.initialize();
        newManagers = database.getAdmins();
    }

    @Test
    void check(){
        Assertions.assertEquals(123456, newManagers.get(1).getId());
    }

    @Test
    void createAdminUserCheckID() {
        Manager newManager =  new Manager("Admin", "Test", 112233, LocalDate.now(), "hiof.no", 998877);
        newManagers.add(newManager);
        Assertions.assertEquals(112233, newManagers.get(newManagers.size()-1).getId());
    }

    @Test // Denne testen viser at du kan hente en liste med Personer og bruke deres ID for å logge inn
    void loginTest() {
        //manager.setId(111111); // Setter feil ID, testen vil da feile da ingen bruker blir lagt til og vi får nullpointexception.
        for (Manager eachAdmin : database.getAdmins())
            if (Integer.valueOf(manager.getId()).equals(eachAdmin.getId())){
                MainJavaFX.setCurrentPerson(manager, true);
            }

        assertEquals(MainJavaFX.getCurrentUser().getId(), manager.getId());
    }

    @Test
    void managerJoinEvent() {
        Event event = new Event();
        event.setCapacity(1);
        User newUser = new User(manager, 1);
        event.addParticipants(newUser);
        assertEquals(0, event.getCapacity());
    }

}
