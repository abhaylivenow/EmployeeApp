package com.example.internprojectday1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends AppCompatActivity implements RecyclerViewAdapter.onClickListener{

    DatabaseReference mRef;
    List<Task> taskList = new ArrayList<>();
    List<String> keyList = new ArrayList<>();
    RecyclerView recyclerView;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    String todayTaskTitle,todayTaskDescription;
    String yesterdayTaskTitle,yesterdayTaskDescription;
    String tommorowTaskTitle,tommorowTaskDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        viewPager=findViewById(R.id.viewpager1);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.today);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

                if(item.getItemId() == R.id.today){
                    addDataToRecyclerViewToday();
                    Toast.makeText(TaskList.this, "Today", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if(item.getItemId() == R.id.todo){
                    showToDoTask();
                    return true;
                }else if(item.getItemId() == R.id.inProgress){
                    showInProgressTask();
                    return true;
                }
                else if(item.getItemId() == R.id.completed){
                    showCompletedTask();
                    return true;
                }
                else if(item.getItemId() == R.id.overDue){
                    showOverDueTask();
                    return true;
                }

                return false;
            }
        });
  /*
        Query query = mRef.child("taskTable").orderByChild("startTime").equalTo("Today");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    todayTaskTitle=postSnapshot.child("head").getValue().toString();
                    todayTaskDescription=postSnapshot.child("des").getValue().toString();
                    sendtodaytask(todayTaskTitle,todayTaskDescription);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        Query query1 = mRef.child("taskTable").orderByChild("startTime").equalTo("Tomorrow");
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    tommorowTaskTitle=postSnapshot.child("head").getValue().toString();
                    tommorowTaskDescription=postSnapshot.child("des").getValue().toString();
                    sendtodaytask(tommorowTaskTitle,tommorowTaskDescription);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        Query query3 = mRef.child("taskTable").orderByChild("startTime").equalTo("Yesterday");
        query3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    yesterdayTaskTitle=postSnapshot.child("head").getValue().toString();
                    yesterdayTaskDescription=postSnapshot.child("des").getValue().toString();
                    sendtodaytask(yesterdayTaskTitle,yesterdayTaskDescription);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
*/
        addDataToRecyclerViewToday();
    }

    @Override
    public void onProductCLick(int position) {
        Toast.makeText(this, "Item clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),task_detail.class);

        String clickedItemKey = keyList.get(position);
        intent.putExtra("key",clickedItemKey);

        startActivity(intent);
    }

    private void addDataToRecyclerViewToday(){

        mRef = FirebaseDatabase.getInstance().getReference("taskTable");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                taskList.clear();
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    String key = postSnapshot.getKey();
                    keyList.add(key);
                    System.out.println("All keys are: " + key);

                    mRef.child(key).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            String taskId = snapshot.child("taskId").getValue().toString();
                            String head = snapshot.child("head").getValue().toString();
                            String des = snapshot.child("des").getValue().toString();
                            String priority = snapshot.child("priority").getValue().toString();
                            String startTime = snapshot.child("startTime").getValue().toString();
                            String endTime = snapshot.child("endTime").getValue().toString();
                            String status = snapshot.child("status").getValue().toString();

                            Task currTask = new Task(taskId,head,des,priority,startTime,endTime,status,"NA");
                            taskList.add(currTask);

                            RecyclerViewAdapter adapter = new RecyclerViewAdapter(getApplicationContext(),taskList, TaskList.this::onProductCLick);
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void showToDoTask(){
        addDataToRecyclerView("To do");
    }

    private void showInProgressTask(){
        addDataToRecyclerView("In progress");
    }

    private void showCompletedTask(){
        addDataToRecyclerView("Done");
    }

    private void showOverDueTask(){
        addDataToRecyclerView("Over due");
    }

    private void addDataToRecyclerView(String parent){

        mRef = FirebaseDatabase.getInstance().getReference("taskTable");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                taskList.clear();
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    String key = postSnapshot.getKey();
                    keyList.add(key);

                    mRef.child(key).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                            String taskId = snapshot.child("taskId").getValue().toString();
                            String head = snapshot.child("head").getValue().toString();
                            String des = snapshot.child("des").getValue().toString();
                            String priority = snapshot.child("priority").getValue().toString();
                            String startTime = snapshot.child("startTime").getValue().toString();
                            String endTime = snapshot.child("endTime").getValue().toString();
                            String status = snapshot.child("status").getValue().toString();

                            if(status.equals(parent)){
                                Task currTask = new Task(taskId,head,des,priority,startTime,endTime,status,"NA");
                                taskList.add(currTask);

                                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getApplicationContext(),taskList, TaskList.this::onProductCLick);
                                recyclerView.setAdapter(adapter);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}