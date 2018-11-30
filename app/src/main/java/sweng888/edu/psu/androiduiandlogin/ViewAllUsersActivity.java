package sweng888.edu.psu.androiduiandlogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import sweng888.edu.psu.androiduiandlogin.model.PersistanceUserData;
import sweng888.edu.psu.androiduiandlogin.model.UserList;
import sweng888.edu.psu.androiduiandlogin.model.UserProfile;

public class ViewAllUsersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    private ArrayList<UserProfile> u1;
    private PersistanceUserData persistanceUserData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler_view);

        u1 = new ArrayList<>();

        persistanceUserData = new PersistanceUserData(this);
        u1 = persistanceUserData.getDataFromDB();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //ListView list = (ListView) findViewById(R.id.list);
        //list.setAdapter(new UserAdapter(this, loadData()));

        adapter = new RecyclerAdapter(this, u1);
        recyclerView.setAdapter(adapter);

        //loadData();


        }

    private void loadData() {
        ArrayList<UserProfile> list = new ArrayList<>();

        UserProfile u = null;

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
            adapter = new RecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }
}






