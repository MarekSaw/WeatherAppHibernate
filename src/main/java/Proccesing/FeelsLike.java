package Proccesing;

public class FeelsLike {
    private double day;
    private double night;
    private double eve;
    private double morn;

    public void setDay(double day) {
        this.day = day;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public void setEve(double eve) {
        this.eve = eve;
    }

    public void setMorn(double morn) {
        this.morn = morn;
    }

    @Override
    public String toString() {
        return "FeelsLike{" +
                "day=" + day +
                ", night=" + night +
                ", eve=" + eve +
                ", morn=" + morn +
                '}';
    }
}

