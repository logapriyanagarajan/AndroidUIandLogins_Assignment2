package sweng888.edu.psu.androiduiandlogin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sweng888.edu.psu.androiduiandlogin.model.UserList;
import sweng888.edu.psu.androiduiandlogin.model.UserProfile;

public class UserAdapter extends BaseAdapter {

    private ArrayList<UserProfile> user;
    private Context c;

    public UserAdapter(Context c, ArrayList<UserProfile> user){
        this.user = user;
        this.c = c;
    }

    @Override
    public int getCount() {
        return user.size();
    }

    @Override
    public Object getItem(int position) {
        return user.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d("TAG", "**********************in getView*******************************");

        if(convertView == null){
            convertView = LayoutInflater.from(c).inflate(
                    R.layout.user_view, parent, false);
        }

       TextView mTextName;
       TextView mTextSurname;
       TextView mTextEmail;


        mTextName = (TextView) convertView.findViewById(R.id.textViewName);
        mTextSurname = (TextView) convertView.findViewById(R.id.textViewSurname);
        mTextEmail = (TextView) convertView.findViewById(R.id.textViewEmail);

        final UserProfile u = (UserProfile) this.getItem(position);

        mTextName.setText(u.getName());
        mTextSurname.setText(u.getSurname());
        mTextEmail.setText(u.getEmail());

        Log.d("TAG", "**********************in getView final*******************************");

        return convertView;

    }
}
