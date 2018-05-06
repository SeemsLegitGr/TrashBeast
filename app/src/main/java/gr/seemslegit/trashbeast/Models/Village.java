package gr.seemslegit.trashbeast.Models;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;

import gr.seemslegit.trashbeast.Controllers.MapController;

public class Village {
    private String name;
    private Double latitude;
    private Double longitude;
    private LatLng coordinates;
    private boolean passed;

    public Village(String name, Double lng , Double lat, boolean passed) {
        this.name = name;
        this.longitude = lng;
        this.latitude = lat;
        this.passed = passed;
    }

    public void DoubleToLatLng(){
        coordinates = new LatLng(latitude,longitude);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getCoordinates() {
        DoubleToLatLng();
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

}
