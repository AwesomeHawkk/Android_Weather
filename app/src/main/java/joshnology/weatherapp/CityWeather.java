package joshnology.weatherapp;

import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;
import java.io.Serializable;
import java.util.Calendar;
import android.os.Parcelable;
import android.os.Parcel;

class CityWeather implements Serializable {

    private String cityName;
    private String icon;
    private String description;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private int cityID;
    private long sunrise;
    private long sunset;

     CityWeather() {
        this.cityName = "";
        this.icon = "";
        this.description = "";
        this.temperature = 0 ;
        this.humidity = 0;
        this.cityID = 0;
        this.windSpeed = 0;
        this.sunrise = 0;
        this.sunset = 0;
    }


     String getCityName() {
        return cityName;
    }

     void setCityName(String cityName) {
        this.cityName = cityName;
    }

     String getIcon() {
        return icon;
    }

     void setIcon(String icon) {
        this.icon = icon;
    }

     String getDescription() {
        return description;
    }

     void setDescription(String description) {
        this.description = description;
    }

     double getTemperature() {
        return temperature;
    }

     void setTemperature(double temperature) {
        this.temperature = temperature;
    }

     double getHumidity() {
        return humidity;
    }

     void setHumidity(double humidity) {
        this.humidity = humidity;
    }

     int getCityID() {
        return cityID;
    }

     void setCityID(int cityID) {
        this.cityID = cityID;
    }

     double getWindSpeed() {
        return windSpeed;
    }

     void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

     long getSunrise() {
        return sunrise;
    }

     void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

     long getSunset() {
        return sunset;
    }

     void setSunset(long sunset) {
        this.sunset = sunset;
    }


    CityWeather parseJSON(JSONObject cityDetails){
         CityWeather cityWeather = new CityWeather();

        try{
            cityWeather.setCityName(cityDetails.getString("name"));
            cityWeather.setCityID(cityDetails.getInt("id"));

            JSONArray weatherArray  = cityDetails.getJSONArray("weather");
            JSONObject weather = weatherArray.getJSONObject(0);
            cityWeather.setDescription(weather.getString("description"));
            cityWeather.pngNameConverter(weather.getString("icon"));


            JSONObject main  = cityDetails.optJSONObject("main");
            JSONObject wind  = cityDetails.optJSONObject("wind");
            JSONObject sys  = cityDetails.optJSONObject("sys");

            cityWeather.setTemperature(main.getDouble("temp"));
            cityWeather.setHumidity(main.getDouble("humidity"));
            cityWeather.setWindSpeed(wind.getDouble("speed"));
            cityWeather.setSunrise(sys.getLong("sunrise"));
            cityWeather.setSunset(sys.getLong("sunset"));
        }catch (JSONException e){
            e.printStackTrace();
        }
         return cityWeather;
    }


     private void pngNameConverter(String s){
        String converted = "not found";
        if(s.equals("01d")){
            converted = "one_d";
        }else if(s.equals("01n")){
            converted = "one_n";
        }else if(s.equals("02d")){
            converted = "two_d";
        }else if(s.equals("02n")){
            converted = "two_n";
        }else if(s.equals("03d")){
            converted = "three_d";
        }else if(s.equals("03n")){
            converted = "three_n";
        }else if(s.equals("04d")){
            converted = "four_d";
        }else if(s.equals("04n")){
            converted = "four_n";
        }else if(s.equals("10d")){
            converted = "ten_d";
        }else if(s.equals("10n")){
            converted = "ten_n";
        }else if(s.equals("11d")){
            converted = "eleven_d";
        }else if(s.equals("11n")){
            converted = "eleven_n";
        }else if(s.equals("13d")){
            converted = "thirteen_d";
        }else if(s.equals("13n")){
            converted = "thirteen_n";
        }else if(s.equals("50d")){
            converted = "fifty_d";
        }else if(s.equals("50n")){
            converted = "fifty_n";
        }
        setIcon(converted);
    }

     boolean contains(String str, char chr ) {
        for(int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == chr)
                return true;
        }
        return false;
    }

     String convertUTC(long utc, int offset) {
         long javaTimestamp = utc * 1000L;
         Date date = new Date(javaTimestamp);
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(date);
         calendar.add(Calendar.HOUR_OF_DAY, offset);

         return calendar.getTime().toString().substring(10, 16);
    }



}
