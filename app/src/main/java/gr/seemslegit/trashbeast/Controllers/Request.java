package gr.seemslegit.trashbeast.Controllers;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mapbox.mapboxsdk.geometry.LatLng;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import gr.seemslegit.trashbeast.Activities.MainActivity;
import gr.seemslegit.trashbeast.Models.Village;

import static android.content.ContentValues.TAG;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

public class Request {
    private  String baseUrl;
    private String url;
    private RequestQueue requestQueue;
    private ArrayList<Village> villageList;
    private Activity activity;

    public Request (Activity activity){
        new GetVillages().execute();
        this.activity  = activity;
    }
    public void HttpJsonrequest(Context context){
        requestQueue = Volley.newRequestQueue(context);
    }

    private class GetVillages extends AsyncTask <Void ,Void ,Void>  {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
      //  Toast.makeText(activity.getApplicationContext(),"Json Data is downloading",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.w("WE MADE IT","POST MALONE");
        //activity.
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "http://10.0.0.124:8080/getAllVillages";
        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray villages = jsonObj.getJSONArray("village");

                // looping through All Contacts
                for (int i = 0; i < villages.length(); i++) {
                    JSONObject c = villages.getJSONObject(i);
                    String id = c.getString("id");
                    String name = c.getString("name");
                    Double Lat = c.getDouble("latitude");
                    Double Lng = c.getDouble("longitude");
                    LatLng latLng = new LatLng(Lat,Lng);
                    //Village village = new Village(name,latLng,false);
                   // MapController.AddMarkers(village.MarkVillage());

                    //villageList.add(village);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(activity.getApplicationContext(),
                           //     "Json parsing error: " + e.getMessage(),
                               // Toast.LENGTH_LONG).show();
                    }
                });

            }

        } else {
            Log.e(TAG, "Couldn't get json from server.");
        activity.runOnUiThread(new Runnable() {
             @Override
             public void run() {

             }
         });
        }

        return null;
    }


}

}
