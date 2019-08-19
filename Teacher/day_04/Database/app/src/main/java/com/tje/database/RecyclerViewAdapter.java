package com.tje.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private MemoListDbHelper memoListDbHelper;
    private ArrayList<Memo> memoArrayList;

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView title;
        public TextView date;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public RecyclerViewAdapter(Context context) {
        memoListDbHelper = new MemoListDbHelper(context);
        memoArrayList = memoListDbHelper.loadMemoList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Memo memo = memoArrayList.get(position);

        holder.title.setText(memo.getFileName());
        holder.date.setText(memo.getDate());
    }

    @Override
    public int getItemCount() {
        if (memoArrayList == null) {
            return 0;
        }
        return memoArrayList.size();
    }

}

/*
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    String [] notes = {"노트1","노트2","노트3","노트4","노트5","노트6","노트7","노트8","노트9","노트10"};
    String [] dates = {"17.01.01","17.02.01","17.03.01","17.04.01","17.05.01","17.06.01","17.07.01","17.08.01","17.09.01","17.10.01"};

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView date;

        public ViewHolder(View view) {
            super(view);
            this.title = view.findViewById(R.id.title);
            this.date = view.findViewById(R.id.date);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_note, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg =
                        String.format("%s 가 클릭되었습니다.",
                                ((TextView)view.findViewById(R.id.title)).getText().toString());

                Toast.makeText(view.getContext(), msg,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.title.setText(notes[position]);
        holder.date.setText(dates[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg =
                        String.format("%s (%s) 가 클릭되었습니다.",
                                notes[position], dates[position]);
                Toast.makeText(view.getContext(), msg,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.length;
    }
}
*/
