package gr.seemslegit.trashbeast.Controllers;

import android.content.Context;

import com.mapbox.directions.DirectionsCriteria;
import com.mapbox.directions.MapboxDirections;
import com.mapbox.directions.service.models.DirectionsResponse;
import com.mapbox.directions.service.models.DirectionsRoute;
import com.mapbox.directions.service.models.Waypoint;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.List;

import gr.seemslegit.trashbeast.R;

import retrofit.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DirectionController {
    MapboxDirections client;
    Context context;


    public DirectionController(Context context) {
        this.context = context;
    }

    public void request(LatLng origin, LatLng destination) {
        Waypoint originWaypoint = new Waypoint(origin.getLongitude(), origin.getLatitude());
        Waypoint destinationWaypoint = new Waypoint(destination.getLongitude(), destination.getLatitude());

        client = new MapboxDirections.Builder().setAccessToken(context.getResources().getString(R.string.MapBoxKEY))
                .setOrigin(originWaypoint)
                .setDestination(destinationWaypoint)
                .setProfile(DirectionsCriteria.PROFILE_DRIVING)
                .build();

        client.enqueue(new retrofit.Callback<DirectionsResponse>() {
            @Override
            public void onResponse(retrofit.Response<DirectionsResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    DirectionsRoute route = response.body().getRoutes().get(0);
                    int distance = route.getDistance(); //meters
                    CalculateDraw(route);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });


    }

    public void CalculateDraw(DirectionsRoute route) {
        List<Waypoint> waypoints = route.getGeometry().getWaypoints();

        LatLng[] points = new LatLng[waypoints.size()];
        for (int i = 0; i < waypoints.size(); i++) {
            points[i] = new LatLng(waypoints.get(i).getLatitude(),
                    waypoints.get(i).getLongitude());
        }
        MapController.DrawPointsOnMap(points);
    }
}
