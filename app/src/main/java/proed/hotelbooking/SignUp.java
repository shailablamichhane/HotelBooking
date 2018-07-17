package proed.hotelbooking;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Nirzal on 05/12/2017.
 */

public class SignUp extends Activity {
    UserDatabaseHelper helper = new UserDatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singup);
        //getWindow().getDecorView().setBackgroundColor(Color.CYAN);

    }
    public void onSignUpClick(View v){
        if (v.getId()==R.id.btn_signup) {
            EditText et_username = (EditText) findViewById(R.id.uname_signup);
            EditText et_email = (EditText) findViewById(R.id.email_signup);
            EditText et_pass1 = (EditText) findViewById(R.id.pass1);
            EditText et_pass2 = (EditText) findViewById(R.id.pass2);

            //convert to string
            String unamestr = et_username.getText().toString();
            String emailstr = et_email.getText().toString();
            String pass1str = et_pass1.getText().toString();
            String pass2str = et_pass2.getText().toString();
            if (TextUtils.isEmpty(unamestr) || TextUtils.isEmpty(emailstr) || TextUtils.isEmpty(pass1str) || TextUtils.isEmpty(pass2str)) {

                Toast.makeText(this, "Do not leave the field blank!", Toast.LENGTH_SHORT).show();
            }
            else {
                if (!pass1str.equals(pass2str)) {
                    Toast pass = Toast.makeText(SignUp.this, "Password do not match!", Toast.LENGTH_SHORT);
                    pass.show();
                } else {
                    Users c = new Users();
                    c.setUserName(unamestr);
                    c.setEmail(emailstr);
                    c.setPassword(pass1str);

                    helper.insertUser(c);
                    Toast.makeText(SignUp.this, "User Created", Toast.LENGTH_LONG).show();
                }

                et_username.setText("");
                et_email.setText("");
                et_pass1.setText("");
                et_pass2.setText("");
            }
        }
    }
}
