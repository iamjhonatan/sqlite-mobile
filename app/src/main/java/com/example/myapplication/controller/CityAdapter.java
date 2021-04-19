package com.example.myapplication.controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.City;
import com.example.myapplication.model.DataModel;


public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_city_recylerview,
                        parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {
        City c = DataModel.getInstance().getCities().get(position);
        holder.textViewCity.setText(c.getName());
        holder.textViewPeople.setText(String.valueOf(c.getPopulation()));
    }

    @Override
    public int getItemCount() {
        return DataModel.getInstance().getCities().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCity;
        TextView textViewPeople;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCity = itemView.findViewById(R.id.textViewCity);
            textViewPeople = itemView.findViewById(R.id.textViewPeople);
        }
    }
}
