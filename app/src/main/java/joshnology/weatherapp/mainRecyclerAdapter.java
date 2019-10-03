package joshnology.weatherapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import java.util.List;

public class mainRecyclerAdapter extends RecyclerView.Adapter<mainRecyclerAdapter.ViewHolder> {

    private List<CityWeather> cityWeathers;
    private Context context;

     mainRecyclerAdapter(List<CityWeather> cityWeather, Context context) {
        this.cityWeathers = cityWeather;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.indiv_city_item_xml,
                    parent, false);
            return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
    final CityWeather cityWeather = cityWeathers.get(position);
    holder.textViewHead.setText(cityWeather.getCityName());
    holder.textViewDescription.setText(cityWeather.getDescription());
    holder.textViewTemperature.setText(Double.toString(cityWeather.getTemperature()) + " º F");


    if(cityWeather.contains(cityWeather.getIcon(), 'd')) {
        holder.cardView.setCardBackgroundColor(Color.rgb(64, 156, 255));
    }else{
        holder.cardView.setCardBackgroundColor(Color.rgb(84,107,171));
    }
        try {
            String Name = cityWeather.getIcon();
            int id = R.drawable.class.getField(Name).getInt(null);
            holder.imageViewIcon.setImageResource(id);
        } catch (Exception e) {
            System.out.println("Something went wrong setting icon." + e);
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(context, WeatherInfoActivity.class);
               intent.putExtra("weather", cityWeather);
               context.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount(){
        return cityWeathers.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
         TextView textViewHead;
         TextView textViewDescription;
         TextView textViewTemperature;
         ImageView imageViewIcon;
         CardView cardView;
         LinearLayout linearLayout;


         ViewHolder(View itemView){
            super(itemView);
            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            textViewTemperature = (TextView) itemView.findViewById(R.id.textViewTemperature);
            imageViewIcon = (ImageView) itemView.findViewById(R.id.imageViewIcon);
            cardView = (CardView) itemView.findViewById(R.id.main_card_view);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.mainCardLayout);
        }
    }



}
