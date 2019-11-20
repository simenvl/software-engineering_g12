package gruppe.Data;

import gruppe.Model.Manager;
import gruppe.Model.User;

import java.util.ArrayList;

interface Services {
    ArrayList<Manager> getAdmins();
    ArrayList<User> getUsers();

}
