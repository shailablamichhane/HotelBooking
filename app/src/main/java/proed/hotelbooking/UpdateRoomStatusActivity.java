package proed.hotelbooking;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateRoomStatusActivity extends AppCompatActivity {
    HotelDatabaseHelper helper = new HotelDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_room_status);

        ListView lv = findViewById(R.id.viewRoomStatusList);

        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = helper.setStatus();

        if (cursor.getCount()==0)
        {
            Toast.makeText(this, "Database is Empty", Toast.LENGTH_SHORT).show();
        }

        else
        {
            while (cursor.moveToNext())
            {
                list.add("Room ID: " + "[" + cursor.getString(0)+ "]" + "    "  +
                        "Room Type: " + cursor.getString(1) + "    "  +
                        "Room Price: " + cursor.getString(2) + "    "  +
                        "Hotel Name: " + cursor.getString(3) + "    "  +
                        "Status: " + cursor.getString(4) + "\n\n");

                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, list);
                lv.setAdapter(listAdapter);
            }
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString().trim();
                String id = text.substring(text.indexOf("[") + 1, text.indexOf("]"));
                Toast.makeText(UpdateRoomStatusActivity.this, "The status of" + id +"has been set to Available", Toast.LENGTH_SHORT).show();
                helper.setRoomStatus(id);
            }
        });
    }
}
