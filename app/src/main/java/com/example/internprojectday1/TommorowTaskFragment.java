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


public class TommorowTaskFragment extends Fragment {


    DatabaseReference reff;
    Bundle bundle;
    TextView tommorowTaskTitle, tommorowTaskDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        reff = FirebaseDatabase.getInstance().getReference();


        final View rootView = inflater.inflate(R.layout.fragment_tommorow_task, container, false);
        tommorowTaskTitle = (TextView) rootView.findViewById(R.id.tommorowTaskTitle);
        tommorowTaskDescription = (TextView) rootView.findViewById(R.id.tommorowTaskDescription);


        Query query = reff.child("taskTable").orderByChild("startTitle").equalTo("Tomorrow");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                tommorowTaskTitle.setText(snapshot.child("head").toString());
                tommorowTaskDescription.setText(snapshot.child("des").toString());

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return rootView;
    }

}
