package proed.hotelbooking;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewHotelContents extends AppCompatActivity {

    HotelDatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hotel_contents);

        ListView listView  = findViewById(R.id.viewHotelList);
        helper = new HotelDatabaseHelper(this);

        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = helper.getHotelListContents();

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "Empty Database", Toast.LENGTH_SHORT).show();
        }

        else
        {
            while (cursor.moveToNext())
            {
                list.add("Hotel ID: " + cursor.getString(0));
                list.add("Hotel Name: " + cursor.getString(1) + "      " +
                        "Location: " + cursor.getString(2) + "\n\n");

                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
