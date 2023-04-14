package com.example.dogparkviewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DogParkViewModel viewModel;
    boolean current=true;
    EditText max_edit,inpark_dogs_edit,humans_edit;
    Button change_mode,reset,double_entry,change_max,change_dogs_inpark;
    ImageButton chage_humans;
    Fragment entryFragment, exitFragment;
    TextView t1,t2;



            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                change_mode=findViewById(R.id.change_mode_btn);
                reset=findViewById(R.id.reset_btn);
                double_entry=findViewById(R.id.double_entry_btn);
                change_max=findViewById(R.id.change_max_btn);
                change_dogs_inpark=findViewById(R.id.dogs_btn);
                chage_humans=findViewById(R.id.humans_btn);
                humans_edit=findViewById(R.id.Humans_edit_tv);
                max_edit=findViewById(R.id.maximum_edit_tv);
                inpark_dogs_edit=findViewById(R.id.dogs_edit_tv);
                t1=findViewById(R.id.dogs_in_park_tv);
                t2=findViewById(R.id.humans_in_park_tv);

                entryFragment=new EntryFragments();
                exitFragment=new ExitFragments();

                firstTimeReplaceFragment(entryFragment);

                change_mode.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        current=!current;
                        if(current){
                            replaceFragement(entryFragment);
                        }
                        else{
                            replaceFragement(exitFragment);
                        }
                    }
                });

                viewModel = new ViewModelProvider(this).get(DogParkViewModel.class);

                viewModel.getInParkString().observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        t1.setText(s);
                    }
                });

                viewModel.getAllHumansString().observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        t2.setText(s);
                    }
                });

                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewModel.reset();
                        Toast.makeText(MainActivity.this, "Reset Successful", Toast.LENGTH_SHORT).show();
                    }
                });

                double_entry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewModel.doubleEntry();
                    }
                });

                change_max.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String maxStr=max_edit.getText().toString();
                        if (maxStr.length() ==0){
                            return;
                        }
                        int maxVal=Integer.parseInt(maxStr);
                        boolean max_change_check=viewModel.changeMax(maxVal);
                        if(max_change_check){
                            Toast.makeText(MainActivity.this, "Max Limit Changed", Toast.LENGTH_SHORT).show();
                            max_edit.setText("");
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Unable To Change", Toast.LENGTH_SHORT).show();
                            max_edit.setText("");
                        }
                    }
                });

                change_dogs_inpark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String dogsStr=inpark_dogs_edit.getText().toString();
                        if (dogsStr.length() ==0){
                            return;
                        }
                        int dogsVal=Integer.parseInt(dogsStr);
                        boolean dogs_in_park_change_check=viewModel.changeDogsInPark(dogsVal);
                        if (dogs_in_park_change_check){
                            Toast.makeText(MainActivity.this, "Dogs In Park Changed", Toast.LENGTH_SHORT).show();
                            inpark_dogs_edit.setText("");
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Unable To Change", Toast.LENGTH_SHORT).show();
                            inpark_dogs_edit.setText("");
                        }
                    }
                });

                chage_humans.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String humansStr=humans_edit.getText().toString();
                        if (humansStr.length() ==0){
                            return;
                        }
                        int humansVal=Integer.parseInt(humansStr);
                        boolean humans_change_check=viewModel.changeHumans(humansVal);
                        if (humans_change_check){
                            Toast.makeText(MainActivity.this, "Humans Entered", Toast.LENGTH_SHORT).show();
                            humans_edit.setText("");
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Unable To Change", Toast.LENGTH_SHORT).show();
                            humans_edit.setText("");
                        }
                    }
                });
            }

            public void firstTimeReplaceFragment(Fragment fragment){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_fragment ,fragment);
                fragmentTransaction.commit();
            }

            public void replaceFragement(Fragment fragment){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_fragment ,fragment);
                fragmentTransaction.commit();
            }



}