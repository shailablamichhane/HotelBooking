package proed.hotelbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RoomInfoActivity extends AppCompatActivity {
    HotelDatabaseHelper helper = new HotelDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);
    }

    public void onRoomButtonClick(View v)
    {
        if (v.getId()==R.id.addRoom) {
            //EditText id = findViewById(R.id.roomId);
            EditText type = findViewById(R.id.roomType);
            EditText price = findViewById(R.id.roomPrice);
            EditText hotel_id = findViewById(R.id.hotelID);
            EditText roomStatus = findViewById(R.id.roomStatus);

            //String room_id = id.getText().toString();
            String room_type = type.getText().toString();
            String room_price = price.getText().toString();
            String hotelID = hotel_id.getText().toString();
            String status = roomStatus.getText().toString();

            if (TextUtils.isEmpty(room_type) || TextUtils.isEmpty(room_price) || TextUtils.isEmpty(hotelID)) {
                Toast.makeText(this, "Please do not leave any field blank! ", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Hotels hotels = new Hotels();
                //hotels.setRoom_id(room_id);
                hotels.setRoom_price(room_price);
                hotels.setRoom_type(room_type);
                hotels.setHotel_name(hotelID);
                hotels.setRoomStatus(status);

                helper.insertRoom(hotels);
                Toast.makeText(this, "Data Added To The Database", Toast.LENGTH_SHORT).show();
            }

            type.setText("");
            price.setText("");
            hotel_id.setText("");
            roomStatus.setText("");
        }
    }

    public void onViewRoomButtonClick(View view)
    {
        if (view.getId()==R.id.viewRoom)
        {
            Intent intent = new Intent(RoomInfoActivity.this, ViewRoomContents.class);
            startActivity(intent);
        }
    }

    public void onUpdateStatusButtonClick(View view)
    {
        if (view.getId()==R.id.updateStatus)
        {
            Intent intent = new Intent(RoomInfoActivity.this, UpdateRoomStatusActivity.class);
            startActivity(intent);
        }
    }
}
