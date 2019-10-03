package no.ticket.Model;

public class Ticket {

    Event event;
    User customer;

    public Ticket(Event event, User customer) {
        this.event = event;
        this.customer = customer;
    }
}
