package edu.itsligo.gaaappmain.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import edu.itsligo.gaaappmain.Admin;
import edu.itsligo.gaaappmain.ProfileAdminFragment;
import edu.itsligo.gaaappmain.R;

public class EditAdminProfile extends AppCompatActivity {
    EditText etEditAdminName, etEditAdminEmail, etEditAdminPhone;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    Button saveButton;
    FirebaseUser user;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin_profile);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

        etEditAdminName = findViewById(R.id.etAdminNameSave);
        etEditAdminEmail = findViewById(R.id.etAdminEmailSave);
        etEditAdminPhone = findViewById(R.id.etAdminPhoneSave);
        saveButton = findViewById(R.id.btEditProfileSave);

        Intent data = getIntent();
        String fullName = data.getStringExtra("FullName");
        String email = data.getStringExtra("UserEmail");
        String phone = data.getStringExtra("UserPhone");

        etEditAdminName.setText(fullName);
        etEditAdminEmail.setText(email);
        etEditAdminPhone.setText(phone);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etEditAdminName.getText().toString().isEmpty() || etEditAdminEmail.getText().toString().isEmpty() || etEditAdminPhone.getText().toString().isEmpty()) {
                    Toast.makeText(EditAdminProfile.this, "Error fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = etEditAdminEmail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef = fstore.collection("Users").document(user.getUid());
                        Map<String, Object> edited = new HashMap<>();
                        edited.put("UserEmail", email);
                        edited.put("FullName", etEditAdminName.getText().toString());
                        edited.put("PhoneNumber", etEditAdminPhone.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startActivity(new Intent(getApplicationContext(),Admin.class));
                                finish();
                            }
                        });
                        Toast.makeText(EditAdminProfile.this, "Email is changed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditAdminProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}