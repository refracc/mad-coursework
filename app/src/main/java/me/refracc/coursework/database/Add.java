package me.refracc.coursework.database;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import me.refracc.coursework.R;

public class Add extends Activity implements View.OnClickListener {

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        View saveButton = findViewById(R.id.add_btnSave);
        saveButton.setOnClickListener(this);
        View back = findViewById(R.id.add_btnBack);
        back.setOnClickListener(this);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_btnBack:
                db = new Database(this);
                break;
            case R.id.add_btnSave:
                String name = ((EditText) findViewById(R.id.add_Name)).getText().toString();
                double abv = Double.parseDouble(((EditText) findViewById(R.id.add_etABV)).getText().toString());
                int volume = Integer.parseInt(((EditText) findViewById(R.id.add_etVolume)).getText().toString());

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                this.db = new Database(this);
                this.db.insert(name, abv, volume, dtf.format(LocalDateTime.now()));

                Log.d("INSERT", "this shit in the system");

                showInformationSavedDialog();

                break;
        }
    }

    protected void showInformationSavedDialog() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(Add.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(Add.this);
        }
        builder.setMessage(R.string.add_next_dialog_message);
        builder.setCancelable(false);
        builder.setNegativeButton(R.string.add_next_dialog_confirm_no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        Add.this.finish();
                    }
                });
        builder.setPositiveButton(R.string.add_next_dialog_confirm_yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
