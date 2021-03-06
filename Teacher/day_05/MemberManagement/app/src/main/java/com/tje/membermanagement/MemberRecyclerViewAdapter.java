package com.tje.membermanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MemberRecyclerViewAdapter extends RecyclerView.Adapter<MemberRecyclerViewAdapter.ViewHolder> {

    MemberDbHelper memberDbHelper;
    ArrayList<Member> list;

    public MemberRecyclerViewAdapter(MemberDbHelper memberDbHelper) {
        this.memberDbHelper = memberDbHelper;
        loadItems();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView name;
        public TextView age;
        public TextView phone;
        public TextView address;
        public TextView registDate;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_recycle_name);
            age = (TextView) view.findViewById(R.id.tv_recycle_age);
            phone = (TextView) view.findViewById(R.id.tv_recycle_phone);
            address = (TextView) view.findViewById(R.id.tv_recycle_address);
            registDate = (TextView) view.findViewById(R.id.tv_recycle_regist_date);
        }
    }

    @NonNull
    @Override
    public MemberRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_member, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(
            @NonNull MemberRecyclerViewAdapter.ViewHolder holder, int position) {
        Member member = this.list.get(position);
        holder.name.setText(member.getName());
        holder.age.setText(member.getAge() + "");
        holder.phone.setText(member.getPhone());
        holder.address.setText(member.getAddress());
        holder.registDate.setText(member.getRegistDate());
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public void loadItems() {
        this.list = memberDbHelper.select();
    }
}










