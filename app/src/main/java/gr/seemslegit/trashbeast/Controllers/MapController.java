package gr.seemslegit.trashbeast.Controllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.util.ArrayList;
import java.util.List;

import gr.seemslegit.trashbeast.Client.RetrofitClientInstance;
import gr.seemslegit.trashbeast.Client.VillageClient;
import gr.seemslegit.trashbeast.Models.Village;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapController implements MapboxMap.OnMapClickListener,  MapboxMap.OnMarkerClickListener {
    private static MapboxMap mapboxMap;
    private Activity activity;
    private List<Village> villageDataSet;
    private ArrayList<MarkerOptions> markers = new ArrayList<>();
    ;

    public MapController(MapboxMap mapboxMap, Activity activity) {
        this.mapboxMap = mapboxMap;
        this.activity = activity;
    }

    public void AddMarkers(LatLng coordinates, String name) {
        MarkerOptions options = new MarkerOptions().position(coordinates).title(name);
        markers.add(options);
        mapboxMap.setOnMarkerClickListener(this);
        mapboxMap.addMarker(options);
    }

    public void VillageToMarker(Village village) {
        village.DoubleToLatLng();
        AddMarkers(village.getCoordinates(), village.getName());
    }

    public ArrayList<MarkerOptions> getmarkers() {
        return markers;
    }

    public void OnMapReady(String Mode) {
        GetInfoFromBackEnd();

    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Village> villages) {
        villageDataSet = villages;
        for (int i = 0; i < villages.size(); i++) {
            VillageToMarker(villages.get(i));
        }
        CameraPosition position = new CameraPosition.Builder()
                .target(markers.get(0).getPosition()) // Sets the new camera position
                .zoom(8) // Sets the zoom
                .tilt(12) // Set the camera tilt
                .build(); // Creates a CameraPosition from the builder
        mapboxMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 7000);
    }

    private void GetInfoFromBackEnd() {
        ProgressDialog dialog;
        dialog = new ProgressDialog(activity);
        dialog.setMessage("loading");
        VillageClient service = RetrofitClientInstance.getRetrofitInstance().create(VillageClient.class);
        Call<List<Village>> call = service.getAll();
        dialog.show();
        call.enqueue(new Callback<List<Village>>() {
            @Override
            public void onResponse(Call<List<Village>> call, Response<List<Village>> response) {
                List<Village> villages = response.body();
                villageDataSet = villages;
                dialog.dismiss();
                Log.w("POSTANSKDM", "WE FUCKIN MADE It");
                generateDataList(villages);
            }

            @Override
            public void onFailure(Call<List<Village>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(activity, "we didnt.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onMapClick(@NonNull LatLng point) {
        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(51.50550, -0.07520)) // Sets the new camera position
                .zoom(12) // Sets the zoom
                .bearing(20)
                .tilt(30) // Set the camera tilt
                .build(); // Creates a CameraPosition from the builder
        mapboxMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 7000);
    }


    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        CameraPosition position = new CameraPosition.Builder()
                .target(marker.getPosition()) // Sets the new camera position
                .zoom(12) // Sets the zoom
                .bearing(20)
                .tilt(30) // Set the camera tilt
                .build(); // Creates a CameraPosition from the builder
        mapboxMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), 7000);
        return false;
    }
}




