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


public class EntryFragments extends Fragment {


    View view;
    Button enter;
    TextView total_entries;
    DogParkViewModel viewModel;
    String total_ent="Total Entries:0";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.activity_entry_fragments, container, false);
        total_entries = (TextView) view.findViewById(R.id.total_entries_tv);
        return  view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(DogParkViewModel.class);
        enter=view.findViewById(R.id.enter_frag_btn);

        viewModel.getAllEntriesString().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                total_entries.setText(s);
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean max_check=viewModel.enter();
                if (max_check){
                    Toast.makeText(getContext(), "Max Limit Reached", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}