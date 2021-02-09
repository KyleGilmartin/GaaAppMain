package edu.itsligo.gaaappmain;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeFragmentNews extends Fragment {

    private RecyclerView mNewslist;
    private DatabaseReference ref;
    TextView tvNewsLink;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.homefragment_news, container, false);
        final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.startLoadingDialog();


        ref = FirebaseDatabase.getInstance().getReference().child("NewTable");
        ref.keepSynced(true);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 500);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 500);
            }
        });

        mNewslist = v.findViewById(R.id.myrecycleview);
        mNewslist.setHasFixedSize(true);
        mNewslist.setLayoutManager(new LinearLayoutManager(getContext()));


        return v;
    }

    public void goToUrl(String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<News, NewsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<News, NewsViewHolder>(News.class, R.layout.news_card_layout, NewsViewHolder.class, ref) {
            @Override
            protected void populateViewHolder(NewsViewHolder newsViewHolder, News news, int i) {
                newsViewHolder.settitle(news.getTitle());
                newsViewHolder.setDesc(news.getDesc());
                newsViewHolder.setImage(getContext(), news.getImageURL());
                newsViewHolder.setLink(news.getLink());


                newsViewHolder.imgExpand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (newsViewHolder.desc.getVisibility() == View.GONE) {
                            newsViewHolder.desc.setVisibility(View.VISIBLE);
                            newsViewHolder.imgExpand.setImageResource(R.drawable.ic_expand_more_black_36dp);
                        } else {
                            newsViewHolder.desc.setVisibility(View.GONE);
                            newsViewHolder.imgExpand.setImageResource(R.drawable.ic_contact);
                        }

                    }
                });
            }
        };
        mNewslist.setAdapter(firebaseRecyclerAdapter);
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder {
        View mview;
        TextView desc;
        TextView link;
        ImageButton imgExpand;


        public NewsViewHolder(View itemView) {
            super(itemView);
            mview = itemView;
            desc = (TextView) mview.findViewById(R.id.supporting_text);
            imgExpand = mview.findViewById(R.id.expand_button);
            link = mview.findViewById(R.id.NewsLink);
        }


        public void setLink(String link) {
            TextView post_link = (TextView) mview.findViewById(R.id.NewsLink);
            post_link.setText(link);
            post_link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    goToUrl(link);
                }
            });


        }


        public void settitle(String title) {
            TextView post_title = (TextView) mview.findViewById(R.id.primary_text);
            post_title.setText(title);

        }

        public void setDesc(String desc) {
            TextView post_Desc = (TextView) mview.findViewById(R.id.supporting_text);
            post_Desc.setText(desc);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mview.findViewById(R.id.media_image);
            Picasso.get().load(image).into(post_image);
        }
    }


}
