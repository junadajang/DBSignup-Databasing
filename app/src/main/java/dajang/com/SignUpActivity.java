package dajang.com;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    EditText etUsername, etPassword, etFullname;
    String username, password, fullname;
    int formsuccess, userid;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbHelper = new DbHelper(this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etFullname = findViewById(R.id.etFullName);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_cancel, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnSave:
                // get user input
                username = etUsername.getText().toString();
                password = etPassword.getText().toString();
                fullname = etFullname.getText().toString();
                formsuccess = 3;

                if(username.equals("")) {
                    etUsername.setError("This field is required");
                    formsuccess--;
                }
                if(password.equals("")) {
                    etPassword.setError("This field is required");
                    formsuccess--;
                }
                if(fullname.equals("")) {
                    etFullname.setError("This field is required");
                    formsuccess--;
                }
                if(formsuccess == 3){
                    HashMap<String, String> map_user = new HashMap();
                    map_user.put(dbHelper.TBL_USER_USERNAME, username);
                    map_user.put(dbHelper.TBL_USER_PASSWORD, password);
                    map_user.put(dbHelper.TBL_USER_FULLNAME, fullname);
                    userid = dbHelper.createUser(map_user);
                    if(userid < 1){
                        Toast.makeText(getApplicationContext(), "User Successfully Created", Toast.LENGTH_SHORT).show();
                    }else{
                        etUsername.setError("Username already existed.");
                    }
                    //Toast.makeText(getApplicationContext(), "Form Successfully Validated", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCancel:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
