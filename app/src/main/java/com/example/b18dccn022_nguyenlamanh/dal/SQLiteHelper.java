package com.example.b18dccn022_nguyenlamanh.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.b18dccn022_nguyenlamanh.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Congviec.db";
    private static int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateDB = "CREATE TABLE items(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ten TEXT," +
                "noidung TEXT," +
                "date TEXT," +
                "tinhtrang TEXT," +
                "congtac INT)";
        sqLiteDatabase.execSQL(sqlCreateDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //them
    public void addItem(Item i) {
        String sql = "INSERT INTO items(ten,noidung,date,tinhtrang, congtac)" +
                "VALUES(?,?,?,?,?)";
        String[] args = {i.getTen(), i.getNoidung(), i.getDate(), i.getTinhtrang(), String.valueOf(i.getCongtac())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    //get all
    public List<Item> getAll() {
        List<Item> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String order = "date DESC";
        Cursor rs = sqLiteDatabase.query("items",
                null, null, null,
                null, null, order);
        while ((rs != null) && (rs.moveToNext())) {
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String date = rs.getString(3);
            String tinhtrang = rs.getString(4);
            int congtac = rs.getInt(5);
            list.add(new Item(id, ten, noidung, date, tinhtrang, congtac));
        }
        return list;
    }

    //update
    public int update(Item i) {
        ContentValues values = new ContentValues();
        values.put("ten", i.getTen());
        values.put("noidung", i.getNoidung());
        values.put("date", i.getDate());
        values.put("tinhtrang", i.getDate());
        values.put("congtac", i.getCongtac());

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = {Integer.toString(i.getId())};

        return sqLiteDatabase.update("items", values, whereClause, whereArgs);
    }

    //delete
    public int delete(int id) {
        String whereClause = "id=?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("items", whereClause, whereArgs);
    }

    //search item by title
    public List<Item> searchByTitle(String key) {
        List<Item> list = new ArrayList<>();
        String whereClause = "ten like ?";
        String[] whereArgs = {"%" + key + "%"};
        String order = "date DESC";
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null, whereClause, whereArgs, null, null, order);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String date = rs.getString(3);
            String tinhtrang = rs.getString(4);
            int congtac = rs.getInt(5);
            list.add(new Item(id, ten, noidung, date, tinhtrang, congtac));
        }
        return list;
    }

    //search item by category
    public List<Item> searchByCategory(String tinhtrang) {
        List<Item> list = new ArrayList<>();
        String whereClause = "tinhtrang like ?";
        String[] whereArgs = {tinhtrang};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null, whereClause, whereArgs, null, null, null);
        while (rs != null && rs.moveToNext()) {
            int id = rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String date = rs.getString(3);
            int congtac = rs.getInt(5);
            list.add(new Item(id, ten, noidung, date, tinhtrang, congtac));
        }
        return list;
    }
}
