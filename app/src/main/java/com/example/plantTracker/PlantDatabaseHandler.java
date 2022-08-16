package com.example.plantTracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PlantDatabaseHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PlantManager";
    private static final String TABLE_PLANTS = "Plants";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE_PLANTED = "date_planted";
    private static final String KEY_DATE_WATERED = "date_watered";
    private static final String KEY_WATER_CYCLE = "water_cycle";
    private static final String KEY_NEXT_WATERING = "next_watering";

    public PlantDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PLANTS = "CREATE TABLE " + TABLE_PLANTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DATE_PLANTED + " TEXT," + KEY_DATE_WATERED + " TEXT," +
                KEY_WATER_CYCLE + " TEXT," + KEY_NEXT_WATERING + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_PLANTS);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANTS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new plant
    void addPlant(Plant plant) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, plant.getName()); // Plant Name
        values.put(KEY_DATE_PLANTED, plant.getDatePlanted()); // Date of the when plant was sowed
        values.put(KEY_DATE_WATERED, plant.getDateWatered()); // late date plant was watered
        values.put(KEY_WATER_CYCLE, plant.getWaterCycle());   // How often should I water
        values.put(KEY_NEXT_WATERING, plant.getNextWaterDate()); // Next date I should water

        // Inserting Row
        db.insert(TABLE_PLANTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single plant
    Plant getPlant(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PLANTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_DATE_PLANTED, KEY_DATE_WATERED }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Plant plant = new Plant(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        // return plant
        return plant;
    }

    // code to get all plant in a list view
    public List<Plant> getAllPlants() {
        List<Plant> plantList = new ArrayList<Plant>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PLANTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Plant plant = new Plant();
                plant.setID(Integer.parseInt(cursor.getString(0)));
                plant.setName(cursor.getString(1));
                plant.setDatePlanted(cursor.getString(2));
                plant.setDateWatered(cursor.getString(3));
                plant.setWatercycle(cursor.getString(4));
                plant.setNextWaterDate(cursor.getString(5));
                // Adding plant to list
                plantList.add(plant);
            } while (cursor.moveToNext());
        }

        // return plant list
        return plantList;
    }

    // get all the names of the plants in a String List
    public List<String> getPlantNames(PlantDatabaseHandler pdb) {
        List<Plant> plants = pdb.getAllPlants();
        ArrayList<String> namesOfPlants = new ArrayList<String>();
        for (Plant plant : plants) {
            String log = plant.getName() + ", Date Planted: " + plant.getDatePlanted();
            namesOfPlants.add(log);
        }
        return namesOfPlants;
    }

    public Boolean containsEntryByName(String name) {
        List<Plant> plants = getAllPlants();
        for (Plant cn : plants) {
            if (cn._name == "name") return true;
        }
        return false;
    }

    public void deleteSameNameEntries() {
        List<Plant> entries = getAllPlants();
        List<String> unique = new ArrayList<String>();
        for (Plant cn : entries) {
            if (unique.contains(cn._name))  deletePlant(cn);
            else unique.add(cn._name);
        }
    }

    // code to update the single plant
    public int updatePlant(Plant plant) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, plant.getName()); // Plant Name
        values.put(KEY_DATE_PLANTED, plant.getDatePlanted()); // Date of the when plant was sowed
        values.put(KEY_DATE_WATERED, plant.getDateWatered()); // late date plant was watered
        values.put(KEY_WATER_CYCLE, plant.getWaterCycle());   // How long the water cycle is
        values.put(KEY_NEXT_WATERING, plant.getNextWaterDate());

        // updating row
        return db.update(TABLE_PLANTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(plant.getID()) });
    }

    // Deleting single plant
    public void deletePlant(Plant plant) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLANTS, KEY_ID + " = ?",
                new String[] { String.valueOf(plant.getID()) });
        db.close();
    }

    // Getting plant Count
    public int getPlantCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PLANTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
