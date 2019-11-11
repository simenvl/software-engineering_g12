package no.ticket.Data;

import no.ticket.Model.Manager;
import no.ticket.Model.User;

import java.util.ArrayList;

interface Services {
    ArrayList<Manager> getAdmins();
    ArrayList<User> getUsers();

}
