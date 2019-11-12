import no.ticket.Data.ServiceStubs;
import no.ticket.Model.Manager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;


public class ManagerTest {
    static ArrayList<Manager> newManagers;


    @BeforeAll
    static void init(){
        ServiceStubs database = new ServiceStubs();
        database.initialize();
        newManagers = database.getAdmins();
    }

    @Test
    void Check(){
        Assertions.assertEquals(123456, newManagers.get(1).getId());
    }

    @Test
    void CreateAdminUserCheckID() {
        Manager newManager =  new Manager("admin", "Test", 112233, LocalDate.now(), "hiof.no", 998877);
        newManagers.add(newManager);
        Assertions.assertEquals(112233, newManagers.get(newManagers.size()-1).getId());
    }
}
