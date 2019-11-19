package gruppe12.Data;

import gruppe12.Model.Manager;
import gruppe12.Model.User;

import java.util.ArrayList;

interface Services {
    ArrayList<Manager> getAdmins();
    ArrayList<User> getUsers();

}
