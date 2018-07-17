package proed.hotelbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.widget.Toast;

/**
 * Created by Dell on 1/30/2018.
 */

public class UserDatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users";
    private static final String TABLE_NAME = "user";
    //private static final String col_id = "id";
    private static final String col_name = "userName";
    private static final String col_email = "email";
    private static final String col_password = "password";

    public UserDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    SQLiteDatabase db;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE `user` ( `id` INTEGER primary key autoincrement not null, `userName` TEXT not null, `email` VARCHAR not null, `password` VARCHAR not null)");
        this.db = db;
    }

    public  void  insertUser(Users c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //Cursor cur = db.rawQuery("select * from users", null);

       //int count = cur.getCount();

        //values.put(col_id, count);
        values.put(col_name, c.getUserName());
        values.put(col_email, c.getEmail());
        values.put(col_password, c.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public  String  searchUser(String uname)
    {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select userName, password from " +TABLE_NAME, null);
        String a, b;
        b = "Not Found!";
        if(cursor.moveToFirst())
        {
            do {
                a = cursor.getString(0);

                if (a.equals(uname))
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
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        this.onCreate(db);
    }
}
