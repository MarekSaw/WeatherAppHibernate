package Proccesing.coordinates;

import Proccesing.Forecast;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonProcessing {

    public Coordinates getClassFromJSONString(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            Coordinates coordinates = objectMapper.readValue(json, Coordinates.class);
            return coordinates;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
