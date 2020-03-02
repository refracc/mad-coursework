package me.refracc.coursework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.sprits_volumes);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activityA = new Intent(MainActivity.this, Volume.class);
                startActivity(activityA);
            }
        });

        Button mgmt = findViewById(R.id.management);
        mgmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alc_mgmt = new Intent(MainActivity.this, AlcoholManagement.class);
                startActivity(alc_mgmt);
            }
        });

    }
}
