package proed.hotelbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogInActivity extends AppCompatActivity {

    AdminDatabaseHelper helper = new AdminDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }

    public void onButtonClick(View v){
        if(v.getId()==R.id.admin_login){

            EditText a = (EditText)findViewById(R.id.admin_userName);
            String str = a.getText().toString();

            EditText b =(EditText)findViewById(R.id.admin_password);
            String pass = b.getText().toString();

            String password = helper.searchAdmin(str);

            if (pass.equals(password)) {
                {
                    Intent intent = new Intent(AdminLogInActivity.this, HotelInfoActivity.class);
                    startActivity(intent);
                }
                Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Credentials do not match!", Toast.LENGTH_LONG).show();
            }

            a.setText("");
            b.setText("");

        }
    }
}
