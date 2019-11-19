package gruppe12.Json;

import com.google.gson.Gson;
import gruppe12.Model.Event;


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
        eventsList.clear();
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(EventPath.eventPath)) {
            Event[] event = gson.fromJson(reader, Event[].class);
            eventsList.addAll(Arrays.asList(event));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
