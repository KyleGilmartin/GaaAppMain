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
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.UUID;
import static android.app.Activity.RESULT_OK;

public class NewsAdminFragment extends Fragment {
    //REQUEST CODE FOR GETTING IMAGE
    private static final int RESULT_LOAD_IMAGE = 1;

    // test comit
    EditText ntitle, ndescription, nLink;
    FirebaseDatabase database;
    DatabaseReference ref;
    Button insert, mBtnUploadImage;
    NewTable newTable;

    //initializing image view
    private ImageView mIVNews;

    //image uri
    private Uri imageUri;

    //database references
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageReference;


    //test35r
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_news, container, false);


        //initializing database references
        mFirebaseStorage = FirebaseStorage.getInstance();
        mStorageReference = mFirebaseStorage.getReference();

        nLink = v.findViewById(R.id.etNewsLink);
        ntitle = v.findViewById(R.id.etNewsTitle);
        ndescription = v.findViewById(R.id.etNewsDescription);
        database = FirebaseDatabase.getInstance();
        insert = v.findViewById(R.id.btNewsSubmit);
        mBtnUploadImage = v.findViewById(R.id.btImageUpload);
        mIVNews = v.findViewById(R.id.ivPic);

        //getting add image button click and then getting image from gallery
        mBtnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntent();
            }
        });

        newTable = new NewTable();
        ref = FirebaseDatabase.getInstance().getReference().child("NewTable");
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(imageUri == null){
                    Snackbar.make(v, "Please select an image", BaseTransientBottomBar.LENGTH_SHORT).show();
                    return;
                }else{
                    //getting title, description
                    String etitle = ntitle.getText().toString();
                    String edesc = ndescription.getText().toString();
                    String elink = nLink.getText().toString();

                    final StorageReference fileToUpload = mStorageReference.child("News").child(UUID.randomUUID().toString()).child(UUID.randomUUID().toString());
                    fileToUpload.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileToUpload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUri = uri;
                                    newTable.setTitle(etitle);
                                    newTable.setDesc(edesc);
                                    newTable.setLink(elink);
                                    newTable.setImageURL(downloadUri.toString());
                                    ref.push().setValue(newTable).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                Toast.makeText(getActivity().getApplicationContext(), "news added to database", Toast.LENGTH_SHORT).show();
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

    private void openIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            mIVNews.setImageURI(imageUri);
        }
    }
}