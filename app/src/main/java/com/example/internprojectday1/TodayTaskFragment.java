package com.example.internprojectday1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;


public class TodayTaskFragment extends Fragment {

    Bundle bundle;

    DatabaseReference reff;
    TextView todayTaskTitle,todayTaskDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        reff= FirebaseDatabase.getInstance().getReference();


        final View rootView = inflater.inflate(R.layout.fragment_today_task, container, false);
        todayTaskTitle = (TextView) rootView.findViewById(R.id.todayTaskTitle);
        todayTaskDescription=(TextView) rootView.findViewById(R.id.todayTaskDescription);


        Query query= reff.child("taskTable").orderByChild("startTitle").equalTo("Today");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                todayTaskTitle.setText(snapshot.child("head").toString());
                todayTaskDescription.setText(snapshot.child("des").toString());

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return rootView;

        //getBundle();
        // Inflate the layout for this fragment
    }
   /* private void getBundle(){
        bundle=new Bundle();
        bundle = getArguments();
        if(null!=bundle){
            if(bundle.containsKey("todayTaskTitle")&& bundle.containsKey("todayTaskDescription")){
                todayTaskTitle.setText(bundle.getString("todayTaskTitle"));
                todayTaskDescription.setText(bundle.getString("todayTaskDescription"));
            }
        }
    }



    */
}