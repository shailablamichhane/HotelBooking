package proed.hotelbooking;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Dell on 2/21/2018.
 */

public class ViewRoomContents extends AppCompatActivity {

    HotelDatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_rooms_layout);

        ListView listView = findViewById(R.id.viewRoomList);
        db = new HotelDatabaseHelper(this);

        ArrayList<String> list = new ArrayList<>();
        Cursor data = db.getListContents();

        if(data.getCount() == 0)
        {
            Toast.makeText(this, "Empty Database", Toast.LENGTH_SHORT).show();
        }

        else
        {
            while (data.moveToNext())
            {
                list.add("Room ID: " + data.getString(0));
                list.add("Room Type: " + data.getString(1) + "    "  +
                        "Room Price: " + data.getString(2) + "    "  +
                        "Hotel Name: " + data.getString(3) + "    "  +
                        "Status: " + data.getString(4) + "\n\n");
                //list.add(" Room Price: " + data.getString(2) + "\n");
                //list.add(" Hotel ID: " + data.getString(3) + "\n\n");
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
