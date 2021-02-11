package edu.itsligo.gaaappmain.Lotto;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.iwgang.countdownview.CountdownView;
import edu.itsligo.gaaappmain.R;


public class HomeFragmentLotto extends Fragment {

    TextView tt1, tt2, tt3, tt4, tt5, rdt12;

    TextView firebase_name;
    FirebaseDatabase database;
    DatabaseReference databaseReference, reference;


    private static final String TAG = "DATE1";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_lotto, container, false);
       TextView homeLottodate = v.findViewById(R.id.tvHomeLottoDate);
        firebase_name = v.findViewById(R.id.nameOfwinner);
        tt1 = v.findViewById(R.id.hometvNumber1);
        tt2 = v.findViewById(R.id.hometvNumber2);
        tt3 = v.findViewById(R.id.hometvNumber3);
        tt4 = v.findViewById(R.id.hometvNumber4);
        tt5 = v.findViewById(R.id.hometvNumber5);
        show_winner();


        CountdownView mCvCountdownView = v.findViewById(R.id.mycountdown);
        //Intent incoming = getIntent();
        // String getDate = incoming.getStringExtra("setDate");


        databaseReference = FirebaseDatabase.getInstance().getReference().child("LottoDate");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String lottoDate = dataSnapshot.child("saveDate").getValue().toString();
                homeLottodate.setText("Next Lotto in " + lottoDate);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String countDate = lottoDate + " 00:00:00";
                Date now = new Date();

                Log.d(TAG, countDate);


                try {
                    Date date = sdf.parse(countDate);
                    long currentTime = now.getTime();
                    long newYearDate = date.getTime();
                    long countDownTimer = newYearDate - currentTime;

                    mCvCountdownView.start(countDownTimer);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







        return v;
    }

    private void show_winner() {
        database = FirebaseDatabase.getInstance();
        reference = databaseReference = database.getReference("lootloWinner");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String cop = snapshot1.child("codeOfPerson").getValue(String.class);
                        String nop = snapshot1.child("nameofPerson").getValue(String.class);


                        firebase_name.setText(nop);
                        Toast.makeText(getContext(), nop, Toast.LENGTH_SHORT).show();
                        Toast.makeText(getContext(), cop, Toast.LENGTH_SHORT).show();

                        int random_int = Integer.parseInt(cop);

                        int n2 = random_int / 10000 % 10;
                        int n3 = random_int / 1000 % 10;
                        int n4 = random_int / 100 % 10;
                        int n5 = random_int / 10 % 10;
                        int n6 = random_int % 10;

                        tt1.setText(String.valueOf(n2));
                        tt2.setText(String.valueOf(n3));
                        tt3.setText(String.valueOf(n4));
                        tt4.setText(String.valueOf(n5));
                        tt5.setText(String.valueOf(n6));


                    }
                } else {
                    Toast.makeText(getContext(), "No USer Exists", Toast.LENGTH_SHORT).show();
                    firebase_name.setText("No USer Exists");
                }


//
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}