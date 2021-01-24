package edu.itsligo.gaaappmain.Games;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.itsligo.gaaappmain.Fixture_Adapter;
import edu.itsligo.gaaappmain.R;
import edu.itsligo.gaaappmain.models.Fixture_model;
import edu.itsligo.gaaappmain.models.results;
import edu.itsligo.gaaappmain.result_Adapter;


public class HomeFragmentResults extends Fragment {

    RecyclerView recyclerView;
    result_Adapter result_adapter;
    List<results> results_list;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference().child("results");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_results, container, false);
        recyclerView = view.findViewById(R.id.listView);
        recyclerView.setHasFixedSize(true);
        results_list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getfixtures();
        return view;
    }

    public void getfixtures() {


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//               for(DataSnapshot ds : dataSnapshot.getChildren()) {
//
//
//
//                   Log.d("TAG", ds.getChildren());
//
//               }
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();

                while (iterator.hasNext()) {

                    results results = new results();
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    results.setTeam1(next.child("team1").getValue().toString());
                    results.setScore1(next.child("score1").getValue().toString());
                    results.setTeam1_image(next.child("team1_image").getValue().toString());
                    results.setTeam2(next.child("team2").getValue().toString());
                    results.setScore2(next.child("score2").getValue().toString());
                    results.setTeam2_image(next.child("team2_image").getValue().toString());


                    results_list.add(results);
                }
                result_adapter = new result_Adapter(getContext(), results_list);
                recyclerView.setAdapter(result_adapter);
//                Toast.makeText(getContext(), dataSnapshot.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}