package com.example.plantTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlantDatabaseHandler pdb = new PlantDatabaseHandler(this);

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("MTN"));

        String now = LocalDate.now().toString();
        String[] result = now.split("-", 10);
        System.out.println(result[0] + " " + result[1] + " " + result[2]);

        System.out.println("Should be today: " + cal.getTime());
        cal.set(Integer.parseInt(result[0]), Integer.parseInt(result[1]) - 1, Integer.parseInt(result[2]));
        System.out.println("Should be today: " + cal.getTime());
        



        Date currentDate = new Date();

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        pdb.addPlant(new Plant("Aloe-Vera", LocalDate.now().toString(), LocalDate.now().toString(), "example", "example"));
        pdb.addPlant(new Plant("Potato", LocalDate.now().toString(), LocalDate.now().toString(), "example", "example"));
        pdb.addPlant(new Plant("Mint", LocalDate.now().toString(), LocalDate.now().toString(), "example", "example"));
        pdb.addPlant(new Plant("Onion", LocalDate.now().toString(), LocalDate.now().toString(), "example", "example"));

        // clean up entries with same name
        pdb.deleteSameNameEntries();

        // Reading all contacts
        Log.d("Reading: ", "Reading all plants..");
        List<Plant> plants = pdb.getAllPlants();

        for (Plant plant : plants) {
            String log = "Id: " + plant.getID() + " ,Name: " + plant.getName() + " ,Date Planted: " +
                    plant.getDatePlanted();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

        // Declare variables for buttons and text fields
        TextView DisplayName = findViewById(R.id.addName);
        TextView DisplayPlanted = findViewById(R.id.DisplayPlanted);
        TextView DisplayWatered = findViewById(R.id.DisplayWatered);
        Button fetchData = findViewById(R.id.fetchData);
        Button goToSearchActivity = findViewById(R.id.goToSearch);
        Button goToAddPlantActivity = findViewById(R.id.goToAddPlant);

        // Button On Click Listeners
        fetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Plant plant = pdb.getPlant(1);
                DisplayName.setText(plant.getName());
                DisplayPlanted.setText(plant.getDatePlanted());
                DisplayWatered.setText(plant.getDateWatered());
            }
        });

        goToSearchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SearchPlantDatabaseInterface.class);
                startActivity(intent);
            }
        });

        goToAddPlantActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), addPlant.class);
                startActivity(intent);
            }
        });

    }
}