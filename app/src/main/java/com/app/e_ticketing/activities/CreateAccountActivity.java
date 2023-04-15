package com.app.e_ticketing.activities;

import static android.content.ContentValues.TAG;

import static com.app.e_ticketing.helpers.common_helper.collection_users;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.e_ticketing.R;
import com.app.e_ticketing.helpers.BaseActivity;
import com.app.e_ticketing.models.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccountActivity extends BaseActivity {
    Button CreateAccount,LoginBtn;
    EditText fullName,etEmail,etMobile,etAddress,etDob,etPassword,etVehicleRegNo;
    RadioGroup rgGender;
    RadioButton rbSelected;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String gender = "Male";
    String stName, stEmail, stMobile,stPassword , stDob, stAddress,stVehicleRegNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        LoginBtn = findViewById(R.id.LoginBtn);
        CreateAccount = findViewById(R.id.CreateAccount);

        fullName = findViewById(R.id.fullName);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);
        etAddress = findViewById(R.id.etAddress);
        etDob = findViewById(R.id.etDob);
        rgGender = findViewById(R.id.rgGender);
        etVehicleRegNo = findViewById(R.id.etVehicleRegNo);

        rgGender.setOnCheckedChangeListener((group, checkedId) -> {
            rbSelected = findViewById(rgGender.getCheckedRadioButtonId());
            gender = rbSelected.getText().toString();
        });


        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        CreateAccount.setOnClickListener(this::signup);
        LoginBtn.setOnClickListener(this::login);
    }

    private  void login(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    private  void signup(View view){
        Log.d(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        showProgressDialog();
            mAuth.createUserWithEmailAndPassword(stEmail, stPassword)
                    .addOnCompleteListener(task -> {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                        hideProgressDialog();
                        if (task.isSuccessful()) {
                            onUserAuthSuccess(task.getResult().getUser());
                        } else {
                            Snackbar.make(view, "error: " + task.getException().getMessage(), Snackbar.LENGTH_SHORT)
                                    .setAction("OK", view1 -> {
                                    })
                                    .setActionTextColor(getResources().getColor(R.color.red))
                                    .show();
                        }
                    });
    }

    private void onUserAuthSuccess(FirebaseUser user) {
        // Write new user
        fullName = findViewById(R.id.fullName);
        etEmail = findViewById(R.id.etEmail);
        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);
        etAddress = findViewById(R.id.etAddress);
        etDob = findViewById(R.id.etDob);
        rgGender = findViewById(R.id.rgGender);
        writeNewUser(user.getUid(), user.getEmail(),stName,stMobile,stVehicleRegNo,stAddress,stDob,gender);
        // Go to MainActivity
        Toast.makeText(this,"User account created!",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void writeNewUser(String userId, String email, String name, String mobile, String vehicleRegNo,
                              String address,String dob, String gender) {
        User user = new User(userId,email, name,mobile, address,vehicleRegNo, gender,dob ,true);
        mDatabase.child(collection_users).child(userId).setValue(user);
    }
    private boolean  validateForm(){
        boolean flag = true;
        stName = fullName.getText().toString();
        stEmail = etEmail.getText().toString();
        stMobile = etMobile.getText().toString();
        stPassword = etPassword.getText().toString();
        stDob = etDob.getText().toString();
        stVehicleRegNo = etVehicleRegNo.getText().toString();

        if(stName.isEmpty()){
            fullName.setError("Required");
            flag = false;
        }
        else if(stEmail.isEmpty()){
            etEmail.setError("Required");
            flag = false;
        }
        else if(stMobile.isEmpty()) {
            etMobile.setError("Required");
            flag = false;
        }
        else if(stPassword.isEmpty()){
            etPassword.setError("Required");
            flag = false;
        }
        else if(stDob.isEmpty()){
            etDob.setError("Required");
            flag = false;
        } else if(stVehicleRegNo.isEmpty()){
            etVehicleRegNo.setError("Required");
            flag = false;
        }
        return flag;
    }
}