package proed.hotelbooking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchRoomActivity extends AppCompatActivity {
    HotelDatabaseHelper helper = new HotelDatabaseHelper(this);
    ArrayAdapter adapter;
    String hotelName;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_room);

        hotelName = getIntent().getExtras().getString("send");

        ListView listView = findViewById(R.id.viewRoomList);

        ArrayList<String> roomList = new ArrayList<>();
        final Cursor cursor = helper.getRoom(hotelName);

        if (cursor.getCount()==0)
        {
            Toast.makeText(this, "No list available", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                roomList.add("Room ID: " + "[" +cursor.getString(3)+ "]" + "    "  + "Room Type: " + cursor.getString(0) + "    "  + " Room Price: " + cursor.getString(1) + "    "  +
                        " Status: " + cursor.getString(2) + "\n\n");

                adapter= new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, roomList);

                listView.setAdapter(adapter);
            }

        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString().trim();
                id = text.substring(text.indexOf("[") + 1, text.indexOf("]"));

                Toast.makeText(SearchRoomActivity.this, id+"" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchRoomActivity.this, BookActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);


            }
        });
    }
}
