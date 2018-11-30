package sweng888.edu.psu.androiduiandlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

import sweng888.edu.psu.androiduiandlogin.model.PersistanceUserData;
import sweng888.edu.psu.androiduiandlogin.model.UserProfile;

public class SignUpActivity extends AppCompatActivity {

    // SQLite
    private PersistanceUserData persistencedata;

    private PersistanceUserData persistanceUserData;
    private FirebaseAuth mAuth = null;

    // UI
    private EditText mEditTextFirst;
    private EditText mEditTextLast;
    private EditText mEditTextUsername;
    private EditText mEditTextBirthday;
    private EditText mEditTextPass;
    private EditText mEditTextEmail;
    private EditText mEditTextPhone;

    private Button mButtonCreateNewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mEditTextFirst = (EditText) findViewById(R.id.editTextName);
        mEditTextLast = (EditText) findViewById(R.id.editTextSurname);
        mEditTextUsername = (EditText) findViewById(R.id.editTextUsername);
        mEditTextBirthday = (EditText) findViewById(R.id.editTextBirth);
        mEditTextPass = (EditText) findViewById(R.id.editTextPassword);
        mEditTextEmail = (EditText) findViewById(R.id.editTextEmail);
        mEditTextPhone = (EditText) findViewById(R.id.editTextPhone);

        mButtonCreateNewUser = (Button) findViewById(R.id.btnconfirm);

        mButtonCreateNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String first = mEditTextFirst.getText().toString();
                String last = mEditTextLast.getText().toString();
                String username = mEditTextUsername.getText().toString();
                String pass = mEditTextPass.getText().toString();
                String email = mEditTextEmail.getText().toString();
                String phone = mEditTextPhone.getText().toString();
                String birthday = mEditTextBirthday.getText().toString();

//                String pattern = "yyyy-MM-dd";
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//                String date = simpleDateFormat.format(new Date());

                UserProfile user = new UserProfile(first, last,
                        username, phone, email, pass, birthday);

                persistencedata.insert(user);

                firebasesignup();
            }
        });
    }

    private void firebasesignup() {
        final String id = mEditTextEmail.getText().toString();
        final String password = mEditTextPass.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(id,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // If sign in sucessfull, display a message to the user.
                    Log.d("USER_AUTH", "createUserWithEmailAndPassword.success");
                    // Get user information.
                    FirebaseUser user = mAuth.getCurrentUser();

                    String msg = "Someone: "+user.getEmail()+" , ID: "+user.getProviderId();
                    Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);

                }else{
                    // TODO
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmailAndPassword:failure", task.getException());
                    Toast.makeText(SignUpActivity.this, "Authentication failed "+task.getException().getMessage() ,
                            Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        persistencedata = new PersistanceUserData(SignUpActivity.this);
    }
}




