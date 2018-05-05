package gr.seemslegit.trashbeast.Controllers;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.util.ArrayList;

import gr.seemslegit.trashbeast.Models.Village;

public class MapController {
    private static MapboxMap mapboxMap;

    public MapController(MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
    }
    public static void AddMarkers (LatLng coordinates, String name){
        MarkerOptions options = new MarkerOptions().position(coordinates).title(name);
        mapboxMap.addMarker(options);
    }
    public void OnMapReady(String Mode) {

        mapboxMap.addMarker(new MarkerOptions().position(new LatLng(41.885, -87.679))
                .title("Chicago")
                .snippet("Illinois")
        );
    }
}
