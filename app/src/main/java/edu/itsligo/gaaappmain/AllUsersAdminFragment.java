package edu.itsligo.gaaappmain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AllUsersAdminFragment extends Fragment {

    RecyclerView recyclerView;
    AdapterUsers adapterUsers;
    List<ModelUsers> usersList;
    FirebaseFirestore fStore;

    FirebaseFirestore db;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_allusers, container, false);
        recyclerView =v.findViewById(R.id.users_recyclerView);
        fStore = FirebaseFirestore.getInstance();

        // set its properties
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // init user list

        usersList = new ArrayList<>();

        db= FirebaseFirestore.getInstance();
        getallUsers();


        return v;
    }

    private void getallUsers() {

        db.collection("Users").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if(!queryDocumentSnapshots.isEmpty()){
                            List<DocumentSnapshot> list= queryDocumentSnapshots.getDocuments();

                            for(DocumentSnapshot d:list){
                                ModelUsers modelUsers = d.toObject(ModelUsers.class);
                                usersList.add(modelUsers);
                            }
                        }
                        adapterUsers = new AdapterUsers(getActivity(),usersList);
                        recyclerView.setAdapter(adapterUsers);

                    }
                });




//
//        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                usersList.clear();
//                for(DataSnapshot ds: dataSnapshot.getChildren()){
//                    ModelUsers modelUsers = ds.getValue(ModelUsers.class);
//
//                   //  get all users excerpt currntly signed in user
//                    if(!modelUsers.getUid().equals(fUser.getUid())){
//                        usersList.add(modelUsers);
//                    }
//
////                    if (!fUser.getUid().equals(ModelUser.getUid())) {
////                        userList.add(modelUser);
//
//                    // adapter
//                    adapterUsers = new AdapterUsers(getActivity(),usersList);
//                    //set adapter to recyle view
//
//                    recyclerView.setAdapter(adapterUsers);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }
}
