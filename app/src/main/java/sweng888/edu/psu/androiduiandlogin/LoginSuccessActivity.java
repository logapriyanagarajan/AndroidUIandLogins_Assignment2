package sweng888.edu.psu.androiduiandlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity {

    private TextView mTextViewEmail;
    private TextView mTextViewPassword;

    private Button mbtnview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);

        mTextViewEmail = (TextView) findViewById(R.id.textViewEmail);
        mTextViewPassword = (TextView)findViewById(R.id.textViewPassword);

        mbtnview = (Button) findViewById(R.id.btnView);

        Intent intent = getIntent();
        String user= intent.getStringExtra("USER");
        String password = intent.getStringExtra("PASSWORD");

        mTextViewEmail.setText(user);
        mTextViewPassword.setText(password);

        mbtnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(LoginSuccessActivity.this,ViewAllUsersActivity.class);
                startActivity(intent1);
            }
        });
    }
}
