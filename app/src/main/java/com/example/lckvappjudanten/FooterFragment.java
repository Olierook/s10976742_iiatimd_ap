package com.example.lckvappjudanten;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//import static android.util.Log.*;

public class FooterFragment extends Fragment implements View.OnClickListener {

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.people_view_button:
                Log.d("test", "hoi");
                break;

            case R.id.store_view_button:
                Log.d("test2", "hoi");
                break;

            case R.id.product_view_button:
                Log.d("test3", "hoi");
//                startActivity(new Intent(this, ActivityToLaunch.class));
                break;

        }
    }
}
