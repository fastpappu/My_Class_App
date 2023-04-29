package abidahsoftware.co.in.myclassapp;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class AddClassInfoFragment extends Fragment {


    View view;


    public AddClassInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_class_info, container, false);
        AppCompatButton appCompatButton = view.findViewById(R.id.add_class_btn);

        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),AddClassActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}