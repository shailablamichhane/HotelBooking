package proed.hotelbooking;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class BookActivity extends AppCompatActivity {
    CustomerDatabaseHelper helper = new CustomerDatabaseHelper(this);
    HotelDatabaseHelper databaseHelper = new HotelDatabaseHelper(this);
    String info = "BOOKING";
    String room_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        room_id = getIntent().getExtras().getString("ID");

        Toast.makeText(BookActivity.this, room_id+"", Toast.LENGTH_SHORT).show();
    }

    public void onBookClick(View view) {
        if (view.getId() == R.id.book_btn) {

            EditText name = findViewById(R.id.customerName);
            EditText email = findViewById(R.id.email);
            EditText address = findViewById(R.id.address);
            EditText phone = findViewById(R.id.phone);
            EditText checkIn = findViewById(R.id.checkIn);
            EditText checkOut = findViewById(R.id.checkOut);

            String customerName = name.getText().toString();
            String customerEmail = email.getText().toString();
            String customerAddress = address.getText().toString();
            String customerPhone = phone.getText().toString();
            String customerCheckIn = checkIn.getText().toString();
            String customerCheckOut = checkOut.getText().toString();

            if (TextUtils.isEmpty(customerName) || TextUtils.isEmpty(customerEmail) || TextUtils.isEmpty(customerAddress) || TextUtils.isEmpty(customerPhone) || TextUtils.isEmpty(customerCheckIn) || TextUtils.isEmpty(customerCheckOut)) {
                Toast.makeText(this, "Do not leave the fields blank!", Toast.LENGTH_SHORT).show();
            } else {
                Bookings bookings = new Bookings();
                bookings.setName(customerName);
                bookings.setEmail(customerEmail);
                bookings.setAddress(customerAddress);
                bookings.setPhone(customerPhone);
                bookings.setCheckIn(customerCheckIn);
                bookings.setCheckOut(customerCheckOut);

                helper.insertBookings(bookings);
                Log.i(info, "Successful");
                Toast.makeText(this, "Room has been booked!", Toast.LENGTH_SHORT).show();
                databaseHelper.updateStatus(room_id);


            }

            name.setText("");
            email.setText("");
            address.setText("");
            phone.setText("");
            checkIn.setText("");
            checkOut.setText("");
        }
    }
}