package no.ticket.Data;

import no.ticket.Model.Manager;
import no.ticket.Model.User;

interface Services {
    Manager[] getAdmins();
    User[] getUsers();

}
