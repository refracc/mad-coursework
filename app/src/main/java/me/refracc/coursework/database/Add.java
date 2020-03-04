package me.refracc.coursework.database;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
                this.finish();
                break;
            case R.id.add_btnSave:
                String name = ((EditText) findViewById(R.id.add_Name)).getText().toString();
                String abv = ((EditText) findViewById(R.id.add_etABV)).getText().toString();
                String volume = ((EditText) findViewById(R.id.add_etVolume)).getText().toString();
                Object imageURL = ((EditText) findViewById(R.id.add_etImageURL)).getText().toString();
                this.db = new Database(this);
                this.db.insert(name, abv, volume, (byte[]) imageURL);

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
