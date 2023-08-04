package com.example.labreport;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class change_password extends AppCompatDialogFragment implements TextWatcher {
    EditText password,confirm_password;
    Listener listener;
    TextView error;
    String Password;
    String Confirm_password;
    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.change_password,null);
        error = (TextView) view.findViewById(R.id.error_text);

        builder.setView(view);
        builder.setTitle("Change Password");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                password = (EditText) view.findViewById(R.id.password);
                confirm_password = (EditText) view.findViewById(R.id.confirm_password);
                password.addTextChangedListener(change_password.this);
                confirm_password.addTextChangedListener(change_password.this);
                Password = password.getText().toString();
                Confirm_password = confirm_password.getText().toString();
                if (Password != Confirm_password) {
                    error.setText("Confirm Password Doesn't Match");
                }
                else {
                    byte[] hashed = Password.getBytes(StandardCharsets.UTF_8);
                    String Hashed_Password = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        Hashed_Password = Base64.getEncoder().encodeToString(hashed);
                    }
                    listener.applyTexts(Hashed_Password);
                }
            }
        });


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) throws ClassCastException{
        super.onAttach(context);
        listener = (Listener) context;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if(Password!=Confirm_password){
            error.setText("Confirm Password Doesn't Match");
        }
    }

    public interface Listener{
        void applyTexts(String Password);
    }
}
