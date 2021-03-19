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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class ProductDialog extends Dialog implements
        View.OnClickListener {

    private Context c;
    private Activity a;
    private String header;
    private String camperId;

    private Button cancel;
    private TextView mheader;


    public ProductDialog(@NonNull Context c, Activity a, String header) {
        super(c);
        this.c = c;
        this.a = a;
        this.header = header;
    }

    public void setHeader (String header) {
        this.header = header;
    }

    public void setCamperId (String camperId) {
        this.camperId = camperId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.product_dialog);
//        FragmentTransaction ft = ((MainActivity) a).getSupportFragmentManager().beginTransaction();
//        ProductRecyclerFragment productRecyclerFragment = new ProductRecyclerFragment();
//        Bundle bundle = new Bundle();
//        bundle.putBoolean("message", true);
//        productRecyclerFragment.setArguments(bundle);
//        ft.replace(R.id.recycler_fragment, productRecyclerFragment);
//        ft.commit();
        cancel = (Button) findViewById(R.id.cancel_button);
        mheader = (TextView) findViewById(R.id.dialog_header);
        cancel.setOnClickListener(this);
        mheader.setText(this.header);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button:
                 hide();
                break;
            case R.id.add_button:
                AppDatabase db = AppDatabase.getInstance(c);
//                if (header == "Deelnemer toevoegen") {
//                    db.camperDao().insertAll(new Camper(
//                            String.valueOf(mfield1.getEditText().getText()),
//                            1,
//                            Double.parseDouble(String.valueOf(mfield2.getText())),
//                            Double.parseDouble(String.valueOf(mfield2.getText()))
//                    ));
//                    ((CamperOverviewActivity) a).populateStore();
//                    break;
//                } else {
//                    db.productDao().insertAll(new Product(
//                            String.valueOf(mfield1.getEditText().getText()),
//                            Double.parseDouble(String.valueOf(mfield2.getText()))
//                    ));
//                    FragmentManager fm = ((ProductOverviewActivity) a).getSupportFragmentManager();
//                    Fragment recycler = fm.findFragmentById(R.id.product_recycler);
//                    if (recycler instanceof ProductRecyclerFragment) {
//                        ((ProductRecyclerFragment) recycler).productAdded();
//                    }
//                };
            default:
                break;
        }
        hide();
    }
}