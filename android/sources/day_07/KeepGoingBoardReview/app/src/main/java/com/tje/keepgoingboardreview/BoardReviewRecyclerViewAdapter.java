package com.tje.keepgoingboardreview;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class BoardReviewRecyclerViewAdapter extends RecyclerView.Adapter<BoardReviewRecyclerViewAdapter.ViewHolder> {








    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView content;
        public TextView nickname;
        public TextView image;
        public TextView write_date;
    }

}


