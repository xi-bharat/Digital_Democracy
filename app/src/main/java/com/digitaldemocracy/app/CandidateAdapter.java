package com.digitaldemocracy.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CandidateAdapter extends BaseAdapter {
    private Context context;
    private List<Candidate> candidateList;
    private OnVoteClickListener voteClickListener;

    public CandidateAdapter(Context context, List<Candidate> candidateList, OnVoteClickListener voteClickListener) {
        this.context = context;
        this.candidateList = candidateList;
        this.voteClickListener = voteClickListener;
    }

    @Override
    public int getCount() {
        return candidateList.size();
    }

    @Override
    public Object getItem(int position) {
        return candidateList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_candidate, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvParty = convertView.findViewById(R.id.tvParty);
        Button btnVote = convertView.findViewById(R.id.btnVote);

        Candidate candidate = candidateList.get(position);
        tvName.setText(candidate.getName());
        tvParty.setText(candidate.getParty());

        btnVote.setOnClickListener(view -> {
            if (voteClickListener != null) {
                voteClickListener.onVoteClick(candidate);
            }
        });

        return convertView;
    }

    public interface OnVoteClickListener {
        void onVoteClick(Candidate candidate);
    }
}