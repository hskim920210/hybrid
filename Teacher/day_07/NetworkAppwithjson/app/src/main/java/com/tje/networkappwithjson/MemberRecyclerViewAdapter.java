package com.tje.networkappwithjson;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MemberRecyclerViewAdapter extends RecyclerView.Adapter<MemberRecyclerViewAdapter.ViewHolder> {

    ArrayList<Member> list = new ArrayList<>();
    View.OnClickListener listener;

    public MemberRecyclerViewAdapter() {
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        public TextView name;
        public TextView age;
        public TextView phone;
        public ImageView image;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tv_recycle_name);
            age = view.findViewById(R.id.tv_recycle_age);
            phone = view.findViewById(R.id.tv_recycle_phone);
            image = view.findViewById(R.id.iv_recycle_image);
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
        holder.phone.setText(member.getTel());


        String imageUrl =
                "http://192.168.0.48:8080/android/resources/img/" + member.getImageUri();
        Glide.with(holder.itemView).load(imageUrl).into(holder.image);

        holder.itemView.setOnClickListener(this.listener);
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    public void loadItems(ArrayList<Member> list) {
        this.list = list;
    }

    public Member getItem(int position) {
        return list.get(position);
    }
}










