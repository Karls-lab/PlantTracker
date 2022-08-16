package com.example.plantTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.time.LocalDate;

public class addPlant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);
        PlantDatabaseHandler pdb = new PlantDatabaseHandler(this);

        EditText newPlantCycle = findViewById(R.id.inputWaterFrequency);
        EditText newPlantName = findViewById(R.id.editTextPlantName);
        Button createNewPlant = findViewById(R.id.createButton);


        createNewPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = newPlantName.getText().toString();
                String waterCycle = newPlantCycle.getText().toString();
                Plant newPlant = new Plant(name,  LocalDate.now().toString(),  LocalDate.now().toString(), waterCycle, "today");
                pdb.addPlant(newPlant);
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}