package edu.itsligo.gaaappmain;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import edu.itsligo.gaaappmain.models.Fixture_model;

public class FixturesAdminFragment extends Fragment {
    ImageView imageView;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageReference;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference().child("fixture");
    TextView date;
    TextView display1;
    TextView display;
    Uri path2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fixture_admin, container, false);


        imageView = v.findViewById(R.id.image1);
        mFirebaseStorage = FirebaseStorage.getInstance();
        mStorageReference = mFirebaseStorage.getReference();
        Spinner spinner = v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Teams, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String result = (String) spinner.getSelectedItem();
                display = (TextView) v.findViewById(R.id.opponent);
                display.setText(result);

                Log.d("Teams", "onItemSelected:  " + result);

                if (result.equals("Sligo")) {
                    imageView.setImageResource(R.drawable.sligocrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.sligocrest);
                    path2 = path;
                }
                if (result.equals("Mayo")) {
                    imageView.setImageResource(R.drawable.mayocrest);
                }
                if (result.equals("Galway")) {
                    imageView.setImageResource(R.drawable.galwaycrest);
                }
                if (result.equals("Leitrim")) {
                    imageView.setImageResource(R.drawable.leitrimcrest);
                }
                if (result.equals("Roscommon")) {
                    imageView.setImageResource(R.drawable.roscommoncrest);
                }

                if (display.getText().toString().equals("Sligo")) {
                    imageView.setImageResource(R.drawable.sligocrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.sligocrest);
                    path2 = path;
                }
                if (display.getText().toString().equals("Mayo")) {
                    imageView.setImageResource(R.drawable.mayocrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.mayocrest);
                    path2 = path;
                }
                if (display.getText().toString().equals("Galway")) {
                    imageView.setImageResource(R.drawable.galwaycrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.galwaycrest);
                    path2 = path;
                }
                if (display.getText().toString().equals("Leitrim")) {
                    imageView.setImageResource(R.drawable.leitrimcrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.leitrimcrest);
                    path2 = path;
                }
                if (display.getText().toString().equals("Roscommon")) {
                    imageView.setImageResource(R.drawable.roscommoncrest);
                    Uri path = Uri.parse("android.resource://edu.itsligo.gaaappmain/" + R.drawable.roscommoncrest);
                    path2 = path;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner spinner1 = v.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),
                R.array.Venue, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String venue = (String) spinner1.getSelectedItem();
                display1 = (TextView) v.findViewById(R.id.venue);
                display1.setText(venue);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        CalendarView calendarView = v.findViewById(R.id.calendarView1);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String Date = (i1 + 1) + "/" + i2 + "/" + i;
                date = v.findViewById(R.id.date1);
                date.setText(Date);
            }
        });


        AppCompatButton btn = v.findViewById(R.id.save);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fixture_model fixture_model = new Fixture_model();


                if (path2 == null) {
                    Snackbar.make(v, "Please select an image", BaseTransientBottomBar.LENGTH_SHORT).show();
                    return;
                } else {

                    String team_name = display.getText().toString();
                    String newdate = date.getText().toString();
                    String venue = display1.getText().toString();
                    final StorageReference fileToUpload = mStorageReference.child("Fixtures").child(UUID.randomUUID().toString()).child(UUID.randomUUID().toString());
                    fileToUpload.putFile(path2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileToUpload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUri = uri;
                                    fixture_model.setName(team_name);
                                    fixture_model.setDate(newdate);
                                    fixture_model.setType(venue);

                                    fixture_model.setImg_url(downloadUri.toString());
                                    mDatabaseReference.push().setValue(fixture_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                Toast.makeText(getActivity().getApplicationContext(), " Fixture added to database", Toast.LENGTH_SHORT).show();
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

        return v;


    }
}
