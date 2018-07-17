package proed.hotelbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dell on 3/2/2018.
 */

public class CustomerDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "customers.db";
    private static final String TABLE_CUSTOMER = "customer";

    private static final String booking_table = "CREATE TABLE `customer` ( `id` INTEGER primary key autoincrement not null, `customer_name` TEXT not null, `customer_email` VARCHAR not null, `address` TEXT not null, `number` TEXT not null, `checkIn` VARCHAR, `checkOut` VARCHAR not null )";
    SQLiteDatabase db;
    public CustomerDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(booking_table);
    }

    public void insertBookings(Bookings bookings)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("customer_name", bookings.getName());
        contentValues.put("customer_email", bookings.getEmail());
        contentValues.put("address", bookings.getAddress());
        contentValues.put("number", bookings.getPhone());
        contentValues.put("checkIn", bookings.getCheckIn());
        contentValues.put("checkOut", bookings.getCheckOut());

        db.insert("customer", null, contentValues);
        db.close();

    }

    public Cursor listCustomers()
    {
        db = this.getWritableDatabase();
        Cursor getCustomers = db.rawQuery("SELECT * FROM customer", null);
        return getCustomers;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);

        onCreate(db);
    }
}
