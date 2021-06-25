package com.example.internprojectday1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;

public class EmployeeTaskTable extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private String mPriority,mCurrentStatus,mFinalState,mEmpName,currentUser="Admin";
    private int level;
    private EditText mtaskProject;
    private EditText mtaskID;
    private EditText mtaskAssignDate;
    private EditText mtaskAssignByKey;
    private EditText mtaskAssignToKey;

    private TextView mtaskAssignEmpName;
    private Spinner mtaskSpinnerEmpName;
    private EditText mtaskAssignEmpMobile;
    private EditText mtaskAssignEmpEmail;

    private EditText mtaskManagerName;
    private EditText mtaskManagerMobile;
    private EditText mtaskManagerEmail;

    private EditText mtaskHeading;
    private EditText mtaskDescription;
    private TextView mtaskPriority;
    private Spinner mtaskSpinnerPriority;

    private EditText mtaskExpectedStartDateTime;
    private EditText mtaskExpectedEndDateTime;
    private EditText mtaskAllottedHours;
    private TextView mtaskCurrentStatus;
    private Spinner mtaskSpinnerCurrentStatus;

    private EditText mtaskActualStartDateTime;
    private EditText mtaskActualEndDateTime;
    private EditText mtaskHours;
    private EditText mtaskLateStart;
    private TextView mtaskFinalState;
    private Spinner mtaskSpinnerFinalState;
    private EditText mtaskRating;

    private Button submitButtonOfTaskTable;

    private Query query;
    ArrayList<String> employeeList;
    ArrayAdapter<String> arrayAdapterEmployeeList;

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
        mtaskSpinnerEmpName=findViewById(R.id.taskSpinnerEmpName);
        mtaskAssignEmpMobile=findViewById(R.id.taskAssignEmpMobile);
        mtaskAssignEmpEmail=findViewById(R.id.taskAssignEmpEmail);

        mtaskManagerName=findViewById(R.id.taskManagerName);
        mtaskManagerMobile=findViewById(R.id.taskManagerMobile);
        mtaskManagerEmail=findViewById(R.id.taskManagerEmail);

        mtaskHeading=findViewById(R.id.taskHeading);
        mtaskDescription=findViewById(R.id.taskDescription);
        mtaskPriority=findViewById(R.id.taskPriority);
        mtaskSpinnerPriority=findViewById(R.id.taskSpinnerPriority);

        mtaskExpectedStartDateTime=findViewById(R.id.taskExpectedStartDateTime);
        mtaskExpectedEndDateTime=findViewById(R.id.taskExpectedEndDateTime);
        mtaskAllottedHours=findViewById(R.id.taskAllotedHours);
        mtaskCurrentStatus=findViewById(R.id.taskCurrentStatus);
        mtaskSpinnerCurrentStatus=findViewById(R.id.taskSpinnerCurrentStatus);

        mtaskActualStartDateTime=findViewById(R.id.taskActualStartDateTime);
        mtaskActualEndDateTime=findViewById(R.id.taskActualEndDateTime);
        mtaskHours=findViewById(R.id.taskHours);
        mtaskLateStart=findViewById(R.id.taskLateStart);
        mtaskFinalState=findViewById(R.id.taskFinalState);
        mtaskSpinnerFinalState=findViewById(R.id.taskSpinnerFinalStatus);
        mtaskRating=findViewById(R.id.taskRating);

        submitButtonOfTaskTable=findViewById(R.id.submitButtonofTaskTable);

        // Spinner click listener
        mtaskSpinnerPriority.setOnItemSelectedListener(this);
        mtaskSpinnerCurrentStatus.setOnItemSelectedListener(this);
        mtaskSpinnerEmpName.setOnItemSelectedListener(this);
        mtaskSpinnerFinalState.setOnItemSelectedListener(this);

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH)+1;
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        mtaskAssignDate.setText(mDay+"/"+mMonth+"/"+mYear);






        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Priority, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mtaskSpinnerPriority.setAdapter(adapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> status = ArrayAdapter.createFromResource(this,
                R.array.TaskCurrentStatus, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        status.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mtaskSpinnerCurrentStatus.setAdapter(status);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> finalState = ArrayAdapter.createFromResource(this,
                R.array.TaskFinalState, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        finalState.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mtaskSpinnerFinalState.setAdapter(finalState);

        // Array for employees

        employeeList= new ArrayList<>();

//        query.addListenerForSingleValueEvent();

        Query query2=FirebaseDatabase.getInstance().getReference("employees").orderByChild("name").equalTo(currentUser);
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    Employee employee=snapshot1.getValue(Employee.class);

                    mtaskManagerName.setText(employee.getName());
                    mtaskManagerEmail.setText(employee.getEmail());
                    mtaskManagerMobile.setText(employee.getMobile());
                    if (employee.getRights().equals("Admin")){
                        level=0;

                    }
                    else if (employee.getRights().equals("Manager")){

                        level=1;
                    }

                }
                showDetail(level);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });




        arrayAdapterEmployeeList=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,
                employeeList);

        // Specify the layout to use when the list of choices appears
        arrayAdapterEmployeeList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mtaskSpinnerEmpName.setAdapter(arrayAdapterEmployeeList);


        databaseReference= FirebaseDatabase.getInstance().getReference("EmployeeTaskTable");

        submitButtonOfTaskTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeTaskTableClass employeeTaskTable=new EmployeeTaskTableClass(mtaskProject.getText().toString(),
                        mtaskID.getText().toString(), mtaskAssignDate.getText().toString(),mtaskAssignByKey.getText().toString(),
                        mtaskAssignToKey.getText().toString(),mEmpName
//                        mtaskAssignEmpName.getText().toString()
                        ,mtaskAssignEmpMobile.getText().toString(),
                        mtaskAssignEmpEmail.getText().toString(),mtaskManagerName.getText().toString(),mtaskManagerMobile.getText().toString(),
                        mtaskManagerEmail.getText().toString(),mtaskHeading.getText().toString(),mtaskDescription.getText().toString(),mPriority,
                        mtaskExpectedStartDateTime.getText().toString(),mtaskExpectedEndDateTime.getText().toString(),mtaskAllottedHours.getText().toString(),
                        mCurrentStatus,mtaskActualStartDateTime.getText().toString(),mtaskActualEndDateTime.getText().toString(),
                        mtaskHours.getText().toString(),mtaskLateStart.getText().toString(),mFinalState,mtaskRating.getText().toString());

                databaseReference.push().setValue(employeeTaskTable);

                toast("Data added...");

            }
        });
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//
//                EmployeeTaskTable employeeTaskTable=snapshot.getValue(EmployeeTaskTable.class);
//
//                try {
//
//                    mtaskProject.setText(employeeTaskTable.getTaskProject());
//                    mtaskID.setText(employeeTaskTable.getTaskID());
//                    mtaskAssignDate.setText(employeeTaskTable.getTaskAssignDate());
//                    mtaskAssignByKey.setText(employeeTaskTable.getTaskAssignByKey());
//                    mtaskAssignToKey.setText(employeeTaskTable.getTaskAssignToKey());
//
//                    mtaskAssignEmpName.setText(employeeTaskTable.getTaskAssignEmpName());
//                    mtaskAssignEmpMobile.setText(employeeTaskTable.getTaskAssignEmpMobile());
//                    mtaskAssignEmpEmail.setText(employeeTaskTable.getTaskAssignEmpEmail());
//
//                    mtaskManagerName.setText(employeeTaskTable.getTaskManagerName());
//                    mtaskManagerMobile.setText(employeeTaskTable.getTaskManagerMobile());
//                    mtaskManagerEmail.setText(employeeTaskTable.getTaskManagerEmail());
//
//                    mtaskHeading.setText(employeeTaskTable.getTaskHeading());
//                    mtaskDescription.setText(employeeTaskTable.getTaskDescription());
//                    mtaskPriority.setText(employeeTaskTable.getTaskPriority());
//
//                    mtaskExpectedStartDateTime.setText(employeeTaskTable.getTaskExpectedStartDateTime());
//                    mtaskExpectedEndDateTime.setText(employeeTaskTable.getTaskExpectedEndDateTime());
//                    mtaskAllottedHours.setText(employeeTaskTable.getTaskAllottedHours());
//                    mtaskCurrentStatus.setText(employeeTaskTable.getTaskCurrentStatus());
//
//                    mtaskActualStartDateTime.setText(employeeTaskTable.getTaskActualStartDateTime());
//                    mtaskActualEndDateTime.setText(employeeTaskTable.getTaskActualEndDateTime());
//                    mtaskHours.setText(employeeTaskTable.getTaskHours());
//                    mtaskLateStart.setText(employeeTaskTable.getTaskLateStart());
//                    mtaskFinalState.setText(employeeTaskTable.getTaskFinalState());
//                    mtaskRating.setText(employeeTaskTable.getTaskRating());
//
//                    toast("Task Table Loaded");
//
//                }
//                catch (Exception e){
//                    toast(e.toString());
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });
//
//
//
//
    }
    private  void toast(String message){
        Toast.makeText(EmployeeTaskTable.this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        switch (parent.getId()){
            case R.id.taskSpinnerPriority:
                mPriority=item;
                break;

            case R.id.taskSpinnerCurrentStatus:
                mCurrentStatus=item;
                break;

            case R.id.taskSpinnerFinalStatus:
                mFinalState=item;
                break;
            case R.id.taskSpinnerEmpName:
                mEmpName=item;
                Query query=FirebaseDatabase.getInstance().getReference("employees").orderByChild("name").equalTo(item);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        for (DataSnapshot employee:snapshot.getChildren()){

                            Employee employee1 =employee.getValue(Employee.class);
                            mtaskAssignEmpMobile.setText(employee1.getMobile());
                            mtaskAssignEmpEmail.setText(employee1.getEmail());
                            arrayAdapterEmployeeList.notifyDataSetChanged();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

                break;

            default:toast("Something Else");

        }


//        toast("Selected Iteam : "+item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void showDetail(int level){

        query=FirebaseDatabase.getInstance().getReference("employees");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {


                for(DataSnapshot employee : snapshot.getChildren()){
                    Employee employee1 =employee.getValue(Employee.class);
                    if(level==0 && !employee1.getName().equals(currentUser)){
                        employeeList.add(employee1.getName());
                        arrayAdapterEmployeeList.notifyDataSetChanged();
                    }
                    else if (level==1 && !employee1.getName().equals(currentUser)){
                        if (employee1.getRights().equals("Manager") || employee1.getRights().equals("employee")){
                            employeeList.add(employee1.getName());
                            arrayAdapterEmployeeList.notifyDataSetChanged();

                        }
                    }
//                    employeeList.add(employee1.getName());
//                    arrayAdapterEmployeeList.notifyDataSetChanged();
//                    if (employee1.getReportingTo().equals("Manager")||employee1.getReportingTo().equals("Top Management")){
//                        employeeList.add(employee1.getName());
//                        arrayAdapterEmployeeList.notifyDataSetChanged();
//
//                    }

                }


            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

}