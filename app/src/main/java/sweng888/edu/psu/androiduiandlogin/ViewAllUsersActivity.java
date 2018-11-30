package sweng888.edu.psu.androiduiandlogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import sweng888.edu.psu.androiduiandlogin.model.PersistanceUserData;
import sweng888.edu.psu.androiduiandlogin.model.UserList;
import sweng888.edu.psu.androiduiandlogin.model.UserProfile;

public class ViewAllUsersActivity extends AppCompatActivity {

    private ArrayList<UserProfile> u1;
    private PersistanceUserData persistanceUserData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_listing);

        persistanceUserData = new PersistanceUserData(this);
        u1 = persistanceUserData.getDataFromDB();


        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(new UserAdapter(this, loadData()));

        }

    private ArrayList<UserProfile> loadData() {
        ArrayList<UserProfile> list = new ArrayList<>();

        UserList u = null;

        String name;
        String surname;
        String email;

        if (u1 != null && !u1.isEmpty()) {

            for (UserProfile i : u1) {
                name = i.getName();
                surname = i.getSurname();
                email = i.getEmail();

                list.add(i);
            }
            }
            return list;
    }
}






