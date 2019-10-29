package no.ticket.Json;

import com.google.gson.Gson;
import no.ticket.Model.Event;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadJson {

    private static ArrayList<Event> eventsList = new ArrayList<>();

    public static void main(String[] args){
        readJson();
    }

    public static ArrayList<Event> getList() {
        readJson();
        return eventsList;
    }

    private static void readJson(){
        Gson gson = new Gson();


        try (FileReader reader = new FileReader(EventPath.eventPath)) {
            Event[] event = gson.fromJson(reader, Event[].class);
            eventsList.addAll(Arrays.asList(event));
            System.out.println(eventsList);
        }catch (Exception e){
            e.printStackTrace();
        }

        /*
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(EventPath.eventPath)) {
            Object obj = parser.parse(reader);
            JSONArray jsonObj = (JSONArray) obj;
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Event>>(){}.getType();
            eventsList = gson.fromJson(jsonObj.toJSONString(), type);
        }catch (Exception e){
            e.printStackTrace();
        }*/
    }


}
