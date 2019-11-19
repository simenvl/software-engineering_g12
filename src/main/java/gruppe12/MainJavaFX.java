package gruppe12;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import gruppe12.Data.ServiceStubs;
import gruppe12.Model.*;
import java.io.IOException;

public class MainJavaFX extends Application {


    private static MainJavaFX mainJavaFX;
    public static Stage primaryStage;
    private static Person currentUser;
    public static ServiceStubs database = new ServiceStubs();
    private static boolean isCurrentUserAdmin = false;
    private static boolean firstStartUp = false;

    private static Event selectedEvent;
    private static Scene scene;


    @Override
    public void start(Stage primaryStage) {

        if (!firstStartUp){
            database.initialize();
            firstStartUp = true;
        }

        mainJavaFX = this;
        try{
            MainJavaFX.primaryStage = primaryStage;

            scene = new Scene(loadFXML("hovedLayout"));
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        primaryStage.sizeToScene();
    }

    static void setSelectedEvent(Event event){
        selectedEvent = event;
    }

    static Event getSelectedEvent() {
        return selectedEvent;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static  MainJavaFX getInstance(){
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
            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("View/HovedLayout.fxml"));

            Parent hovedLayout = fxmlLoader.load();

            Scene hovedScene = new Scene(hovedLayout);

            primaryStage.setScene(hovedScene);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }


    public void setEventLayout(Event eventToBeEdited) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EventLayout.fxml"));
            Parent ticketLayout = fxmlLoader.load();

            scene.setRoot(ticketLayout);

            EventController eventController = fxmlLoader.getController();
            eventController.setEventToBeEdited(eventToBeEdited);

            primaryStage.setScene(scene);
            primaryStage.sizeToScene();
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

    public void setLoginLayout() {
        try {
            setRoot("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}
