package com.app.e_ticketing.activities.Officers;

import static com.app.e_ticketing.helpers.common_helper.collection_tickets;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.e_ticketing.R;
import com.app.e_ticketing.helpers.BaseActivity;
import com.app.e_ticketing.models.Ticket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateTicketActivity extends BaseActivity{
    Button CreateTicket;
    EditText fullName,etVehicleRegNo,etFine,etLocation,etNotes,etRule;
    String stFullName,stVehicleRegNo,stFine,stLocation,stNotes,stRule;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket);
        CreateTicket = findViewById(R.id.CreateTicket);

        fullName = findViewById(R.id.fullName);
        etVehicleRegNo = findViewById(R.id.etVehicleRegNo);
        etFine = findViewById(R.id.etFine);
        etLocation = findViewById(R.id.etLocation);
        etNotes = findViewById(R.id.etNotes);
        etRule = findViewById(R.id.etRule);

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
        CreateTicket.setOnClickListener(view->{
            createTicket();
        });
    }

    private void createTicket(){
        if (!validateForm()) {
            return;
        }
        showProgressDialog();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        String uKey = mDatabase.getDatabase().getReference(collection_tickets).push().getKey();

        Ticket ticket = new Ticket(uKey, stRule,"",stFullName,currentDateandTime,stNotes,stLocation,
                Double.parseDouble(stFine),stVehicleRegNo,false,"active");

        mDatabase.child(collection_tickets).child(uKey).setValue(ticket)
                .addOnSuccessListener(command -> {
                    Toast.makeText(this, "Ticket issued!", Toast.LENGTH_SHORT).show();
                    hideProgressDialog();
                    startActivity(new Intent(this, MyTicketsOfficerActivity.class));
                })
                .addOnFailureListener(command -> {
                            hideProgressDialog();
                            Toast.makeText(this, "failed to add book!", Toast.LENGTH_SHORT).show();
                        }
                );
    }

    private boolean validateForm() {
        boolean result = true;

        stFullName = fullName.getText().toString().trim();
        stVehicleRegNo = etVehicleRegNo.getText().toString().trim();
        stFine = etFine.getText().toString().trim();
        stLocation = etLocation.getText().toString().trim();
        stNotes = etNotes.getText().toString().trim();
        stRule = etRule.getText().toString().trim();

            if (TextUtils.isEmpty(stFullName)) {
                fullName.setError("Required");
                result = false;
            } else fullName.setError(null);

            if (TextUtils.isEmpty(stVehicleRegNo)) {
                etVehicleRegNo.setError("Required");
                result = false;
            } else etVehicleRegNo.setError(null);

            if (TextUtils.isEmpty(stFine)) {
                etFine.setError("Required");
                result = false;
            } else etFine.setError(null);

            if (TextUtils.isEmpty(stLocation)) {
                etLocation.setError("Required");
                result = false;
            } else etLocation.setError(null);

            if (TextUtils.isEmpty(stNotes)) {
                etNotes.setError("Required");
                result = false;
            } else {
                etNotes.setError(null);
            }
            if (TextUtils.isEmpty(stRule)) {
                etRule.setError("Required");
                result = false;
            } else {
                etRule.setError(null);
            }
        return result;
    }

}
