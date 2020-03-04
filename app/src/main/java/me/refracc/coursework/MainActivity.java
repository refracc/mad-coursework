package me.refracc.coursework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import me.refracc.coursework.database.Add;
import me.refracc.coursework.database.CheckData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View vol = findViewById(R.id.sprits_volumes);
        vol.setOnClickListener(this);

        View mgmt = findViewById(R.id.management);
        mgmt.setOnClickListener(this);

        View listData = findViewById(R.id.list_data);
        listData.setOnClickListener(this);

        View addCustomDrink = findViewById(R.id.button3);
        addCustomDrink.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.sprits_volumes:
                Intent i = new Intent(this, Volume.class);
                startActivity(i);
                break;
            case R.id.management:
                Intent in = new Intent(this, AlcoholManagement.class);
                startActivity(in);
                break;
            case R.id.list_data:
                Intent iNt = new Intent(this, CheckData.class);
                startActivity(iNt);
                break;
            case R.id.button3:
                Intent inte = new Intent(this, Add.class);
                startActivity(inte);
                break;
        }
    }
}
