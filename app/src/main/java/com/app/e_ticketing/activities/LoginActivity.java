package com.app.e_ticketing.activities;
import static android.content.ContentValues.TAG;
import static com.app.e_ticketing.helpers.common_helper.collection_users;
import static com.app.e_ticketing.helpers.common_helper.getOfficerLogin;
import static com.app.e_ticketing.helpers.common_helper.getUserLogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.e_ticketing.R;
import com.app.e_ticketing.activities.Officers.DashboardOfficerActivity;
import com.app.e_ticketing.activities.Users.DashboardUserActivity;
import com.app.e_ticketing.helpers.BaseActivity;
import com.app.e_ticketing.models.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    Button btnLogin;
    TextView tvCreateAccount;
    EditText etEmail, etPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.LoginBtn);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        tvCreateAccount = findViewById(R.id.CreateAccount);
        btnLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LoginBtn:
                String stEmail = etEmail.getText().toString();
                String stPassword = etPassword.getText().toString();
                if(!stEmail.isEmpty() && !stPassword.isEmpty()){
                    if(getOfficerLogin(stEmail,stPassword)) {
                        Toast.makeText(getApplicationContext(),"Login Success!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), DashboardOfficerActivity.class));
                    }
                    else{
                        signIn(etEmail.getText().toString().trim(), etPassword.getText().toString().trim(), v);
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"email (or) password should not be empty!",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.CreateAccount:
                startActivity(new Intent(getApplicationContext(), CreateAccountActivity.class));
                break;
            default:
                break;
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private void signIn(String email, String password, View view) {
        Log.d(TAG, "signIn:" + email);
        showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                    // If sign in fails, display a message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "signInWithEmail", task.getException());
                        Snackbar.make(view, "Invalid Credentials!!", Snackbar.LENGTH_SHORT)
                                .setAction("OK", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view1) {
                                    }
                                })
                                .setActionTextColor(getResources().getColor(R.color.red))
                                .show();
                    }
                    else{
                        //check user role
                        mDatabase = FirebaseDatabase.getInstance().getReference();
                            mDatabase.child(collection_users).child(Objects.requireNonNull(mAuth.getUid())).get().addOnCompleteListener(task0 -> {
                                if (task0.isSuccessful()) {
                                    Toast.makeText(this, "Hello!", Toast.LENGTH_SHORT).show();
                                    User user = task0.getResult().getValue(User.class);
                                    try {
                                        assert user != null;
                                        if (user.isStatus())
                                            startActivity(new Intent(getApplicationContext(), DashboardUserActivity.class));
                                        else {
                                            Toast.makeText(this, "user blocked!", Toast.LENGTH_SHORT).show();
                                            mAuth.signOut();
                                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                        }
                                    } catch (Exception ex) {
                                        ex.getMessage();
                                        mAuth.signOut();
                                        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    }
                                }else{
                                    Toast.makeText(this, "invalid user!", Toast.LENGTH_SHORT).show();
                                }
                            });
                    }
                    // [START_EXCLUDE]
                    hideProgressDialog();
                    // [END_EXCLUDE]
                });
        // [END sign_in_with_email]
    }

}