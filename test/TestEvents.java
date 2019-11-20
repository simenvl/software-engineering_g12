
import gruppe.HovedLayoutController;
import gruppe.Data.DataHandler;
import gruppe.Model.User;
import gruppe.Model.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

class TestEvents {
    private static Event event;
    private static User testson;
    private static ArrayList<Event> eventList = new ArrayList<>();

    @BeforeAll
     static void init() {
        DataHandler.getEventData();
        eventList = DataHandler.getEventList();
        event = new Event("Run", LocalDate.now(), 123456, 11, "Location", 120,"Description",12, 120);
        eventList.add(event);
        testson = new User("First Testson",101, LocalDate.now(), "test@hiof.no", 9933);

    }

    @Test
    void createNewEventChecklist() {
        Event skiingEvent = new Event("Skiing", LocalDate.now(), 123456, 18, "Location", 220, "Description", 360, 20);
        eventList.add(skiingEvent);
        Assertions.assertEquals(skiingEvent, eventList.get(eventList.size()-1));
    }

    @Test
    void editEventCheckTitle() {
        eventList.get(0).setTitle("New Title");
        Assertions.assertEquals("New Title", eventList.get(0).getTitle());
    }


    @Test
    void checkRemainingSpots() {
        event.addParticipants(testson);
        Assertions.assertEquals(119, event.getCapacity());
        Assertions.assertEquals(20, eventList.get(eventList.size() - 1).getCapacity());
    }

    @Test
    void deleteEventCheckSize() {
        int sizeBeforeDelete = eventList.size() - 1;
        HovedLayoutController.deleteEventFromList(event, eventList);
        Assertions.assertEquals(sizeBeforeDelete, eventList.size());
    }



    @Test
    void checkIfEventIsPassed() {
        // negativ om datoen er passert, 0 er samme dato. Forventer da 0
        int eventTime = event.getDate().compareTo(LocalDate.now());
        Assertions.assertEquals(0, eventTime);
    }

}