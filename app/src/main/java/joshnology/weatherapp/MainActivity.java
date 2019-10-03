package joshnology.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import org.json.*;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    List<CityWeather> cityWeathers = new ArrayList<>();

    //Feeding a literal String with pre-chosen cities just to illistrate the app.
    String url ="http://api.openweathermap.org/data/2.5/group?id=5368361,1850147,1819729,5128581,524901," +
            "5391811,6539761,5391710,5358705&units=imperial&&APPID=7d91d4786913bbfe02de9040711d6bef";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         //Setting up the recyclerView and Request que variables to populate the page with a list of cities.
         RecyclerView recyclerView;
         RecyclerView.Adapter adapter;
         RequestQueue queue = Volley.newRequestQueue(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        try{Thread.sleep(1000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}

        adapter = new mainRecyclerAdapter(cityWeathers, this);
        recyclerView.setAdapter(adapter);
    }


    // Request a JSON response from the provided URL.
    JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("list");
                        for(int i = 0; i < jsonArray.length(); i++){
                            CityWeather cityWeather = new CityWeather();
                            JSONObject cityDetails = jsonArray.getJSONObject(i);
                            cityWeather = cityWeather.parseJSON(cityDetails);
                            cityWeathers.add(cityWeather);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    });
}
