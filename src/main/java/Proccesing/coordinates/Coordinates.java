package Proccesing.coordinates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {
    private Coord coord;

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Coord getCoord() {
        return coord;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "coord=" + coord +
                '}';
    }
}
