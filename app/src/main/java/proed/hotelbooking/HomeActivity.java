package proed.hotelbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    HotelDatabaseHelper helper = new HotelDatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(HomeActivity.this, "Welcome!", Toast.LENGTH_LONG).show();
        Button btn_home;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn_home =(Button)findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SearchCountry.class);
                startActivity(intent);
            }
        });
    }

    public void onSearchClick(View view)
    {

        if (view.getId()==R.id.viewHotel)
        {
            Intent intent = new Intent(HomeActivity.this, SearchCountry.class);
            startActivity(intent);

        }
    }

}
