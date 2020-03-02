package me.refracc.coursework;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.refracc.coursework.adapters.DrinkAdapter;
import me.refracc.coursework.info.Drink;

public class Volume extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume);

        List<Drink> drinks = new ArrayList<>();

        ListView lv = findViewById(R.id.list_view);

        Drink absinthe = new Drink("Absinthe", "45% ~ 70%", "25ml", "drawable://" + R.drawable.absinthe);
        Drink spirits = new Drink("Spirits", "35% ~ 60%", "25ml", "drawable://" + R.drawable.whisky);
        Drink shots = new Drink("Shots/Liqueurs", "15% ~ 20%", "25ml", "drawable://" + R.drawable.shots);
        Drink wines = new Drink("Wines", "8% ~ 16%", "750ml", "drawable://" + R.drawable.wine);
        Drink beer = new Drink("Beer", "3% ~ 7%", "568ml", "drawable://" + R.drawable.beer);
        Drink lager = new Drink("Lager", "3% ~ 8%", "586ml", "drawable://" + R.drawable.lager);
        Drink cider = new Drink("Cider", "4% ~ 7.5%", "586ml", "drawable://" + R.drawable.cider);
        Drink warn = new Drink("RECOMMENDATION", "All drinks listed are in their", "recommended serving amounts.", null);

        drinks.add(absinthe);
        drinks.add(spirits);
        drinks.add(shots);
        drinks.add(wines);
        drinks.add(beer);
        drinks.add(lager);
        drinks.add(cider);
        drinks.add(warn);

        DrinkAdapter da = new DrinkAdapter(this, R.layout.adapter_layout, drinks);
        lv.setAdapter(da);
    }
}
