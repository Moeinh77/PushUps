package data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.set;

import static android.content.ContentValues.TAG;


public class DataBaseHandler extends SQLiteOpenHelper {

    private final ArrayList<set> set_list = new ArrayList<>();

    public DataBaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table =
                "CREATE TABLE " + Constants.TABLE_NAME + " ("
                        + Constants.KEY_ID + " INTEGER PRIMARY KEY, "
                        + Constants.TIMES_NAME + " INT, " + Constants.date + " LONG );";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }
////////////////////////////////////////////////////
    public int getTotalsets() {

        String get_total = "SELECT * FROM " + Constants.TABLE_NAME;
            //   +" WHERE "+Constants.KEY_ID +"="+id+";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(get_total, null);
        int count = cursor.getCount();

        cursor.close();
        return count;

    }
    ////////////////////////////////////////////////////

    public int getTotalpushups() {

        SQLiteDatabase db = this.getReadableDatabase();
        int sets = 0;
        String Totalpushups = "SELECT SUM("
                + Constants.TIMES_NAME + ")" + "FROM " + Constants.TABLE_NAME;
        Cursor cursor = db.rawQuery(Totalpushups, null);
        if (cursor.moveToFirst()) {
            sets = cursor.getInt(0);
        }

        db.close();
        cursor.close();
        return sets;
    }

    public void addSet(set Set) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constants.date, System.currentTimeMillis());
        values.put(Constants.TIMES_NAME, Set.getTimes());

        db.insert(Constants.TABLE_NAME, null, values);

        Log.d(TAG, "addSet: Successfully added");
        db.close();
    }

    public ArrayList<set> getSets(){

        set_list.clear();

        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor=db.query(Constants.TABLE_NAME
                ,new String[]{Constants.KEY_ID,Constants.TIMES_NAME,Constants.date}
                ,null,null,null,null,Constants.date+" DESC ");

        if (cursor.moveToFirst()){
            do {
                set Set=new set();
                Set.setSetId(cursor.getInt(cursor.getColumnIndex(Constants.KEY_ID)));
                Set.setTimes(cursor.getInt(cursor.getColumnIndex(Constants.TIMES_NAME)));

                DateFormat dateFormat=DateFormat.getDateInstance();
                String date=dateFormat.format(
                        new Date(cursor.getLong(cursor.getColumnIndex(Constants.date))).getTime()
                );

                Set.setDate(date);

                set_list.add(Set);


            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return set_list;
    }

    public void delete(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID + " =?",
                new String[]{String.valueOf(id)});

        db.close();
    }

}
