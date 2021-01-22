package edu.itsligo.gaaappmain.Lotto;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;
import java.util.Random;
import edu.itsligo.gaaappmain.ModelUsers;
import edu.itsligo.gaaappmain.R;


public class AdminFragmentLotto extends Fragment {


    private Button btn_winner;
    private TextView tv_winner;
    private FirebaseFirestore FireStore;
    private DatabaseReference databaseReference;
    private Dialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_lotto, container, false);
        btn_winner = view.findViewById(R.id.btWinner);
        tv_winner = view.findViewById(R.id.tvHomeLottoWinner);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(R.layout.progress);
        progressDialog = dialogBuilder.create();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FireStore = FirebaseFirestore.getInstance();
        btn_winner.setOnClickListener(v-> pickUser());
        progressDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("LottoWinner").child("UserName");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tv_winner.setText(snapshot.getValue(String.class));
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(),"Failed to connect", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    private void pickUser(){

        progressDialog.show();
        FireStore.collection("Users").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    if(!queryDocumentSnapshots.isEmpty()){
                        List<DocumentSnapshot> list= queryDocumentSnapshots.getDocuments();
                        int random = new Random().nextInt(list.size());

                        ModelUsers user_model = list.get(random).toObject(ModelUsers.class);
                        String winner_name = user_model.getFullName();

                        databaseReference.setValue(winner_name).addOnCompleteListener(task -> {
                            tv_winner.setText(winner_name);
                            progressDialog.dismiss();
                        }).addOnFailureListener(e -> {
                            Toast.makeText(getContext(),"Failed to connect", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        });
                        tv_winner.setText(user_model.getFullName());

                    }
                });
    }
}