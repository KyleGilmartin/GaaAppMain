package edu.itsligo.gaaappmain;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragmentContact extends Fragment {
    private String mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;
    private Button buttonSend;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.homefagment_contact, container, false);
//       mEditTextTo = v.findViewById(R.id.edit_text_to);
        mEditTextTo = ("project300year3@gmail.com");
        mEditTextSubject = v.findViewById(R.id.edit_text_subject);
        mEditTextMessage = v.findViewById(R.id.edit_text_message);
        buttonSend = v.findViewById(R.id.button_send);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMail();

            }
        });

        return v;
    }

    private void sendMail() {
        String recipientList = mEditTextTo.toString();
        String[] recipients = recipientList.split(",");
        String subject = mEditTextSubject.getText().toString();
        String message = mEditTextMessage.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }


}


