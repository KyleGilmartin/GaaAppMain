package edu.itsligo.gaaappmain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileAdminFragment extends Fragment {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView uName,uEmail,uPhone;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_profile_admin, container, false);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        uName = v.findViewById(R.id.profileFullName);
        uEmail = v.findViewById(R.id.profileEmail);
        uPhone =  v.findViewById(R.id.profilePhone);

        DocumentReference docR = fStore.collection("Users").document(fAuth.getCurrentUser().getUid());
        docR.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    uName.setText(documentSnapshot.getString("FullName"));
                    uEmail.setText(documentSnapshot.getString("UserEmail"));
                    uPhone.setText(documentSnapshot.getString("PhoneNumber"));
                }
            }
        });

        return v;
    }
}
