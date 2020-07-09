package com.example.lckvappjudanten;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

//import static android.util.Log.*;

public class FooterFragment extends Fragment implements View.OnClickListener {

//    private FragmentActivity myContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.footer_view, container, false);

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView toPeopleButton = (TextView) getView().findViewById(R.id.people_view_button);
        toPeopleButton.setOnClickListener(this);
        TextView toStoreButton = (TextView) getView().findViewById(R.id.store_view_button);
        toStoreButton.setOnClickListener(this);
        TextView toProductButton = (TextView) getView().findViewById(R.id.product_view_button);
        toProductButton.setOnClickListener(this);
    }
//
//    @Override
//    public void onAttach(Context context) {
//        myContext=(FragmentActivity) context;
//        super.onAttach(context);
//    }

    @Override
    public void onClick(View v) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.people_view_button:
                ft.add(R.id.placeholder2, new StoreRowFragment("Anton", 19.85));
                break;

            case R.id.store_view_button:
                ft.add(R.id.placeholder1, new StoreRowFragment("Henk", 19.85));
                ft.add(R.id.placeholder3, new StoreRowFragment("Truus", 19.8));
                ft.add(R.id.placeholder4, new StoreRowFragment("Sjaak", 19.0));

                break;

            case R.id.product_view_button:
                ft.add(R.id.placeholder5, new StoreRowFragment("Erik", 420.69));
                ft.add(R.id.placeholder6, new StoreRowFragment("Robbert", 0.01));

//                startActivity(new Intent(this, ActivityToLaunch.class));
                break;

        }
        ft.commit();

    }
}
