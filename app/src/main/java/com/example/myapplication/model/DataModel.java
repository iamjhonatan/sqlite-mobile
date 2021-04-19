package com.example.myapplication.model;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class DataModel {
    private static DataModel instance = new DataModel();

    private DataModel() {
    }

    public static DataModel getInstance() {
        return instance;
    }

    private CityDatabase database;
    private ArrayList<City> cities;
    private Context context;

    public void setContext (Context context) {
        this.context = context;
        database = new CityDatabase(context);
        cities = database.retrieveCitiesFromDB();

        //addCity(new City("Curitiba", 2000000));
        //addCity(new City("Rio de Janeiro", 6747815));
        //addCity(new City("São Paulo", 12325232));
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void addCity(City city) {
        long id = database.createCityInDB(city);

        if (id > 0) {
            city.setId(id);
            cities.add(city);
        }
        else {
            Toast.makeText(context, "Add city problem", Toast.LENGTH_LONG).show();
        }
    }

}
