package edu.itsligo.gaaappmain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewsAdminFragment extends Fragment {
// test comit
    EditText ntitle, ndescription;
    FirebaseDatabase database;
    DatabaseReference ref;
    Button insert;
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
                ref.push().setValue(newTable);
// test
                Toast.makeText(getActivity().getApplicationContext(),"news added to database", Toast.LENGTH_SHORT).show();
            }
        });



        return v;


    }


}
