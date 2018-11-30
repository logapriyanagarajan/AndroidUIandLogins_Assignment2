import android.content.Context;
import android.service.autofill.UserData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import sweng888.edu.psu.androiduiandlogin.model.UserProfile;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context ctx;
    private ArrayList<UserProfile> u1;

    public RecyclerAdapter(Context ctx, ArrayList<UserProfile> u1) {
        this.ctx = ctx;
        this.u1 = u1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.recycler_layout,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        UserProfile userdetails = u1.get(i);

        viewHolder.usrname.setText(userdetails.getName());
        viewHolder.surname.setText(userdetails.getSurname());
        viewHolder.email.setText(userdetails.getEmail());

    }

    @Override
    public int getItemCount() {
        return u1.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView usrname, surname, email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            usrname = itemView.findViewById(R.id.textViewName);
            surname = itemView.findViewById(R.id.textViewSurname);
            email = itemView.findViewById(R.id.textViewEmail);

        }
    }
}

