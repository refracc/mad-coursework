package me.refracc.coursework.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class Database {

    static final String NAME = "database";
    static int VERSION = 1;

    private Context c;
    private static SQLiteDatabase db;
    private static SQLiteStatement stmt;

    static final String TABLE = "drinks";
    static final String INSERT = "INSERT INTO " + TABLE + "(name, abv, volume, img) VALUES (?,?,?,?)";

    public Database (Context c) {
        this.c = c;
        OpenHelper openHelper = new OpenHelper(this.c);
        db = openHelper.getWritableDatabase();
        this.stmt = db.compileStatement(INSERT);
    }
    
    public long insert(String name, String abv, String volume, byte[] img) {
        this.stmt.bindString(1, name);
        this.stmt.bindString(2, abv);
        this.stmt.bindString(3, volume);
        this.stmt.bindBlob(4, img);
        return this.stmt.executeInsert();
    }

    public void deleteAll() {
        db.delete(TABLE, null, null);
    }

    public List<Object[]> selectAll() {
        List<Object[]> list = new ArrayList<>();
        Cursor cursor = db.query(TABLE, new String[]{"name", "abv", "volume", "img"}, null, null, null, null, "name asc");
        int x = 0;
        if (cursor.moveToFirst()) {
            do {
                Object[] b1 = new Object[]{cursor.getString(0),
                        cursor.getString(1), cursor.getString(2),
                        cursor.getBlob(3)};
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
