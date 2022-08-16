package com.example.plantTracker;

public class Plant {
    int _id;
    String _name;
    String _date_planted;
    String _date_watered;
    String _water_cycle;
    String _next_cycle_date;

    public Plant(){};
    public Plant(int id, String name, String _date_planted, String _date_watered, String _water_cycle, String _next_cycle_date){
        this._id = id;
        this._name = name;
        this._date_planted = _date_planted;
        this._date_watered = _date_watered;
        this._water_cycle = _water_cycle;
        this._next_cycle_date = _next_cycle_date;
    }

    public Plant(String name, String _date_planted, String _date_watered, String _water_cycle, String _next_cycle_date){
        this._name = name;
        this._date_planted = _date_planted;
        this._date_watered = _date_watered;
        this._water_cycle = _water_cycle;
        this._next_cycle_date = _next_cycle_date;
    }

    public int getID(){
        return this._id;
    }
    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }
    public void setName(String name){
        this._name = name;
    }

    public String getDatePlanted() { return this._date_planted; }
    public void setDatePlanted(String newDate) { this._date_planted = newDate; }

    public String getDateWatered() { return this._date_watered; }
    public void setDateWatered(String newDate) { this._date_watered = newDate; }

    public String getWaterCycle() { return this._water_cycle; }
    public void setWatercycle(String newCycle) { this._water_cycle = newCycle; }

    public String getNextWaterDate() { return this._next_cycle_date; }
    public void setNextWaterDate(String newDate) { this._next_cycle_date = newDate; }
}
