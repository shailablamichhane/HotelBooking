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

public class HotelDatabaseHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "hotels";
    private static final String TABLE_NAME = "hotel";
    private static final String TABLE_ROOM = "room";
    private static final String col_id = "id";
    private static final String col_name = "hotelName";
    private static final String col_location = "location";
    private static final String room_id = "room_id";
    private static final String room_type = "room_type";
    private static final String room_price = "room_price";
    private static final String hotel_name = "hotel_name";
    private static final String room_status = "room_status";
    private static final String hotel_table = "CREATE TABLE `hotel` ( `id` INTEGER primary key autoincrement not null, `hotelName` TEXT not null, `location` TEXT not null )";
    private static final String room_table ="CREATE TABLE IF NOT EXISTS `room` ( `room_id` INTEGER primary key autoincrement not null, `room_type` TEXT not null, `room_price` TEXT not null, `hotel_name` TEXT not null, 'room_status' TEXT not null )";
    SQLiteDatabase db;
    private static final String info = "DATABASE";
    public HotelDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(info, "Created!!!!!!!!!");

        db.execSQL(hotel_table);
        db.execSQL(room_table);

    }

    public void insertHotel(Hotels h)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(col_id, h.getId());
        values.put(col_name, h.getHotelName());
        values.put(col_location, h.getLocation());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void insertRoom(Hotels hotels)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //values.put(room_id, hotels.getRoom_id());
        values.put(room_price, hotels.getRoom_price());
        values.put(room_type, hotels.getRoom_type());
        values.put(hotel_name, hotels.getHotel_name());
        values.put(room_status, hotels.getRoomStatus());

        db.insert(TABLE_ROOM, null, values);
        db.close();
    }

    public Cursor getListContents()
    {
        db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM room ", null);
        return data;
    }

    public Cursor getHotelListContents()
    {
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM hotel", null);
        return cursor;
    }

    public Cursor getCountry()
    {
        db = this.getWritableDatabase();
        Cursor searchCountry =  db.rawQuery("SELECT DISTINCT location FROM hotel", null);
        return searchCountry;
    }

    public Cursor getHotelName(String name)
    {
        db = this.getWritableDatabase();
        Cursor searchHotel = db.rawQuery("SELECT hotelName FROM hotel WHERE location ='"+name+"'" , null);
        return searchHotel;
    }

    public Cursor getRoom(String roomName)
    {
        db = this.getWritableDatabase();
        Cursor searchRoom = db.rawQuery("SELECT room_type, room_price, room_status, room_id FROM room WHERE TRIM(hotel_name) ='"+roomName.trim()+"'  AND `room_status` = 'Available'", null);
        return  searchRoom;
    }


    public void updateStatus(String id)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(room_status, "Unavailable");
        db.update(TABLE_ROOM, contentValues, "room_id = ?", new String[]{id});
    }

    public Cursor setStatus()
    {
        db= this.getWritableDatabase();
        Cursor getStatus = db.rawQuery("SELECT * FROM ROOM WHERE `room_status` = 'Unavailable'", null);
        return getStatus;
    }

    public void setRoomStatus(String id)
    {
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(room_status, "Available");
        db.update(TABLE_ROOM, contentValues, "room_id = ?", new String[]{id});
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);

        onCreate(db);
    }
}
