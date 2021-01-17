package edu.itsligo.gaaappmain;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ProfileAdminFragment extends Fragment  {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView uName, uEmail, uPhone;
    FusedLocationProviderClient fusedLocationProviderClient;
    TextView userlocation;
    ImageView adminImage;
    StorageReference storageReference;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_profile_admin, container, false);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        // admin profile picture on click
        adminImage = v.findViewById(R.id.AdminProfilePicture);
        adminImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Long click to change image", Toast.LENGTH_SHORT).show();
//
            }
        });


        adminImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallery, 1000);
                return false;
            }
        });



        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(adminImage);
            }
        });


        userlocation = v.findViewById(R.id.tv_location);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //   getLocation();
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
        getLocation();

        // profile image


        return v;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
//                adminImage.setImageURI(imageUri);

                uploadImageToFirebase(imageUri);
            }
        }

    }

    private void uploadImageToFirebase(Uri imageUri) {
        StorageReference fileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                   @Override
                   public void onSuccess(Uri uri) {
                       Picasso.get().load(uri).into(adminImage);
                   }
               });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed to upload", Toast.LENGTH_SHORT).show();
            }
        });
    }


//    public void profileClick(View view){
//
//        Toast.makeText(getContext(), "sdd", Toast.LENGTH_SHORT).show();
//
//    }
//
//
//// override for profile image
//    public void onActivityResult(int requestCode, int resultCode, @androidx.annotation.NonNull Intent data) {
//        super.onActivityResult(requestCode,requestCode,data);
//        if(requestCode == 1000){
//            if(resultCode == Activity.RESULT_OK){
//                Uri imageUri = data.getData();
//                adminImage.setImageURI(imageUri);
//            }
//        }
//    }


    private void getLocation() {
        if (MainActivity.new_location != null) {
            try {
                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(
                        MainActivity.new_location.getLatitude(), MainActivity.new_location.getLongitude(), 1
                );
                userlocation.setText(addresses.get(0).getCountryName());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(
                        MainActivity.last_location.getLatitude(), MainActivity.last_location.getLongitude(), 1
                );
                userlocation.setText(addresses.get(0).getCountryName());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
