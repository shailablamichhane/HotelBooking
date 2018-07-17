package proed.hotelbooking;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewCustomersActivity extends AppCompatActivity {
    CustomerDatabaseHelper databaseHelper = new CustomerDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);

        ListView listView = findViewById(R.id.viewCustomersList);

        ArrayList<String> list = new ArrayList<>();

        Cursor cursor = databaseHelper.listCustomers();

        if (cursor.getCount()==0)
        {
            Toast.makeText(this, "Database Empty", Toast.LENGTH_SHORT).show();
        }

        else
        {
            while (cursor.moveToNext())
            {
                list.add("Customer ID: " + "[" + cursor.getString(0)+ "]" + "    "  +
                        "Customer Name: " + cursor.getString(1) + "    "  +
                        "Email: " + cursor.getString(2));
                 list.add("Address: " + cursor.getString(3) + "    "  +
                        "Phone: " + cursor.getString(4) + "    "  +
                        "Check-In: " + cursor.getString(5) + "    "  +
                        "Check-Out: " + cursor.getString(6) + "\n\n");

                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, list);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
