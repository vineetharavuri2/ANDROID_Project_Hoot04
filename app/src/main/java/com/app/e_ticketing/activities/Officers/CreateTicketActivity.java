package com.app.e_ticketing.activities.Officers;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.e_ticketing.R;
import com.app.e_ticketing.helpers.BaseActivity;

public class CreateTicketActivity extends BaseActivity{
    Button CreateTicket;
    EditText fullName,etVehicleRegNo,etFine,etLocation,etNotes;
    String stFullName,stVehicleRegNo,stFine,stLocation,stNotes;
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

        findViewById(R.id.btnBack).setOnClickListener(view->{
            this.finish();
        });
        CreateTicket.setOnClickListener(view->{
            createTicket();
        });
    }
    private void createTicket()
    {
        if (!validateForm()) {
            return;
        }
        Toast.makeText(this, "Ticket will be saved!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MyTicketsOfficerActivity.class));
    }
    private boolean validateForm() {
        boolean result = true;

        stFullName = fullName.getText().toString().trim();
        stVehicleRegNo = etVehicleRegNo.getText().toString().trim();
        stFine = etFine.getText().toString().trim();
        stLocation = etLocation.getText().toString().trim();
        stNotes = etNotes.getText().toString().trim();

        

            if (TextUtils.isEmpty(stFullName)) {
                fullName.setError("Required");
                result = false;
            }
            else fullName.setError(null);

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
            
        return result;
    }

}