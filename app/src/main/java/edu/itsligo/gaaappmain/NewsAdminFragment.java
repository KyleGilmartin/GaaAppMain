package edu.itsligo.gaaappmain;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class NewsAdminFragment extends Fragment {
    // test comit
    EditText ntitle, ndescription;
    FirebaseDatabase database;
    DatabaseReference ref;
    Button insert, upload;
    NewTable newTable;


    //test35r
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_news, container, false);


        ntitle = v.findViewById(R.id.etNewsTitle);
        ndescription = v.findViewById(R.id.etNewsDescription);
        database = FirebaseDatabase.getInstance();
        insert = v.findViewById(R.id.btNewsSubmit);


        newTable = new NewTable();
        ref = FirebaseDatabase.getInstance().getReference().child("NewTable");
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String etitle = ntitle.getText().toString();
                String edesc = ndescription.getText().toString();
                newTable.setTitle(etitle);
                newTable.setDesc(edesc);
                ref.push().setValue(newTable).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity().getApplicationContext(), "news added to database", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "Ops..Somethings went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }







        });




        return v;


    }


}
