package proed.hotelbooking;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Nirzal on 05/12/2017.
 */

public class LogIn extends Activity {
    UserDatabaseHelper helper = new UserDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //getWindow().getDecorView().setBackgroundColor(Color.);
    }
        public void onButtonClick(View v){
            if(v.getId()==R.id.btn_login){

                EditText a = (EditText)findViewById(R.id.uname_login);
                String str = a.getText().toString();

                EditText b = (EditText)findViewById(R.id.pass_login);
                String pass = b.getText().toString();

                String password = helper.searchUser(str);

                if (pass.equals(password))
                {
                    Intent intent = new Intent(LogIn.this,HomeActivity.class);
                    startActivity(intent);
                }

                else
                {
                    Toast.makeText(LogIn.this, "Credentials do not match!", Toast.LENGTH_LONG).show();
                }

                a.setText("");
                b.setText("");
            }
            if (v.getId()==R.id.signup){
                Intent intent = new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
            }

            if (v.getId()==R.id.admin_signIn)
            {
                Intent intent = new Intent(LogIn.this, AdminLogInActivity.class);
                startActivity(intent);
            }
        }
}

