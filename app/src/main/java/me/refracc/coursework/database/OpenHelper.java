package me.refracc.coursework.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class OpenHelper extends SQLiteOpenHelper {

    public OpenHelper(Context context) {
        super(context, Database.NAME, null, Database.VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "
                + Database.TABLE
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, abv REAL, volume REAL)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Database.VERSION = newVersion;
        db.execSQL("DROP TABLE IF EXISTS " + Database.TABLE);
        onCreate(db);
    }
}
