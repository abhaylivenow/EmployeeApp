package com.example.internprojectday1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    DatabaseReference mRef;
    EditText edtName, edtId, edtMobile, edtEmail, edtDesignation, edtDOJ, edtRights, edtReportTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        Button btn = findViewById(R.id.mSubmitBtn);
        initView();
        mRef = FirebaseDatabase.getInstance().getReference("employees");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // receive all data entered by user and make an object of user

                String id = edtId.getText().toString();
                String name = edtName.getText().toString() ;
                String mobile = edtMobile.getText().toString();
                String email = edtEmail.getText().toString();
                String designation = edtDesignation.getText().toString();
                String reportingTo = edtReportTo.getText().toString();
                String DOJ  = edtDOJ.getText().toString();
                String rights = edtRights.getText().toString();

                // Create an object of employee from above data and push it to database
                Employee newEmployee = new Employee(id,name,mobile,email,designation,reportingTo,DOJ,rights);
                mRef.push().setValue(newEmployee);

                // at the same time jump to Employee list activity
                Intent intent = new Intent(MainActivity.this,EmployeeList.class);
                startActivity(intent);

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