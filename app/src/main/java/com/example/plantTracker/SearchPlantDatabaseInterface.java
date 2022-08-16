package com.example.plantTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class SearchPlantDatabaseInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_plant_database_interface);
        PlantDatabaseHandler pdb = new PlantDatabaseHandler(this);

        List<String> plants = pdb.getPlantNames(pdb);
        ListView itemListView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, plants);
        itemListView.setAdapter(adapter);

        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println(plants.get(i));
                Intent intent = new Intent(view.getContext(), CalendarActivity.class);
                intent.putExtra("data", plants.get(i));
                startActivity(intent);
            }
        });
    }
}