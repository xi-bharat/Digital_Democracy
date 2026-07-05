package com.digitaldemocracy.app;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CandidatesActivity extends AppCompatActivity {
    private ListView listView;
    private CandidateAdapter adapter;
    private List<Candidate> candidateList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidates);

        listView = findViewById(R.id.listCandidates);
        candidateList = getCandidates();

        adapter = new CandidateAdapter(this, candidateList, candidate -> {
            castVote(candidate);
        });

        listView.setAdapter(adapter);
    }

    private List<Candidate> getCandidates() {
        List<Candidate> list = new ArrayList<>();
        list.add(new Candidate("Shri Narendra Modi", "BJP", 78956678));
        list.add(new Candidate("Shri Rahul Gandhi", "INC", 56971));
        return list;
    }

    private void castVote(Candidate candidate) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("candidates").child(candidate.getName());
        dbRef.child("votes").setValue(candidate.getVotes() + 1);
        Toast.makeText(this, "Vote cast for " + candidate.getName(), Toast.LENGTH_SHORT).show();
    }
}
