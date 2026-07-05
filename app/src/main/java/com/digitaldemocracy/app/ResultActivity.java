package com.digitaldemocracy.app;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private ListView listViewResults;
    private ResultAdapter resultAdapter;
    private List<Candidate> candidateList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        listViewResults = findViewById(R.id.listViewResults);
        candidateList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("candidates");

        // Fetch election results from Firebase
        databaseReference.orderByChild("votes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                candidateList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Candidate candidate = snapshot.getValue(Candidate.class);
                    candidateList.add(candidate);
                }
                // Sort candidates by highest votes (descending order)
                candidateList.sort((c1, c2) -> Integer.compare(c2.getVotes(), c1.getVotes()));

                resultAdapter = new ResultAdapter(ResultActivity.this, candidateList);
                listViewResults.setAdapter(resultAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
