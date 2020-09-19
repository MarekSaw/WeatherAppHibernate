package Proccesing.coordinates;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coord {
    @JsonProperty("lon")
    private double longitude;

    @JsonProperty("lat")
    private double latitude;

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
