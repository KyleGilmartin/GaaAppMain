package edu.itsligo.gaaappmain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.itsligo.gaaappmain.models.Fixture_model;


public class Fixture_Adapter extends RecyclerView.Adapter<Fixture_Adapter.MyHolder> {

    Context context;
    List<Fixture_model> usersList;


    public Fixture_Adapter(Context context, List<Fixture_model> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // inflate layout(row_user.eml)
        View view = LayoutInflater.from(context).inflate(R.layout.homefragmentfixturesrow, viewGroup, false);


        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        // get data
        String name = usersList.get(i).getName();
        String date = usersList.get(i).getDate();
        String venue = usersList.get(i).getType();
        String url = usersList.get(i).getImg_url();

        // set data
        myHolder.mNametv.setText(name);
        myHolder.mEmailTv.setText(date);
        myHolder.venue.setText(venue);
        Picasso.get().load(url).into(myHolder.imageView);

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context,""+userEmail, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView mNametv, mEmailTv, venue;
        ImageView imageView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            mNametv = itemView.findViewById(R.id.opponent);
            mEmailTv = itemView.findViewById(R.id.date);
            venue = itemView.findViewById(R.id.venue);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
