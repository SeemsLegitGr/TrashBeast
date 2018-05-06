package gr.seemslegit.trashbeast.Fragments;


import android.content.Context;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import com.mapbox.mapboxsdk.Mapbox;

import java.util.ArrayList;
import java.util.List;

import gr.seemslegit.trashbeast.Activities.MainActivity;
import gr.seemslegit.trashbeast.Client.RetrofitClientInstance;
import gr.seemslegit.trashbeast.Client.VillageClient;
import gr.seemslegit.trashbeast.Controllers.MapController;
import gr.seemslegit.trashbeast.Controllers.ScrollMapView;
import gr.seemslegit.trashbeast.Models.Village;
import gr.seemslegit.trashbeast.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private MainActivity mainActivity;
    private ScrollMapView mapView;
    private Toolbar toolbar;
    private MapboxMap map;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.map_page, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Mapbox.getInstance(view.getContext(), getString(R.string.MapBoxKEY));
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        Spinner spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_list, android.R.layout.simple_expandable_list_item_1);
        spinner.setAdapter(adapter);
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
        ((AppCompatActivity) getActivity()).getMenuInflater().inflate(R.menu.map_toolbar_menu, menu);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Check:
                //TODO
                Toast.makeText(getContext(), "Check selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.More:
                //TODO
                Toast.makeText(getContext(), "More Selected", Toast.LENGTH_SHORT)
                        .show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();

    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();

    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();

    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);

    }


    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        map = mapboxMap;
        MapController mapController = new MapController(mapboxMap, mainActivity);
        mapController.OnMapReady("mode");

    }


    public void showBottomSheetDialogFragment(List<Village> villages) {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment(villages);
        bottomSheetFragment.show(getFragmentManager(), bottomSheetFragment.getTag());
    }
}