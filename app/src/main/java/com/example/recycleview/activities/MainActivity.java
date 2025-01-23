package com.example.recycleview.activities;

import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recycleview.R;
import java.util.ArrayList;
import com.example.recycleview.classes.CustomeAdapter;
import com.example.recycleview.classes.MyData;
import com.example.recycleview.Models.DataModel;

public class MainActivity extends AppCompatActivity
{
    private ArrayList<DataModel> dataSet;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dataSet = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < MyData.nameArray.length ; i++){
            dataSet.add(new DataModel(
                    MyData.nameArray[i],
                    MyData.descriptionArray[i],
                    MyData.drawableArray[i],
                    MyData.id_[i]
            ));
        }

        adapter = new CustomeAdapter(dataSet);
        recyclerView.setAdapter(adapter);

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return false;
            }
        });
    }
}