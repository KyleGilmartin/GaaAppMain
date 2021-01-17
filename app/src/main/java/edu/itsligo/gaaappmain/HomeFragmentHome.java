package edu.itsligo.gaaappmain;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HomeFragmentHome extends Fragment {
    TextView date;
    private RecyclerView mNewslist;
    private DatabaseReference ref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.homefagment_home, container, false);
        date = v.findViewById(R.id.tvhomePageDate);
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        date.setText(currentDate);

        ref = FirebaseDatabase.getInstance().getReference().child("NewTable");
        ref.keepSynced(true);
        mNewslist = v.findViewById(R.id.myrecycleview2);
        mNewslist.setHasFixedSize(true);
        mNewslist.setLayoutManager(new LinearLayoutManager(getContext()));




        return  v;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<News, NewsViewHolder2> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<News, NewsViewHolder2>(News.class, R.layout.news_card_home_layout, NewsViewHolder2.class, ref) {
            @Override
            protected void populateViewHolder(NewsViewHolder2 newsViewHolder, News news, int i) {
                newsViewHolder.setDesc(news.getDesc());
                newsViewHolder.setImage(getContext(), news.getImageURL());

                Toast.makeText(getContext(), String.valueOf(news), Toast.LENGTH_SHORT).show();
            }


        };
        mNewslist.setAdapter(firebaseRecyclerAdapter);
    }

    public static class NewsViewHolder2 extends RecyclerView.ViewHolder {
        View mview;
        TextView desc;


        public NewsViewHolder2(View itemView) {
            super(itemView);
            mview = itemView;
            desc = (TextView) mview.findViewById(R.id.supporting_text2);

        }



        public void setDesc(String desc) {
            TextView post_Desc = (TextView) mview.findViewById(R.id.supporting_text2);
            post_Desc.setText(desc);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mview.findViewById(R.id.media_image2);
            Picasso.get().load(image).into(post_image);
        }
    }
}
