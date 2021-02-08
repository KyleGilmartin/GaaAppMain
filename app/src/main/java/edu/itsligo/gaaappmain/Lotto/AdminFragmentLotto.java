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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Random;

import edu.itsligo.gaaappmain.ModelUsers;
import edu.itsligo.gaaappmain.R;


public class AdminFragmentLotto extends Fragment {
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    TextView txt1, txt2, txt3, txt4, txt5, updateUser;
    int min = 10000;
    int max = 99000;
    int random_int;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_lotto, container, false);

        txt1 = v.findViewById(R.id.admintvNumber1);
        txt2 = v.findViewById(R.id.admintvNumber2);
        txt3 = v.findViewById(R.id.admintvNumber3);
        txt4 = v.findViewById(R.id.admintvNumber4);
        txt5 = v.findViewById(R.id.admintvNumber5);
        updateUser = v.findViewById(R.id.updateUser);


        Button showWinner = v.findViewById(R.id.btShowWinner);
        showWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                random_int = (int)(Math.random() * (max - min + 1) + min);

//        rdt12.setText(String.valueOf(random_int));


                int n2 = random_int / 10000 % 10;
                int n3 = random_int / 1000 % 10;
                int n4 = random_int / 100 % 10;
                int n5 = random_int / 10 % 10;
                int n6 = random_int % 10;

                txt1.setText(String.valueOf(n2));
                txt2.setText(String.valueOf(n3));
                txt3.setText(String.valueOf(n4));
                txt4.setText(String.valueOf(n5));
                txt5.setText(String.valueOf(n6));



//        Toast.makeText(this, String.valueOf(random_int), Toast.LENGTH_SHORT).show();

                Query checkuser = FirebaseDatabase.getInstance().getReference("lootlo").orderByChild("codeOfPerson").equalTo(String.valueOf(random_int));
                checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            String nameofPerson = snapshot.child(String.valueOf(random_int)).child("nameofPerson").getValue(String.class);
                            String codeOfPerson = snapshot.child(String.valueOf(random_int)).child("codeOfPerson").getValue(String.class);

                            database=FirebaseDatabase.getInstance();
                            databaseReference=database.getReference("lootloWinner");
                            HelperClass helperClass=new HelperClass(nameofPerson,codeOfPerson);
                            databaseReference.child(codeOfPerson).setValue(helperClass);


                            String subject="Winner";

                            String mssg="Congratulations You are Winner of Lootlo";

                            JavaMailAPI javaMailAPI=new JavaMailAPI(getContext(),decodeUserEmail(nameofPerson),subject,mssg);
                            javaMailAPI.execute();


                            Toast.makeText(getContext(), "User Exists", Toast.LENGTH_SHORT).show();


                            updateUser.setText(nameofPerson);
                        }
                        else {
                            Toast.makeText(getContext(), "NO User Exists", Toast.LENGTH_SHORT).show();
                            updateUser.setText("NO User Exists");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        return v;
    }


//    public void Winner(View view) {
//
//
//
//    }



    static String decodeUserEmail(String userEmail) {
        return userEmail.replace(",", ".");
    }



}