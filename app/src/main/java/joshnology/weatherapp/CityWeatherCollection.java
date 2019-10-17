package joshnology.weatherapp;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.os.Parcelable;

public class CityWeatherCollection {


    private List<CityWeather> cityWeathers = new ArrayList<>();

    //Feeding a literal String with pre-chosen cities just to illistrate the app.
    private String url ="http://api.openweathermap.org/data/2.5/group?id=5368361,1850147,1819729,5128581,524901," +
            "5391811,6539761,5391710,5358705&units=imperial&&APPID=7d91d4786913bbfe02de9040711d6bef";


    public CityWeatherCollection(Context context) {

        RequestQueue queue = Volley.newRequestQueue(context);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    public List<CityWeather> getCityWeathers() {
        return cityWeathers;
    }

    // Request a JSON response from the provided URL.
    private JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url, null,
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
