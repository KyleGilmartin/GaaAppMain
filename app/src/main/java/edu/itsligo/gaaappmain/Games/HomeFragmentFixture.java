package edu.itsligo.gaaappmain.Games;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.itsligo.gaaappmain.AdapterUsers;
import edu.itsligo.gaaappmain.Fixture_Adapter;
import edu.itsligo.gaaappmain.ModelUsers;
import edu.itsligo.gaaappmain.R;
import edu.itsligo.gaaappmain.models.Fixture_model;

import static android.content.ContentValues.TAG;


public class HomeFragmentFixture extends Fragment {

    RecyclerView recyclerView;
    Fixture_Adapter fixture_adapter;
    List<Fixture_model> fixture_list;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference().child("fixture");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fixture_list = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_home_fixture, container, false);
        recyclerView = view.findViewById(R.id.listView);
        recyclerView.setHasFixedSize(true);

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

                    Fixture_model fixture_model = new Fixture_model();
                    DataSnapshot next = (DataSnapshot) iterator.next();
                    fixture_model.setDate(next.child("date").getValue().toString());
                    fixture_model.setName(next.child("name").getValue().toString());
                    fixture_model.setType(next.child("type").getValue().toString());
                    fixture_model.setImg_url(next.child("img_url").getValue().toString());


                    fixture_list.add(fixture_model);
                }
                fixture_adapter = new Fixture_Adapter(requireContext(), fixture_list);
                recyclerView.setAdapter(fixture_adapter);
//                Toast.makeText(getContext(), dataSnapshot.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}