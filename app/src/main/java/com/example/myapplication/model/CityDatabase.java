package com.example.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class CityDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "cities.sqlite";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "City";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_POPULATION = "population";

    private Context context;


    public CityDatabase (Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS " + DB_TABLE + "("+
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_POPULATION + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long createCityInDB(City city) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, city.getName());
        values.put(COL_POPULATION, city.getPopulation());
        long id = database.insert(DB_TABLE, null, values);
        database.close();

        return id;
    }

    public ArrayList<City> retrieveCitiesFromDB() {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(DB_TABLE, null, null, null, null, null, null);
        ArrayList<City> cities = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndex(COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                int population = cursor.getInt(cursor.getColumnIndex(COL_POPULATION));

                City c = new City (id, name, population);
                cities.add(c);
            } while (cursor.moveToNext());
        }

        database.close();
        return cities;
    }
}
