package gr.seemslegit.trashbeast.Models;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;

public class Village {
    private String name;
    private LatLng coordinates;
    private boolean passed;

    public Village(String name, LatLng coordinates, boolean passed) {
        this.name = name;
        this.coordinates = coordinates;
        this.passed = passed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public MarkerOptions MarkVillage(){
        MarkerOptions markerOptions = new MarkerOptions().position(coordinates).title(name).snippet(name);
        return markerOptions;
    }
}
