package me.refracc.coursework.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Database {

    static final String NAME = "database";
    static int VERSION = 1;

    static Context c;
    static SQLiteDatabase db;
    static SQLiteStatement stmt;

    static final String TABLE = "drinks";
    static final String INSERT = "INSERT INTO " + TABLE + "(name, abv, volume, date) VALUES (?,?,?,?)";

    public Database (Context c) {
        Database.c = c;
        OpenHelper openHelper = new OpenHelper(Database.c);
        db = openHelper.getWritableDatabase();
        stmt = db.compileStatement(INSERT);
    }

    public long insert(String name, double abv, int volume, String date) {
        stmt.bindString(1, name);
        stmt.bindDouble(2, abv);
        stmt.bindDouble(3, volume);
        stmt.bindString(4, date);
        return stmt.executeInsert();
    }

    public void deleteAll() {
        db.delete(TABLE, null, null);
    }

    public List<String[]> selectAll() {
        List<String[]> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE, new String[]{"name", "abv", "volume", "date"}, null, null, null, null, "name asc");
        int x = 0;
        if (cursor.moveToFirst()) {
            do {
                String[] b1 = new String[]{cursor.getString(0),
                        cursor.getString(1), cursor.getString(2)};
                list.add(b1);
                x++;
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        cursor.close();
        return list;
    }
}
