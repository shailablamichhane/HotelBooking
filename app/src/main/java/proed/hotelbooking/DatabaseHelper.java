package proed.hotelbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nirzal on 15/09/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "hotelbook";
    public static final String TABLE_NAME = "hotelbooking";
    public static final String col_1 = "id";
    public static final String col_2 = "name";
    public static final String col_3 = "no";
    public static final String col_4 = "hotel";
    public static final String col_5 = "room";
    SQLiteDatabase db;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE `hotelbooking` ( `id` INTEGER, `name` TEXT, `no` INTEGER, `hotel` TEXT, `room` TEXT, PRIMARY KEY(`id`) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String no,String booked_hotel,String room){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_2,name);
        contentValues.put(col_3,no);
        contentValues.put(col_4,booked_hotel);
        contentValues.put(col_5,room);
        long result = db.insert(TABLE_NAME,null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " +TABLE_NAME, null);
        return res;
    }

    public  boolean searchRoom(String room)
    {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select room from " + TABLE_NAME, null);
        String a;
        boolean b = false;
        if (cursor.moveToFirst())
        {
            do
            {
                a = cursor.getString(0);
                if (a.equals(room))
                {
                    b = true;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }
}
