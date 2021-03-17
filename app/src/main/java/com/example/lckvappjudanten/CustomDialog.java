package com.example.lckvappjudanten;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class CustomDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Context c;
    private Activity a;
    private String header;
    private String field1;
    private String field2;
    private Button cancel, add;
//    private FragmentTransaction ft;
    private TextView mheader;
    private TextInputLayout mfield1;
    private EditText mfield2;

    public CustomDialog(@NonNull Context c, Activity a, String header, String field1, String field2
//                        ,FragmentTransaction ft
    ) {
        super(c);
        this.c = c;
        this.a = a;
        this.header = header;
        this.field1 = field1;
        this.field2 = field2;
//        this.ft = ft;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        cancel = (Button) findViewById(R.id.cancel_button);
        add = (Button) findViewById(R.id.add_button);
        mheader = (TextView) findViewById(R.id.dialog_header);
        mfield1 = (TextInputLayout) findViewById(R.id.name_entry);
        mfield2 = (EditText) findViewById(R.id.balance_entry);
        cancel.setOnClickListener(this);
        add.setOnClickListener(this);
        mheader.setText(this.header);
        mfield1.setHint(this.field1);
        mfield2.setHint(this.field2);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button:
                 dismiss();
                break;
            case R.id.add_button:
                AppDatabase db = AppDatabase.getInstance(c);
                if (header == "Deelnemer toevoegen") {
                    db.camperDao().insertAll(new Camper(
                            String.valueOf(mfield1.getEditText().getText()),
                            1,
                            Double.parseDouble(String.valueOf(mfield2.getText())),
                            Double.parseDouble(String.valueOf(mfield2.getText()))
                    ));
                    ((CamperOverviewActivity) a).populateStore();
                    break;
                } else {
                    db.productDao().insertAll(new Product(
                            String.valueOf(mfield1.getEditText().getText()),
                            Double.parseDouble(String.valueOf(mfield2.getText()))
                    ));
                    FragmentManager fm = ((ProductOverviewActivity) a).getSupportFragmentManager();
                    Fragment recycler = fm.findFragmentById(R.id.product_recycler);
                    if (recycler instanceof ProductRecyclerFragment) {
                        ((ProductRecyclerFragment) recycler).productAdded();
                    }
                };
            default:
                break;
        }
        dismiss();
    }
}