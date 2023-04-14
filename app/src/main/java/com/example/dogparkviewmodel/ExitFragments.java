package com.example.dogparkviewmodel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogparkviewmodel.DogParkViewModel;
import com.example.dogparkviewmodel.R;


public class ExitFragments extends Fragment {
    View view;
    Button exit;
    TextView total_exit;
    DogParkViewModel viewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.activity_exit_fragments, container, false);
        total_exit=view.findViewById(R.id.total_exit_tv);
        return  view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(DogParkViewModel.class);
        exit=view.findViewById(R.id.exit_frag_btn);

        viewModel.getAllExitsString().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                total_exit.setText(s);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean min_check=viewModel.exit();
                if (min_check){
                    Toast.makeText(getContext(), "Min Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}