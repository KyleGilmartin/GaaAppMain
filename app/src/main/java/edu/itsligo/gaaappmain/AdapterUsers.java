package edu.itsligo.gaaappmain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyHolder> {

    Context context;
    List<ModelUsers> usersList;


    public AdapterUsers(Context context, List<ModelUsers> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // inflate layout(row_user.eml)
        View view = LayoutInflater.from(context).inflate(R.layout.row_admin_usersall, viewGroup,false);


        return new MyHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        // get data
        String userName = usersList.get(i).getFullName();
        String userEmail = usersList.get(i).getUserEmail();

        // set data
        myHolder.mNametv.setText(userName);
        myHolder.mEmailTv.setText(userEmail);

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,""+userEmail, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class  MyHolder extends RecyclerView.ViewHolder{

        TextView mNametv,mEmailTv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            mNametv = itemView.findViewById(R.id.nameTv);
            mEmailTv = itemView.findViewById(R.id.emailTv);
        }
    }
}
