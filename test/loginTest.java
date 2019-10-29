import no.ticket.MainJavaFX;
import no.ticket.Model.Manager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class loginTest {
    static Manager manager;
    @BeforeAll
    public static void init(){
        manager = new Manager("", "Test", 123456, LocalDate.of(1990,2,2), "test", 999);
    }
    @Test //Denne testen viser at du kan hente en liste med Personer og bruke deres ID for å logge inn
    void loginTest() {
        MainJavaFX.setCurrentPerson(manager, true);
        assertEquals(MainJavaFX.getCurrentUser().getId(), manager.getId());
    }

}
