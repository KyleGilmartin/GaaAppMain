package edu.itsligo.gaaappmain;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ProfileAdminFragment extends Fragment {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView uName, uEmail, uPhone;
    FusedLocationProviderClient fusedLocationProviderClient;
    TextView userlocation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_profile_admin, container, false);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        userlocation = v.findViewById(R.id.tv_location);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        uName = v.findViewById(R.id.profileFullName);
        uEmail = v.findViewById(R.id.profileEmail);
        uPhone = v.findViewById(R.id.profilePhone);

        DocumentReference docR = fStore.collection("Users").document(fAuth.getCurrentUser().getUid());
        docR.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    uName.setText(documentSnapshot.getString("FullName"));
                    uEmail.setText(documentSnapshot.getString("UserEmail"));
                    uPhone.setText(documentSnapshot.getString("PhoneNumber"));
                }
            }
        });

        return v;
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {

                    try {
                        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        userlocation.setText(addresses.get(0).getAddressLine(0));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
