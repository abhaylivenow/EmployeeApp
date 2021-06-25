package com.example.internprojectday1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class task_detail extends AppCompatActivity {

    EditText edtTaskHead, edtDes, edtTaskPriority, edtTaskStartTime, edtTaskEndTime, edtTaskStatus;
    Button btnMakeChanges;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        initViews();

        Intent intent = getIntent();
        String key = intent.getStringExtra("key");

        mRef = FirebaseDatabase.getInstance().getReference("taskTable");

        mRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                String head = snapshot.child("head").getValue().toString();
                edtTaskHead.setText(head);

                String des = snapshot.child("des").getValue().toString();
                edtDes.setText(des);

                String priority = snapshot.child("priority").getValue().toString();
                edtTaskPriority.setText(priority);

                String startTime = snapshot.child("startTime").getValue().toString();
                edtTaskStartTime.setText(startTime);

                String endTime = snapshot.child("endTime").getValue().toString();
                edtTaskEndTime.setText(endTime);

                String status = snapshot.child("status").getValue().toString();
                edtTaskStatus.setText(status);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        btnMakeChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRef.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        String newHead = edtTaskHead.getText().toString();
                        snapshot.child("head").getRef().setValue(newHead);

                        String newDes = edtDes.getText().toString();
                        snapshot.child("des").getRef().setValue(newDes);

                        String newPriority = edtTaskPriority.getText().toString();
                        snapshot.child("priority").getRef().setValue(newPriority);

                        String newStartTime = edtTaskStartTime.getText().toString();
                        snapshot.child("startTime").getRef().setValue(newStartTime);

                        String newEndTime = edtTaskEndTime.getText().toString();
                        snapshot.child("endTime").getRef().setValue(newEndTime);

                        String newStatus = edtTaskStatus.getText().toString();
                        snapshot.child("status").getRef().setValue(newStatus);

                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
            Intent intent = new Intent(getApplicationContext(),TaskList.class);
            Toast.makeText(task_detail.this, "Changes made successfully", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            }
        });

    }
    private void initViews(){
        edtTaskHead = findViewById(R.id.taskDetail_taskHeading);
        edtDes = findViewById(R.id.taskDetail_taskDescription);
        edtTaskPriority = findViewById(R.id.taskDetail_taskPriority);
        edtTaskStartTime = findViewById(R.id.taskDetail_taskStartTime);
        edtTaskEndTime = findViewById(R.id.taskDetail_taskEndTime);
        edtTaskStatus = findViewById(R.id.taskDetail_status);
        btnMakeChanges = findViewById(R.id.btn_makeChanges);
    }
}