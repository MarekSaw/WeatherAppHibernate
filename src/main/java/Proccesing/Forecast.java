package Proccesing;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {
    private double lat;
    private double lon;
    private String timezone;

    @JsonProperty("timezone_offset")
    private int timezoneOffset;
    private Current current;
    private Daily[] daily;

    public Daily[] getDaily() {
        return daily;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setTimezone_offset(int timezone_offset) {
        this.timezoneOffset = timezone_offset;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public void setDaily(Daily[] daily) {
        this.daily = daily;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", timezone='" + timezone + '\'' +
                ", timezone_offset=" + timezoneOffset +
                ", current=" + current +
                ", daily=" + Arrays.toString(daily) +
                '}';
    }
}
