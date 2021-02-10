package edu.itsligo.gaaappmain.Lotto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.itsligo.gaaappmain.R;

public class SetLottoDate extends AppCompatActivity {

    CalendarView mcalendarView;
    TextView mselectedDate;
    Button mSaveDate;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_lotto_date);


        databaseReference = FirebaseDatabase.getInstance().getReference("LottoDate");

        mcalendarView = findViewById(R.id.calendarViewAdminDate);
        mselectedDate = findViewById(R.id.tvAdminDateSelected);
        mSaveDate = findViewById(R.id.btnSaveDate);

        mcalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = i2 + "/" + (i1 + 1) + "/" + i;
                mselectedDate.setText(date);
            }
        });

        mSaveDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newdate = mselectedDate.getText().toString();

                if (mselectedDate == null) {
                    Toast.makeText(SetLottoDate.this, "Please select a date", Toast.LENGTH_SHORT).show();
                } else {

                   LottoDataModel lottoDataModel = new LottoDataModel(newdate);
                   databaseReference.setValue(lottoDataModel);

                    Toast.makeText(SetLottoDate.this, "New date saved", Toast.LENGTH_SHORT).show();
                }


                Intent intent = new Intent(SetLottoDate.this, AdminFragmentLotto.class);
                startActivity(intent);
            }
        });


    }
}