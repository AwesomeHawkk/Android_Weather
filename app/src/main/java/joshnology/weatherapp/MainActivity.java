package joshnology.weatherapp;

import android.content.Intent;
import android.os.Handler;
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
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       final CityWeatherCollection cityWeatherCollection = new CityWeatherCollection(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                List<CityWeather> cityWeathers = new ArrayList<>();
                cityWeathers = cityWeatherCollection.getCityWeathers();
                Intent homeIntent = new Intent(MainActivity.this,WeatherHome.class);

//                homeIntent.putExtra("weather", cityWeathers);
                startActivity(homeIntent);
                finish();


            }
        } , SPLASH_TIME_OUT);




    }



}
