package no.ticket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.ticket.Controller.HovedLayoutController;
import no.ticket.Model.Manager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainJavaFX extends Application {


    private static MainJavaFX mainJavaFX;
    public static Stage primaryStage;
    private static int currentPassword;

    @Override
    public void start(Stage primaryStage) {

        mainJavaFX = this;
        System.out.println(managerList().get(0).getId());

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


    public static ArrayList<Manager> managerList(){
        Manager manager1 = new Manager("title", "John Johnsen", 123456, LocalDate.of(1996, 12, 20), "john@hotmail.com", 21436587);
        Manager manager2 = new Manager("title", "Tom Tom", 654321, LocalDate.of(1996, 12, 20), "tom@hotmail.com", 21436587);
        ArrayList<Manager> managerArrayList = new ArrayList<>();
        managerArrayList.add(manager1);
        managerArrayList.add(manager2);
        return managerArrayList;
    }

    public static int getCurrentPassword() {
        return currentPassword;
    }

    public static int setCurrentPassword(int currentPassword) {
        MainJavaFX.currentPassword = currentPassword;
        return currentPassword;
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
}
