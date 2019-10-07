package no.ticket.Json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import no.ticket.Model.Event;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ReadJson {

    private static ArrayList<Event> eventsList;

    public static void main(String[] args){

    }

    public static ArrayList<Event> getList() {
        readJson();
        return eventsList;
    }

    private static void readJson(){
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(EventPath.eventPath)) {
            Object obj = parser.parse(reader);
            JSONArray jsonObj = (JSONArray) obj;
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Event>>(){}.getType();
            eventsList = gson.fromJson(jsonObj.toJSONString(), type);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
