package no.ticket.Json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import no.ticket.Model.Event;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteJson {


    @SuppressWarnings("unchecked")
    public static void main (String[] args){
        ArrayList<Event> eventArrayList = new ArrayList<>();
        addToJson(eventArrayList);
    }

    public static void addToJson(ArrayList<Event> event){
        // initiate Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonEvent = gson.toJson(event);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(EventPath.eventPath))) {
            bufferedWriter.write(jsonEvent);
        } catch (IOException ioexc) {
            System.out.println(ioexc.getMessage());
        }
    }
}
