package com.example.internprojectday1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditDetails extends AppCompatActivity {

    EditText edtName, edtId, edtMobile, edtEmail, edtDesignation, edtDOJ, edtRights, edtReportTo;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        initView();

        Intent intent = getIntent();
        // get the clicked item key from EmployeeList activity
        // and use this key as reference to show data in fields to update
        String key = intent.getStringExtra("key");

        mRef = FirebaseDatabase.getInstance().getReference();
        mRef.child("employees").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String id = snapshot.child("id").getValue().toString();
                edtId.setText(id);

                String name = snapshot.child("name").getValue().toString();
                edtName.setText(name);

                String mobile = snapshot.child("mobile").getValue().toString();
                edtMobile.setText(mobile);

                String email = snapshot.child("email").getValue().toString();
                edtEmail.setText(email);

                String designation = snapshot.child("designation").getValue().toString();
                edtDesignation.setText(designation);

                String doj = snapshot.child("doj").getValue().toString();
                edtDOJ.setText(doj);

                String rights = snapshot.child("rights").getValue().toString();
                edtRights.setText(rights);

                String reportingTo = snapshot.child("reportingTo").getValue().toString();
                edtReportTo.setText(reportingTo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initView(){
        edtName = findViewById(R.id.mEmpName);
        edtId = findViewById(R.id.mEmpId);
        edtMobile = findViewById(R.id.mEmpMobile);
        edtEmail = findViewById(R.id.mEmpEmail);
        edtDesignation = findViewById(R.id.mEmpDesg);
        edtDOJ = findViewById(R.id.mEmpDoj);
        edtRights = findViewById(R.id.mEmpRights);
        edtReportTo = findViewById(R.id.mEmpReportTo);
    }
}