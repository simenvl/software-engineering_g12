
import no.ticket.Model.User;
import no.ticket.Model.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestEvents {
    private static Event event;
    private static User testson;
    private static ArrayList<Event> eventList = new ArrayList<>();

    @BeforeEach
    void init() {
        event = new Event("Run", LocalDate.now(), 123456, 11, "Location", 120,"Description",12, 120);
        Event concertEvent = new Event("Skiing", LocalDate.now(), 123456, 18, "Location", 220, "Description", 360, 20);
        eventList.clear();
        eventList.add(event);
        eventList.add(concertEvent);
        testson = new User("First Testson",101, LocalDate.now(), "test@hiof.no", 9933);

    }

    @Test
    public void GetTitleMovie() {
        Assertions.assertEquals("Run", event.getTitle());
    }

    @Test
    public void GetCapacity30() {
        Assertions.assertEquals(120, event.getCapacity());
    }

    @Test
    public void GetEventListLength() {
        Assertions.assertEquals(2, eventList.size());
    }

    @Test
    public void GetTitleAfterEdit() {
        event.setTitle("Title");
        Assertions.assertEquals("Title", eventList.get(0).getTitle());
    }

    @Test
    public void DeleteEvent() {
        eventList.remove(event);
        Assertions.assertEquals(1, eventList.size());
    }

    @Test
    public void ConcertIsLeft() {
        eventList.remove(event);
        Assertions.assertEquals("Skiing", eventList.get(0).getTitle());
    }

}