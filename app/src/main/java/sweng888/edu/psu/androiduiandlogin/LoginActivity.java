package sweng888.edu.psu.androiduiandlogin;

import android.content.Intent;
import android.service.autofill.UserData;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import sweng888.edu.psu.androiduiandlogin.model.PersistanceUserData;
import sweng888.edu.psu.androiduiandlogin.model.UserProfile;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEditEmail;
    private EditText mEditPassword;

    private Button mBtnLogin;
    private Button mBtnSignup;

    private ArrayList<UserProfile> u1;
    private PersistanceUserData persistanceUserData;

    private FirebaseAuth mAuth = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditPassword = (EditText) findViewById(R.id.editTextPassword);

        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnSignup = (Button) findViewById(R.id.btnSignup);

        mBtnLogin.setOnClickListener(this);
        mBtnSignup.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        persistanceUserData = new PersistanceUserData(this);
        u1 = persistanceUserData.getDataFromDB();

    }

    public void onClick(View v){

        switch (v.getId()){
            case R.id.btnLogin: fireBaseSignIn(); break;
            case R.id.btnSignup:
                Signup();
                // Create an intent to navigate to the SignUpActivity.
                // Move the fireBaseSignUp method to the SignUpActivity
                // The method should be called after the user info has been added to
                // the local database (SQLite).
                break;
        }

    }

    private void verifyUser(){

        UserProfile data = null;

        if(u1 != null && !u1.isEmpty()){
            String user = mEditEmail.getText().toString();
            String password = mEditPassword.getText().toString();

            for(UserProfile i:u1){
                if(i.getEmail().equals(user)){
                    data = i;
                }
                break;
            }

            if(data == null){
                Toast.makeText(LoginActivity.this, "User invalid, sign up", Toast.LENGTH_LONG).show();
                }
                else{
                if(!data.getPassword().equals(password)){
                    Toast.makeText(LoginActivity.this,"Wrong Passowrd, try again!", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
                    intent.putExtra("USER", data.getName());
                    intent.putExtra("PASSWORD", data.getPassword());
                    startActivity(intent);
                }
            }
        }

    }

    private void Signup(){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);

    }

    private void fireBaseSignIn(){

        String user = mEditEmail.getText().toString();
        final String password = mEditPassword.getText().toString();
        UserProfile data = null;

        mAuth = FirebaseAuth.getInstance();

        mAuth.signInWithEmailAndPassword(user, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(LoginActivity.this, LoginSuccessActivity.class);
                    startActivity(intent);
                    }
                    else{
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_LONG).show();

                }
            }
        });

    }

}
