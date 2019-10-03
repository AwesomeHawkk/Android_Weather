package joshnology.weatherapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textViewCityName = (TextView) findViewById(R.id.WI_cityName_textView);
        TextView textViewTemperature = (TextView) findViewById(R.id.WI_temperature_textView);
        TextView textViewInfo = (TextView) findViewById(R.id.WI_info_textView);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textViewWind = (TextView) findViewById(R.id.textViewWind);
        TextView textViewHumidity = (TextView) findViewById(R.id.textViewHunidity);
        TextView textViewSunrise = (TextView) findViewById(R.id.textViewSunrise);
        TextView textViewSunset = (TextView) findViewById(R.id.textViewSunset);

        Intent i = getIntent();
        CityWeather cityWeather = (CityWeather)i.getSerializableExtra("weather");
            try {
                String Name = cityWeather.getIcon();
                int id = R.drawable.class.getField(Name).getInt(null);
                imageView.setImageResource(id);
            } catch (Exception e) {
                System.out.println("Something went wrong setting icon." + e);
           }

            textViewCityName.setText(cityWeather.getCityName());
            textViewTemperature.setText(Double.toString(cityWeather.getTemperature()) + "º F");
            textViewInfo.setText(cityWeather.getDescription());
            textViewHumidity.setText(Double.toString(cityWeather.getHumidity()) + "%");
            textViewWind.setText(Double.toString(cityWeather.getWindSpeed()) + " mph");
            textViewSunrise.setText(cityWeather.convertUTC(cityWeather.getSunrise(), -7) + "am");
            textViewSunset.setText(cityWeather.convertUTC(cityWeather.getSunset(), 5) + "pm");

        //Dynamically setting the background color based on if it's day or night
        if(cityWeather.contains(cityWeather.getIcon(), 'd')) {
            getWindow().getDecorView().setBackgroundColor(Color.rgb(64, 156, 255));
        }else{
            getWindow().getDecorView().setBackgroundColor(Color.rgb(84,107,171));
        }

    }
}
