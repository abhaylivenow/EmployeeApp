package com.example.internprojectday1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class EmployeeTaskTableActivity extends AppCompatActivity {
    
    private TextView mtaskProject;
    private TextView mtaskID;
    private TextView mtaskAssignDate;
    private TextView mtaskAssignByKey;
    private TextView mtaskAssignToKey;

    private TextView mtaskAssignEmpName;
    private TextView mtaskAssignEmpMobile;
    private TextView mtaskAssignEmpEmail;

    private TextView mtaskManagerName;
    private TextView mtaskManagerMobile;
    private TextView mtaskManagerEmail;

    private TextView mtaskHeading;
    private TextView mtaskDescription;
    private TextView mtaskPriority;

    private TextView mtaskExpectedStartDateTime;
    private TextView mtaskExpectedEndDateTime;
    private TextView mtaskAllottedHours;
    private TextView mtaskCurrentStatus;

    private TextView mtaskActualStartDateTime;
    private TextView mtaskActualEndDateTime;
    private TextView mtaskHours;
    private TextView mtaskLateStart;
    private TextView mtaskFinalState;
    private TextView mtaskRating;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_task_table);

        mtaskProject=findViewById(R.id.taskProject);
        mtaskID=findViewById(R.id.taskId);
        mtaskAssignDate=findViewById(R.id.taskAssignDate);
        mtaskAssignByKey=findViewById(R.id.taskAssignByKey);
        mtaskAssignToKey=findViewById(R.id.taskAssignToKey);

        mtaskAssignEmpName=findViewById(R.id.taskAssignEmpName);
        mtaskAssignEmpMobile=findViewById(R.id.taskAssignEmpMobile);
        mtaskAssignEmpEmail=findViewById(R.id.taskAssignEmpEmail);

        mtaskManagerName=findViewById(R.id.taskManagerName);
        mtaskManagerMobile=findViewById(R.id.taskManagerMobile);
        mtaskManagerEmail=findViewById(R.id.taskManagerEmail);

        mtaskHeading=findViewById(R.id.taskHeading);
        mtaskDescription=findViewById(R.id.taskDescription);
        mtaskPriority=findViewById(R.id.taskPriority);

        mtaskExpectedStartDateTime=findViewById(R.id.taskExpectedStartDateTime);
        mtaskExpectedEndDateTime=findViewById(R.id.taskExpectedEndDateTime);
        mtaskAllottedHours=findViewById(R.id.taskAllotedHours);
        mtaskCurrentStatus=findViewById(R.id.taskCurrentStatus);

        mtaskActualStartDateTime=findViewById(R.id.taskActualStartDateTime);
        mtaskActualEndDateTime=findViewById(R.id.taskActualEndDateTime);
        mtaskHours=findViewById(R.id.taskHours);
        mtaskLateStart=findViewById(R.id.taskLateStart);
        mtaskFinalState=findViewById(R.id.taskFinalState);
        mtaskRating=findViewById(R.id.taskRating);

        databaseReference= FirebaseDatabase.getInstance().getReference("EmployeeTaskTable").child(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                EmployeeTaskTable employeeTaskTable=snapshot.getValue(EmployeeTaskTable.class);

                try {

                    mtaskProject.setText(employeeTaskTable.getTaskProject());
                    mtaskID.setText(employeeTaskTable.getTaskID());
                    mtaskAssignDate.setText(employeeTaskTable.getTaskAssignDate());
                    mtaskAssignByKey.setText(employeeTaskTable.getTaskAssignByKey());
                    mtaskAssignToKey.setText(employeeTaskTable.getTaskAssignToKey());

                    mtaskAssignEmpName.setText(employeeTaskTable.getTaskAssignEmpName());
                    mtaskAssignEmpMobile.setText(employeeTaskTable.getTaskAssignEmpMobile());
                    mtaskAssignEmpEmail.setText(employeeTaskTable.getTaskAssignEmpEmail());

                    mtaskManagerName.setText(employeeTaskTable.getTaskManagerName());
                    mtaskManagerMobile.setText(employeeTaskTable.getTaskManagerMobile());
                    mtaskManagerEmail.setText(employeeTaskTable.getTaskManagerEmail());

                    mtaskHeading.setText(employeeTaskTable.getTaskHeading());
                    mtaskDescription.setText(employeeTaskTable.getTaskDescription());
                    mtaskPriority.setText(employeeTaskTable.getTaskPriority());

                    mtaskExpectedStartDateTime.setText(employeeTaskTable.getTaskExpectedStartDateTime());
                    mtaskExpectedEndDateTime.setText(employeeTaskTable.getTaskExpectedEndDateTime());
                    mtaskAllottedHours.setText(employeeTaskTable.getTaskAllottedHours());
                    mtaskCurrentStatus.setText(employeeTaskTable.getTaskCurrentStatus());

                    mtaskActualStartDateTime.setText(employeeTaskTable.getTaskActualStartDateTime());
                    mtaskActualEndDateTime.setText(employeeTaskTable.getTaskActualEndDateTime());
                    mtaskHours.setText(employeeTaskTable.getTaskHours());
                    mtaskLateStart.setText(employeeTaskTable.getTaskLateStart());
                    mtaskFinalState.setText(employeeTaskTable.getTaskFinalState());
                    mtaskRating.setText(employeeTaskTable.getTaskRating());

                    toast("Task Table Loaded");

                }
                catch (Exception e){
                    toast(e.toString());
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });




    }
    private  void toast(String message){
        Toast.makeText(EmployeeTaskTableActivity.this,message,Toast.LENGTH_LONG).show();
    }
}