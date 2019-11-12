import no.ticket.Data.ServiceStubs;
import no.ticket.MainJavaFX;
import no.ticket.Model.Manager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class loginTest {
    static Manager manager;
    static ServiceStubs database = new ServiceStubs();
    @BeforeAll
    public static void init(){
        manager = new Manager("", "Test", 123456, LocalDate.of(1990,2,2), "test", 999);
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

}
