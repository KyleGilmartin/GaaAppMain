package edu.itsligo.gaaappmain;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FixturesAdminFragment extends Fragment {
    ImageView imageView;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fixture_admin, container, false);


        imageView = v.findViewById(R.id.image1);

        Spinner spinner = v.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.Teams, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               String result = (String) spinner.getSelectedItem();
               TextView display = (TextView) v.findViewById (R.id.opponent);
                display.setText(result);

                Log.d("Teams", "onItemSelected:  " + result);

                if (result == "Sligo"){
                    imageView.setImageResource(R.drawable.sligocrest);
                }
                if (result == "Mayo"){
                    imageView.setImageResource(R.drawable.mayocrest);
                }
                if (result == "Galway"){
                    imageView.setImageResource(R.drawable.galwaycrest);
                }
                if (result == "Leitrim"){
                    imageView.setImageResource(R.drawable.leitrimcrest);
                }
                if (result == "Roscommon"){
                    imageView.setImageResource(R.drawable.roscommoncrest);
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
                TextView display1 = (TextView) v.findViewById (R.id.venue);
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
                TextView date = v.findViewById(R.id.date1);
                date.setText(Date);
            }
        });

        return v;


    }
}
