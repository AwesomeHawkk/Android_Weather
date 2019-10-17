package joshnology.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class WeatherHome extends AppCompatActivity {
    List<CityWeather> cityWeathers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        //Setting up the recyclerView and Request que variables to populate the page with a list of cities.
        RecyclerView recyclerView;
        RecyclerView.Adapter adapter;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_home);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CityWeatherCollection cityWeatherCollection = new CityWeatherCollection(this);
        cityWeathers = cityWeatherCollection.getCityWeathers();

        try{Thread.sleep(1000);}catch(InterruptedException ex){Thread.currentThread().interrupt();}

        adapter = new mainRecyclerAdapter(cityWeathers, this);
        recyclerView.setAdapter(adapter);
    }


}
