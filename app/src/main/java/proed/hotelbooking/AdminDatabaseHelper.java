package proed.hotelbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dell on 2/19/2018.
 */

public class AdminDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "admins";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "admin";
    public static final String col_name = "userName";
    public static final String col_password = "password";
    private String tag = "Info";
    SQLiteDatabase db;

    public AdminDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE `admin` ( `id` INTEGER primary key autoincrement not null, `userName` TEXT not null, `password` VARCHAR not null)");
        this.db = db;

        ContentValues contentValues = new ContentValues();
        contentValues.put(col_name, "admin");
        contentValues.put(col_password, "admin");

        db.insert(TABLE_NAME, null, contentValues);
        //db.close();

        Log.i(tag, "created");
    }


    public String searchAdmin(String name)
    {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select userName, password from " + TABLE_NAME, null);
        String a, b;
        b = "Not Found";
        if (cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(name))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
}
