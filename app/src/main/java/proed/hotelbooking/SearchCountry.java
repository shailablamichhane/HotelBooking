package proed.hotelbooking;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Nirzal on 27/11/2017.
 */

public class SearchCountry extends AppCompatActivity
{

    HotelDatabaseHelper helper = new HotelDatabaseHelper(this);
    ArrayAdapter listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_country);

        SearchView searchText = findViewById(R.id.searchView);
        final ListView listView = findViewById(R.id.viewCountryList);


        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = helper.getCountry();

        if (cursor.getCount() == 0) {
            Toast.makeText(SearchCountry.this, "No lists available!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                list.add(cursor.getString(0) + "\n");
                Collections.sort(list);
                listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(listAdapter);

            }
        }

        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {

                listAdapter.getFilter().filter(text);
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString().trim();

                Toast.makeText(SearchCountry.this, text, Toast.LENGTH_SHORT).show();
                String hotelName = text;
                Intent intent = new Intent(SearchCountry.this, SearchHotelActivity.class);
                intent.putExtra("SEND", hotelName);
                startActivity(intent);
            }
        });

    }
}
