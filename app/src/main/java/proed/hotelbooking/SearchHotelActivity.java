package proed.hotelbooking;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class SearchHotelActivity extends AppCompatActivity {
    HotelDatabaseHelper helper = new HotelDatabaseHelper(this);
    ArrayAdapter arrayAdapter;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_hotel);

        text = getIntent().getExtras().getString("SEND");

        ListView lv = findViewById(R.id.viewHotelList);
        SearchView searchView =findViewById(R.id.searchHotel);

        ArrayList<String> hotelList = new ArrayList();
        Cursor cursor = helper.getHotelName(text);

        if (cursor.getCount()==0)
        {
            Toast.makeText(this, "No lists available!", Toast.LENGTH_SHORT).show();
        }

        else
        {
            while (cursor.moveToNext())
            {
                hotelList.add(cursor.getString(0) + "\n");
                Collections.sort(hotelList);
                arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, hotelList);
                lv.setAdapter(arrayAdapter);
            }
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                arrayAdapter.getFilter().filter(s);
                return false;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString().trim();

                Toast.makeText(SearchHotelActivity.this, name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchHotelActivity.this, SearchRoomActivity.class);
                intent.putExtra("send", name);
                startActivity(intent);
            }
        });
    }
}
