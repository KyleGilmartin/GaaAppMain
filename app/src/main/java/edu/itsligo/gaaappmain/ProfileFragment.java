package edu.itsligo.gaaappmain;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProfileFragment extends Fragment {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView uName,uEmail,uPhone;


    TextView userlocation;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.profile_fragment,container,false);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userlocation = v.findViewById(R.id.tv_location);
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

        getLocation();

        return v;
    }


    private void getLocation() {
        if(MainActivity.new_location!=null) {
            try {
                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(
                        MainActivity.new_location.getLatitude(), MainActivity.new_location.getLongitude(), 1
                );
                userlocation.setText(addresses.get(0).getAddressLine(0));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(
                        MainActivity.last_location.getLatitude(), MainActivity.last_location.getLongitude(), 1
                );
                userlocation.setText(addresses.get(0).getAddressLine(0));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    {

}


}
