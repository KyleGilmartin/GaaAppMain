package edu.itsligo.gaaappmain;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import edu.itsligo.gaaappmain.models.Fixture_model;
import edu.itsligo.gaaappmain.models.results;

public class GamesAdminFragment extends Fragment {
    //database references
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageReference;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference().child("results");
    AppCompatButton save_btn;
    ImageView team1, team2;
    EditText team1_score, team2_score;
    TextView display1, display2;
    String image1storegepath;
    Uri path1, path2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_games_admin, container, false);
        //initializing database references
        mFirebaseStorage = FirebaseStorage.getInstance();
        mStorageReference = mFirebaseStorage.getReference();
        save_btn = v.findViewById(R.id.button4);
        team1 = v.findViewById(R.id.image1);
        team2 = v.findViewById(R.id.image2);
        team1_score = v.findViewById(R.id.editTextTextPersonName);
        team2_score = v.findViewById(R.id.editTextTextPersonName2);

        Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.sligocrest);
        path1 = path;

        Spinner spinner2 = v.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Teams, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String Team1 = (String) spinner2.getSelectedItem();
                display1 = (TextView) v.findViewById(R.id.team1Name);
                display1.setText(Team1);


                if (display1.getText().toString().equals("Sligo")) {
                    team1.setImageResource(R.drawable.sligocrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.sligocrest);
                    path1 = path;
                }
                if (display1.getText().toString().equals("Mayo")) {
                    team1.setImageResource(R.drawable.mayocrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.mayocrest);
                    path1 = path;
                }
                if (display1.getText().toString().equals("Galway")) {
                    team1.setImageResource(R.drawable.galwaycrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.galwaycrest);
                    path1 = path;
                }
                if (display1.getText().toString().equals("Leitrim")) {
                    team1.setImageResource(R.drawable.leitrimcrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.leitrimcrest);
                    path1 = path;
                }
                if (display1.getText().toString().equals("Roscommon")) {
                    team1.setImageResource(R.drawable.roscommoncrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.roscommoncrest);
                    path1 = path;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }


        });

        Spinner spinner3 = v.findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getContext(),
                R.array.Teams, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String Team2 = (String) spinner3.getSelectedItem();
                display2 = (TextView) v.findViewById(R.id.team2Name);
                display2.setText(Team2);
                if (display2.getText().toString().equals("Sligo")) {
                    team2.setImageResource(R.drawable.sligocrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.sligocrest);
                    path2 = path;
                }
                if (display2.getText().toString().equals("Mayo")) {
                    team2.setImageResource(R.drawable.mayocrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.mayocrest);
                    path2 = path;
                }
                if (display2.getText().toString().equals("Galway")) {
                    team2.setImageResource(R.drawable.galwaycrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.galwaycrest);
                    path2 = path;
                }
                if (display2.getText().toString().equals("Leitrim")) {
                    team2.setImageResource(R.drawable.leitrimcrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.leitrimcrest);
                    path2 = path;
                }
                if (display2.getText().toString().equals("Roscommon")) {
                    team2.setImageResource(R.drawable.roscommoncrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.roscommoncrest);
                    path2 = path;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
//        save_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                results result = new results(team1_score.getText().toString(), team2_score.getText().toString(),display1.getText().toString(),display2.getText().toString(),"","");
//                String key = mDatabase.getReference().child("results").push().getKey();
//
//                mDatabaseReference = mDatabase.getReference().child("fixture").child(key);
//                mDatabaseReference.setValue(result);
//            }
//        });


        results results = new results();
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (path1 == null) {
                    Snackbar.make(v, "Please select an image", BaseTransientBottomBar.LENGTH_SHORT).show();
                    return;
                } else {

                    final StorageReference fileToUpload = mStorageReference.child("Results").child(UUID.randomUUID().toString()).child(UUID.randomUUID().toString());
                    fileToUpload.putFile(path1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileToUpload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUri = uri;

                                    image1storegepath = (downloadUri.toString());
                                    if (path2 == null) {
                                        Snackbar.make(v, "Please select an image", BaseTransientBottomBar.LENGTH_SHORT).show();
                                        return;
                                    } else {
                                        //getting title, description
                                        String team1_scores = team1_score.getText().toString();
                                        String team2_scores = team2_score.getText().toString();
//                    String team1_imagess = ntitle.getText().toString();
//                    String team2_image = ndescription.getText().toString();
                                        String team1_name = display1.getText().toString();
                                        String team2_name = display2.getText().toString();
                                        final StorageReference fileToUpload = mStorageReference.child("Results").child(UUID.randomUUID().toString()).child(UUID.randomUUID().toString());
                                        fileToUpload.putFile(path2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                fileToUpload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        Uri downloadUri = uri;
                                                        results.setScore1(team1_scores);
                                                        results.setScore2(team2_scores);
                                                        results.setTeam1(team1_name);
                                                        results.setTeam2(team2_name);
                                                        results.setTeam1_image(image1storegepath);
                                                        results.setTeam2_image(downloadUri.toString());

                                                        mDatabaseReference.push().setValue(results).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                if (task.isSuccessful()) {
                                                                    Toast.makeText(getActivity().getApplicationContext(), "  added to database", Toast.LENGTH_SHORT).show();
                                                                    Intent intent = new Intent(getActivity(), Admin.class);
                                                                    startActivity(intent);
                                                                } else {
                                                                    Toast.makeText(getActivity().getApplicationContext(), "Ops..Somethings went wrong!", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }

                                }
                            });
                        }
                    });
                }





            }

        });

        return v;
    }
}
