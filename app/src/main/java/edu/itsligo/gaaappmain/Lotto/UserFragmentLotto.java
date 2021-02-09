package edu.itsligo.gaaappmain.Lotto;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.sapereaude.maskedEditText.MaskedEditText;
import edu.itsligo.gaaappmain.R;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;


public class UserFragmentLotto extends Fragment {

    TextView tt1, tt2, tt3, tt4, tt5, rdt12;
    EditText mName, mCardno,  mPin;
    Button buy;
    MaskedEditText mDate;

    int min = 10000;
    int max = 99000;
    int random_int;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String mailOfUser;
    Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_lotto, container, false);
        fAuth = FirebaseAuth.getInstance();

        mailOfUser = fAuth.getCurrentUser().getEmail();


        //  lotto form validation
        fStore = FirebaseFirestore.getInstance();
        mName = v.findViewById(R.id.etUserCardName);

        DocumentReference docR = fStore.collection("Users").document(fAuth.getCurrentUser().getUid());
        docR.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    mName.setText(documentSnapshot.getString("FullName"));

                }
            }
        });

        mCardno = v.findViewById(R.id.etUserCardNo);
        mDate =(MaskedEditText) v.findViewById(R.id.etUserCardDate);
        mPin = v.findViewById(R.id.etUserCardPin);


        tt1 = v.findViewById(R.id.usertvNumber1);
        tt2 = v.findViewById(R.id.usertvNumber2);
        tt3 = v.findViewById(R.id.usertvNumber3);
        tt4 = v.findViewById(R.id.usertvNumber4);
        tt5 = v.findViewById(R.id.usertvNumber5);

        Button pick = v.findViewById(R.id.btRandomNumers);
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                random_int = (int) (Math.random() * (max - min + 1) + min);


//        rdt12.setText(String.valueOf(random_int));


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

                Toast.makeText(getContext(), String.valueOf(random_int), Toast.LENGTH_SHORT).show();


            }
        });

        buy = v.findViewById(R.id.btBuyUserTicket);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                databaseReference = database.getReference("lootlo");
//

                HelperClass helperClass = new HelperClass(encodeUserEmail(mailOfUser), String.valueOf(random_int));
                databaseReference.child(String.valueOf(random_int)).setValue(helperClass);
                Toast.makeText(getContext(), "Data Inserted Successfully", Toast.LENGTH_SHORT).show();


                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.customlottodialog);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    dialog.getWindow().setBackgroundDrawable(getDrawable(getActivity(),R.drawable.lottopaymentcard));
                }
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                dialog.show();

                Button okey = dialog.findViewById(R.id.btn_okey);
                okey.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                Button cancel = dialog.findViewById(R.id.btn_cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                mName.getText().clear();
                mCardno.getText().clear();
                mDate.getText().clear();
                mPin.getText().clear();

            }
        });


//        rdt12=findViewById(R.id.tvNumber1);

        //Toast.makeText(getContext(), "Random value in int from " + min + " to " + max + ":", Toast.LENGTH_SHORT).show();


        buy.setEnabled(false);

        mName.addTextChangedListener(LoginTextWatcher);
        mCardno.addTextChangedListener(LoginTextWatcher);
        mDate.addTextChangedListener(LoginTextWatcher);
        mPin.addTextChangedListener(LoginTextWatcher);


        return v;
    }

    private TextWatcher LoginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String userNameInput = mName.getText().toString().trim();
            String userCardInput = mCardno.getText().toString().trim();
            String userDateInput = mDate.getText().toString().trim();
            String userPinInput = mPin.getText().toString().trim();

            buy.setEnabled(!userNameInput.isEmpty() && !userCardInput.isEmpty() && !userDateInput.isEmpty() && !userPinInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    static String encodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }
//    public void pick(View view) {
//
//
////
//
//
//
//    }

//    public void Buy(View view) {
//
//    }


}
