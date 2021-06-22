package com.example.internprojectday1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList extends AppCompatActivity implements RecyclerViewAdapter.onClickListener{

    DatabaseReference mRef;
    List<String> uidList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        // Initialize Recycler view
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // List that hold all the employee for showing using recyclerView
        List<Employee> employeeList = new ArrayList<>();

        mRef = FirebaseDatabase.getInstance().getReference("employees");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                /*
                Clearing list every time when onDataChange() is called
                so that when data is changed it doesn't repeat values
                 */

                employeeList.clear();

                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    uidList.add(postSnapshot.getKey());
                    Employee employee = postSnapshot.getValue(Employee.class);
                    employeeList.add(employee);
                }

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getApplicationContext(),employeeList,EmployeeList.this::onProductCLick);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onProductCLick(int position) {

        // get the key of the clicked item user and send to the EditDetail activity
        String key = uidList.get(position);

        Intent intent = new Intent(EmployeeList.this,EditDetails.class);
        intent.putExtra("key",key);
        startActivity(intent);

    }
}