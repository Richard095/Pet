package com.example.pets;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView petRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_activity_toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        petRecycler = findViewById(R.id.activity_main_pet_recycler);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        petRecycler.setLayoutManager(gridLayoutManager);


        ArrayList<Pet> petList = new ArrayList<>();

        petList.add(new Pet("Capitano",getString(R.string.pet_description),"Makely","55 55 22 21 23 21",R.drawable.capitano));
        petList.add(new Pet("Catrina",getString(R.string.pet_description),"Richard","55 55 22 21 23 21",R.drawable.catrina));
        petList.add(new Pet("Husk",getString(R.string.pet_description),"Eduardo","55 55 22 21 23 21",R.drawable.husk));
        petList.add(new Pet("Sia",getString(R.string.pet_description),"Miguel","55 55 22 21 23 21",R.drawable.sia));
        petList.add(new Pet("Pal",getString(R.string.pet_description),"Ariosto","55 55 22 21 23 21",R.drawable.pal));
        petList.add(new Pet("Noodles",getString(R.string.pet_description),"Giovannie","55 55 22 21 23 21",R.drawable.noodles));
        petList.add(new Pet("Grey",getString(R.string.pet_description),"Julio","55 55 22 21 23 21",R.drawable.tut));

        PetAdapter petAdapter = new PetAdapter(this,petList);
        petRecycler.setAdapter(petAdapter);


        petAdapter.setOnPetClickListener(new PetAdapter.OnPetClickListener() {
            @Override
            public void OnPetClick(Pet pet) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra(DetailActivity.PET_KEY,pet);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
                startActivity(intent,bundle);
            }
        });
    }
}
