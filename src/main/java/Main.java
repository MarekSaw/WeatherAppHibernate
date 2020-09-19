
import Proccesing.JSONForecastProcessing;
import Repositories.OpenWeatherRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        OpenWeatherRepository owr = new OpenWeatherRepository("8f8e09e8c64b12495e3cfdb7f75b54b1");
        JSONForecastProcessing json = new JSONForecastProcessing();
        Scanner input = new Scanner(System.in);


        System.out.println("Witaj, jeżeli chcesz uzyskać informacje o pogodzie na podstawie nazwy miasta wpisz 1, jeżeli koordynatów to 2");
        switch (input.nextInt()) {
            case 1:
                System.out.println("Podaj nazwę miasta");
                String city = input.next();
                System.out.println("Podaj datę w formacie rok, miesiac, dzien jako liczby całkowite kolejno zatwierdzając przyciskiem enter");
                System.out.println("Jeżeli nie chcesz podawać wpisz 0");
                int year = input.nextInt();
                if(year == 0){
                    System.out.println(owr.getForecast(city));
                }else{
                    System.out.println(owr.getForecast(city,LocalDate.of(year,input.nextInt(),input.nextInt())));
                }
                break;
            case 2:
                System.out.println("Podaj koordynaty miasta w kolejności szerokość, długość");
                double lat = input.nextDouble();
                double lon = input.nextDouble();
                System.out.println("Podaj datę w formacie rok, miesiac, dzien jako liczby całkowite kolejno zatwierdzając przyciskiem enter");
                System.out.println("Jeżeli nie chcesz podawać daty wciśnij enter");
                int year1 = input.nextInt();
                if(year1 == 0){
                    System.out.println(owr.getForecast(lat, lon));
                }else{
                    System.out.println(owr.getForecast(lat, lon,LocalDate.of(year1,input.nextInt(),input.nextInt())));
                }
                break;
        }

        //System.out.println(owr.getForecast("Białystok",LocalDate.now().plusDays(2)));
        //System.out.println(owr.getForecast("Białystok"));
        //System.out.println(owr.getForecast(53.133331, 23.15,LocalDate.now().plusDays(2)));
        //System.out.println(owr.getForecast(53.133331, 23.15));

    }
}
