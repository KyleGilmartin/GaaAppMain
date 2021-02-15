package edu.itsligo.gaaappmain;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import edu.itsligo.gaaappmain.APIFetch.fetchData;

public class HomeFragmentHome extends Fragment {
    TextView date;
    private RecyclerView mNewslist;
    private DatabaseReference ref;

    public static TextView mPos1, mPos2, mPos3, mClub1, mClub2, mClub3, mWins1, mWins2, mWins3, mPlayed1, mPlayed2, mPlayed3, mGD1, mGD2, mGD3, mPts1, mPts2, mPts3;
    Button button;
    TextView time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.homefagment_home, container, false);
        date = v.findViewById(R.id.tvhomePageDate);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        date.setText(currentDate);

        // api table
        // gets time for refreshing api calls
        time = v.findViewById(R.id.tvTimeCheck);

        mPos1 = v.findViewById(R.id.t1Pos);
        mClub1 = v.findViewById(R.id.t1Club);
        mWins1 = v.findViewById(R.id.t1Wins);
        mGD1 = v.findViewById(R.id.t1GD);
        mPts1 = v.findViewById(R.id.t1Pts);

        mPos2 = v.findViewById(R.id.t2Pos);
        mClub2 = v.findViewById(R.id.t2Club);
        mWins2 = v.findViewById(R.id.t2Wins);
        mGD2 = v.findViewById(R.id.t2GD);
        mPts2 = v.findViewById(R.id.t2Pts);

        mPos3 = v.findViewById(R.id.t3Pos);
        mClub3 = v.findViewById(R.id.t3Club);
        mWins3 = v.findViewById(R.id.t3Wins);
        mGD3 = v.findViewById(R.id.t3GD);
        mPts3 = v.findViewById(R.id.t3Pts);


        String currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
        time.setText("Last Updated " + currentTime);


        fetchData process = new fetchData();
        process.execute();


        Handler handler1 = new Handler();
        for (int a = 1; a <= 100; a++) {
            handler1.postDelayed(new Runnable() {

                @Override
                public void run() {

                    String currentTime = new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date());
                    time.setText("Last Updated " + currentTime);
                    fetchData process = new fetchData();
                    process.execute();
                }
            }, 200000 * a);
        }


        button = v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData process = new fetchData();
                process.execute();
            }
        });


        // api end


        ImageSlider imageSlider = v.findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://pbs.twimg.com/media/DxmRWtvWsAAaRWV.jpg", "IT Sligo vs IT Tralee"));
        slideModels.add(new SlideModel("https://pbs.twimg.com/media/DpF3u-xW0AA6KAa.jpg", "IT Sligo's Hurlers vs GMIT"));
        slideModels.add(new SlideModel("https://old.itsligo.ie/files/2016/02/Hurlerstrophiesweb27022016.jpg", "Hurlers Win Fergal Maher Cup"));
        slideModels.add(new SlideModel("https://img2.thejournal.ie/inline/1933493/original/?width=630&version=1933493", "IT Sligo's Eunan Doherty"));
        slideModels.add(new SlideModel("https://pbs.twimg.com/media/Dy171AiXgAEucA-.jpg", "IT Sligo's Ladies"));
        imageSlider.setImageList(slideModels, true);


        return v;
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseRecyclerAdapter<News, NewsViewHolder2> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<News, NewsViewHolder2>(News.class, R.layout.news_card_home_layout, NewsViewHolder2.class, ref) {
//            @Override
//            protected void populateViewHolder(NewsViewHolder2 newsViewHolder, News news, int i) {
//                newsViewHolder.setDesc(news.getDesc());
//                newsViewHolder.setImage(getContext(), news.getImageURL());
//
//                Toast.makeText(getContext(), String.valueOf(news), Toast.LENGTH_SHORT).show();
//            }
//
//
//        };
//        mNewslist.setAdapter(firebaseRecyclerAdapter);
//    }
//
//    public static class NewsViewHolder2 extends RecyclerView.ViewHolder {
//        View mview;
//        TextView desc;
//
//
//        public NewsViewHolder2(View itemView) {
//            super(itemView);
//            mview = itemView;
//            desc = (TextView) mview.findViewById(R.id.supporting_text2);
//
//        }
//
//
//
//        public void setDesc(String desc) {
//            TextView post_Desc = (TextView) mview.findViewById(R.id.supporting_text2);
//            post_Desc.setText(desc);
//        }
//
//        public void setImage(Context ctx, String image) {
//            ImageView post_image = (ImageView) mview.findViewById(R.id.media_image2);
//            Picasso.get().load(image).into(post_image);
//        }
//    }
}
