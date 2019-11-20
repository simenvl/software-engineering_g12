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
        FXMLLoader fxmlLoader = new FXMLLoader(MainJavaFX.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
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


    public static void main(String[] args) {
        launch();
    }

}
