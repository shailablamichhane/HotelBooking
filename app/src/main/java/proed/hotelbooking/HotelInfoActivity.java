package proed.hotelbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class HotelInfoActivity extends AppCompatActivity {

    HotelDatabaseHelper helper = new HotelDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_info);
    }

    public void onAddButtonClick(View v)
    {
        if (v.getId()==R.id.hotelAdd)
        {
            //EditText id = (EditText)findViewById(R.id.hotel_id);
            EditText name = (EditText)findViewById(R.id.hotelName);
            EditText location = (EditText)findViewById(R.id.location);

            //String hotelId = id.getText().toString();
            String hotelName = name.getText().toString();
            String hotelLocation = location.getText().toString();

            if(TextUtils.isEmpty(hotelName) || TextUtils.isEmpty(hotelLocation)) {
                Toast.makeText(this, "Please do not leave any field blank! ", Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {
                Hotels h = new Hotels();
                //h.setId(hotelId);
                h.setHotelName(hotelName);
                h.setLocation(hotelLocation);

                helper.insertHotel(h);
                Toast.makeText(this, "Data Added To The Database", Toast.LENGTH_SHORT).show();
            }

            name.setText("");
            location.setText("");
        }
    }

    public void onRoomAddButtonClick(View view)
    {
        if (view.getId()==R.id.roomAdd)
        {
            Intent intent = new Intent(HotelInfoActivity.this, RoomInfoActivity.class);
            startActivity(intent);
        }
    }


    public  void onViewHotelButtonClick(View view)
    {
        if (view.getId()==R.id.viewHotel)
        {
            Intent intent = new Intent(HotelInfoActivity.this, ViewHotelContents.class);
            startActivity(intent);
        }
    }

    public void onViewCustomerButtonClick(View view)
    {
        if (view.getId()==R.id.viewCustomers)
        {
            Intent intent = new Intent(HotelInfoActivity.this, ViewCustomersActivity.class);
            startActivity(intent);
        }
    }
}
