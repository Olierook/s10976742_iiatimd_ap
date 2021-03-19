package com.example.lckvappjudanten;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;

public class LoginDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Context c;
    private Activity a;
    private Button cancel, add, register;
    //    private FragmentTransaction ft;
    private TextView mheader;
    private TextInputLayout mfield1, mfield2;

    public LoginDialog(@NonNull Context c, Activity a
    ) {
        super(c);
        this.c = c;
        this.a = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_dialog);
        cancel = (Button) findViewById(R.id.cancel_button);
        add = (Button) findViewById(R.id.add_button);
        register = (Button) findViewById(R.id.register_button);
        mheader = (TextView) findViewById(R.id.dialog_header);
        mfield1 = (TextInputLayout) findViewById(R.id.email_entry);
        mfield2 = (TextInputLayout) findViewById(R.id.password_entry);
        cancel.setOnClickListener(this);
        add.setOnClickListener(this);
        register.setOnClickListener(this);
        mheader.setText("Inloggen");
        mfield1.setHint("Email");
        mfield2.setHint("Wachtwoord");


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button:
                dismiss();
                break;
            case R.id.add_button:
                ApiRequester.getInstance(c).login(String.valueOf(mfield1.getEditText().getText()), String.valueOf(mfield2.getEditText().getText()));
                break;
            case R.id.register_button:
                RegisterDialog rdd=new RegisterDialog(c, a);
                rdd.show();
                break;
            default:
                break;
        }
        dismiss();
    }
}
