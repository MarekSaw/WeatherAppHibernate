package Proccesing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONForecastProcessing {

    public Forecast getClassFromJSONString(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            Forecast forecast = objectMapper.readValue(json, Forecast.class);
            return forecast;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
