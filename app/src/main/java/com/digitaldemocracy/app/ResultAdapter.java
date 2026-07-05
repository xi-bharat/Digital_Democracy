package com.digitaldemocracy.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ResultAdapter extends BaseAdapter {
    private Context context;
    private List<Candidate> candidateList;

    public ResultAdapter(Context context, List<Candidate> candidateList) {
        this.context = context;
        this.candidateList = candidateList;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_result, parent, false);
        }

        TextView tvCandidateName = convertView.findViewById(R.id.tvCandidateName);
        TextView tvVotes = convertView.findViewById(R.id.tvVotes);

        Candidate candidate = candidateList.get(position);
        tvCandidateName.setText(candidate.getName());
        tvVotes.setText("Votes: " + candidate.getVotes());

        return convertView;
    }
}

