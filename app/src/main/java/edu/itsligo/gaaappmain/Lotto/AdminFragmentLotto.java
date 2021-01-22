package edu.itsligo.gaaappmain.Lotto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_lotto, container, false);
        btn_winner = view.findViewById(R.id.btWinner);
        tv_winner = view.findViewById(R.id.tvHomeLottoWinner);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FireStore = FirebaseFirestore.getInstance();
        btn_winner.setOnClickListener(v-> pickUser());
    }

    private void pickUser(){
        FireStore.collection("Users").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {

                    if(!queryDocumentSnapshots.isEmpty()){
                        List<DocumentSnapshot> list= queryDocumentSnapshots.getDocuments();
                        int random = new Random().nextInt(list.size());

                        ModelUsers user_model = list.get(random).toObject(ModelUsers.class);
                        tv_winner.setText(user_model.getFullName());

                    }
                });
    }
}