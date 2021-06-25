package com.example.internprojectday1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;


public class YesterdayTaskFragment extends Fragment {

    DatabaseReference reff;
    TextView yesterdayTaskTitle,yesterdayTaskDescription;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        reff = FirebaseDatabase.getInstance().getReference();


        final View rootView = inflater.inflate(R.layout.fragment_yesterday_task, container, false);
        yesterdayTaskTitle = (TextView) rootView.findViewById(R.id.yesterdayTaskTitle);
        yesterdayTaskDescription = (TextView) rootView.findViewById(R.id.yesterdayTaskDescription);


        Query query = reff.child("taskTable").orderByChild("startTitle").equalTo("Yesterday");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                yesterdayTaskTitle.setText(snapshot.child("head").toString());
                yesterdayTaskDescription.setText(snapshot.child("des").toString());

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return rootView;
    }

}