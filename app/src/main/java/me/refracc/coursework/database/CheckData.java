package me.refracc.coursework.database;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import me.refracc.coursework.R;

public class CheckData extends ListActivity {

    TextView selection;
    Database db;
    List<String[]> drinks = null;
    String[] stg1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_layout);

        db = new Database(this);
        drinks = db.selectAll();
        stg1 = new String[drinks.size()];
        int x = 0;
        String stg;

        DecimalFormat df = new DecimalFormat("#.##");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (String[] drink : drinks) {
            double units = ((Double.parseDouble(drink[1])/100) * Integer.parseInt(drink[2])) / 10;

            stg = drink[0] + "    -    "
                    + drink[1] + "    -    "
                    + drink[2] + "    -    "
                    + df.format(units) + " units    -    "
                    + dtf.format(LocalDateTime.now());
            stg1[x] = stg;
            x++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                stg1);
        this.setListAdapter(adapter);
        selection = findViewById(R.id.check_selection);
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        selection.setText(stg1[position]);
    }
}