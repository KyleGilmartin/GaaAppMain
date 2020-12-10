package edu.itsligo.gaaappmain;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContactAdminFragment  extends Fragment {
    EditText title, description;
    Button setTraining;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_admin_contact, container, false);

//        title = v.findViewById(R.id.etTitle);
//        description = v.findViewById(R.id.etDescription);
//        setTraining = v.findViewById(R.id.btnSetTraining);




//        setTraining.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!title.getText().toString().isEmpty() &&
//                        !description.getText().toString().isEmpty()) {
//                    Intent intent = new Intent(Intent.ACTION_INSERT);
//                    intent.setData(CalendarContract.Events.CONTENT_URI);
//                    intent.putExtra(CalendarContract.Events.TITLE,
//                            title.getText().toString());
//                    intent.putExtra(CalendarContract.Events.DESCRIPTION,
//                            description.getText().toString());
//                    intent.putExtra(Intent.EXTRA_EMAIL,"oisinlynch2010@gmail.com, oisin.lynch2@mail.itsligo.ie");
//                    if (intent.resolveActivity(getPackageManager()) != null) {
//                        startActivity(intent);
//                    } else{
//                        Toast.makeText(Admin.this,"There is no app that ", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(Admin.this,"Please fill out the entire form", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

        return v;
    }
}
