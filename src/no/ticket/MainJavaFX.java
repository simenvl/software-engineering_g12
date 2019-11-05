package no.ticket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.ticket.Controller.EventController;
import no.ticket.Controller.HovedLayoutController;
import no.ticket.Controller.TicketController;
import no.ticket.Model.Event;
import no.ticket.Model.Manager;
import no.ticket.Model.Person;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainJavaFX extends Application {


    private static MainJavaFX mainJavaFX;
    public static Stage primaryStage;
    private static Person currentUser;
    private static boolean isCurrentUserAdmin = false;

    @Override
    public void start(Stage primaryStage) {

        mainJavaFX = this;
        try{
            this.primaryStage = primaryStage;

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("View/Login.fxml"));

            Parent hovedLayout = fxmlLoader.load();

            Scene hovedScene = new Scene(hovedLayout);

            primaryStage.setScene(hovedScene);

            primaryStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public static  no.ticket.MainJavaFX getInstance(){
        return mainJavaFX;
    }


    public static void setCurrentPerson(Person currentUser, boolean currentUserRole){
        MainJavaFX.currentUser = currentUser;
        if (currentUserRole)
            isCurrentUserAdmin = true;
        else
            isCurrentUserAdmin = false;
    }

    public static Person getCurrentUser(){
        return currentUser;
    }

    public static boolean getIsUserAdmin(){
        return isCurrentUserAdmin;
    }


    public void setHovedLayout() {
        try{
            this.primaryStage = primaryStage;

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("View/HovedLayout.fxml"));

            Parent hovedLayout = fxmlLoader.load();

            Scene hovedScene = new Scene(hovedLayout);

            primaryStage.setScene(hovedScene);

            HovedLayoutController hovedController = fxmlLoader.getController();

            /* primaryStage.show();*/
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


    public void setEventLayout(Event eventToBeEdited) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("View/EventLayout.fxml"));
            Parent ticketLayout = fxmlLoader.load();

            Scene eventScene = new Scene(ticketLayout);
            primaryStage.setScene(eventScene);
            primaryStage.setTitle("Edit Event");

            EventController eventController = fxmlLoader.getController();

            eventController.setEventToBeEdited(eventToBeEdited);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setNewEventLayout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("View/EventLayout.fxml"));
            Parent ticketLayout = fxmlLoader.load();

            Scene eventScene = new Scene(ticketLayout);
            primaryStage.setScene(eventScene);
            primaryStage.setTitle("Create Event");

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTicketLayout(Event ticketEvent){

        try{

            FXMLLoader fxmlLoader= new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("View/TicketLayout.fxml"));

            Parent ticketLayout = fxmlLoader.load();
            Scene ticketScene= new Scene(ticketLayout, 300,300);

            primaryStage.setScene(ticketScene);
            primaryStage.setTitle("Make Tickets");
            primaryStage.show();

            TicketController ticketController= fxmlLoader.getController();

            ticketController.setEventToAddTicket(ticketEvent);


        }
        catch(IOException e) {
            e.printStackTrace();
        }


    }

    public void setCreateUserLayout() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("View/CreateUser.fxml"));
            Parent createUser = fxmlLoader.load();

            Scene createUserScene = new Scene(createUser);
            primaryStage.setScene(createUserScene);
            primaryStage.setTitle("Create User");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
