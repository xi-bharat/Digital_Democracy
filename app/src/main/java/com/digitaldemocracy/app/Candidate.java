package com.digitaldemocracy.app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Candidate {
    private String name;
    private String party;
    private int votes;

    public Candidate() {
        // Default constructor required for Firebase
    }

    public Candidate(String name, String party, int votes) {
        this.name = name;
        this.party = party;
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public int getVotes() {
        return votes;
    }
}
