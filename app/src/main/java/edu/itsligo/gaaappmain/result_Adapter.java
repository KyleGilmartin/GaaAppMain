package edu.itsligo.gaaappmain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.itsligo.gaaappmain.models.Fixture_model;
import edu.itsligo.gaaappmain.models.results;


public class result_Adapter extends RecyclerView.Adapter<result_Adapter.MyHolder> {

    Context context;
    List<results> resultsList;


    public result_Adapter(Context context, List<results> resultsList) {
        this.context = context;
        this.resultsList = resultsList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // inflate layout(row_user.eml)
        View view = LayoutInflater.from(context).inflate(R.layout.homefragementresults, viewGroup, false);


        return new MyHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        // get data
        String team1name = resultsList.get(i).getTeam1();
        String team2name = resultsList.get(i).getTeam2();
        String team1score = resultsList.get(i).getScore1();
        String team2score = resultsList.get(i).getScore2();
        String team1image = resultsList.get(i).getTeam1_image();
        String team2image = resultsList.get(i).getTeam2_image();

        // set data
        myHolder.team1name.setText(team1name);
        myHolder.team1score.setText(team1score);


        Picasso.get().load(team1image).into(myHolder.team1image);
        Picasso.get().load(team2image).into(myHolder.team2image);

        myHolder.team2name.setText(team2name);
        myHolder.team2score.setText(team2score);

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context,""+userEmail, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView team1name, team2name, team1score, team2score;

        ImageView team1image, team2image;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            team1name = itemView.findViewById(R.id.team1Name);
            team2name = itemView.findViewById(R.id.team2Name);
            team1score = itemView.findViewById(R.id.team1Score);
            team2score = itemView.findViewById(R.id.team2Score);
            team1image = itemView.findViewById(R.id.image1);
            team2image = itemView.findViewById(R.id.image2);

        }
    }
}
