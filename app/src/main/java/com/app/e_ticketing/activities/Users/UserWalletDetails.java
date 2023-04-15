package com.app.e_ticketing.activities.Users;

import static com.app.e_ticketing.helpers.common_helper.collection_wallets;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.e_ticketing.R;
import com.app.e_ticketing.helpers.BaseActivity;
import com.app.e_ticketing.models.Wallet;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserWalletDetails extends BaseActivity {
    Wallet wallet = new Wallet();
    TextView tvWalletAmount;
    Button btnDeposit;
    String return_activity,activity;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_wallet_details);
        findViewById(R.id.btnBack).setOnClickListener(view->{
            startActivity(new Intent(getApplicationContext(), DashboardUserActivity.class));
        });
        tvWalletAmount = findViewById(R.id.tvWalletAmount);
        btnDeposit = findViewById(R.id.btnDeposit);
        firebaseDatabase = FirebaseDatabase.getInstance();

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        getWalletDetails();
        btnDeposit.setOnClickListener(view->{
            //show bottom nav for payment
            paymentWindow();
        });
    }

    boolean check= false;
    private void getWalletDetails()
    {
        showProgressDialog();
        databaseReference = firebaseDatabase.getReference(collection_wallets);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() != 0) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        try {
                            String id = ds.child("id").getValue(String.class);
                            String user_id = ds.child("uid").getValue(String.class);
                            if (user_id.equals(mAuth.getUid())) {
                                check= true;
                                double amount = ds.child("amount").getValue(double.class);
                                Boolean status = ds.child("status").getValue(boolean.class);
                               wallet = new Wallet(id,user_id,"",amount,status);
                                showDetails(wallet);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if(!check){
                        tvWalletAmount.setText("0.00");
                    }
                }
                hideProgressDialog();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void showDetails(Wallet wallet){
        tvWalletAmount.setText(wallet.getAmount()+"");
    }

    private  void paymentWindow(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.activity_deposit_funds);
        bottomSheetDialog.show();
        EditText etAmount_pay;
        Button btnProceed;
        etAmount_pay = bottomSheetDialog.findViewById(R.id.etAmount_pay);
        btnProceed = bottomSheetDialog.findViewById(R.id.btnProceed);

        btnProceed.setOnClickListener(view->{
            if (Double.parseDouble(tvWalletAmount.getText().toString()) > 0){
                double amount_tot = Double.parseDouble(etAmount_pay.getText().toString()) + wallet.getAmount();

                Wallet wallet_new = new Wallet(wallet.getId(), mAuth.getUid(), "", amount_tot,  true);
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child(collection_wallets).child(wallet.getId()).setValue(wallet_new)
                        .addOnSuccessListener(command -> {
                            Toast.makeText(this, "Deposited!", Toast.LENGTH_SHORT).show();
                            hideProgressDialog();
                            startActivity(new Intent(this, UserWalletDetails.class));
                        })
                        .addOnFailureListener(command -> {
                                    hideProgressDialog();
                                    Toast.makeText(this, "failed to add funds!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(this, UserWalletDetails.class));
                                }
                        );
            }else{
                String uKey = firebaseDatabase.getReference(collection_wallets).push().getKey();
                double amount_tot = Double.parseDouble(etAmount_pay.getText().toString());

                Wallet wallet_new = new Wallet(uKey, mAuth.getUid(), "", amount_tot,  true);
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child(collection_wallets).child(uKey).setValue(wallet_new)
                        .addOnSuccessListener(command -> {
                            Toast.makeText(this, "Deposited!", Toast.LENGTH_SHORT).show();
                            hideProgressDialog();
                            startActivity(new Intent(this, UserWalletDetails.class));
                        })
                        .addOnFailureListener(command -> {
                                    hideProgressDialog();
                                    Toast.makeText(this, "failed to add funds!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(this, UserWalletDetails.class));
                                }
                        );
            }
            hideProgressDialog();
            bottomSheetDialog.dismiss();
        });
    }
}
