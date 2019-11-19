package gruppe12.Model;

public class Ticket {

    Event event;
    Person customer;

    public Ticket(Event event, Person customer) {
        this.event = event;
        this.customer = customer;
    }
}
