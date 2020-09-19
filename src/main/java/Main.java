import Proccesing.Forecast;
import Proccesing.JSONForecastProcessing;
import Repositories.OpenWeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;


import java.io.IOException;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        OpenWeatherRepository owr = new OpenWeatherRepository("8f8e09e8c64b12495e3cfdb7f75b54b1");
        JSONForecastProcessing json = new JSONForecastProcessing();
        System.out.println(owr.getForecast(53.133331, 23.15));

        System.out.println(owr.getForecast("London",LocalDate.now().plusDays(2)));
        System.out.println(owr.getForecast("London"));

    }
}
