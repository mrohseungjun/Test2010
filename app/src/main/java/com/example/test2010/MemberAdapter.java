package com.example.test2010;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MyViewHolder> {
    private List<Member> memberList;

    public MemberAdapter(List<Member> memberList){this.memberList = memberList;}

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvname;
        private TextView tvage;
        private TextView tvtel;
        private TextView tvaddr;
        private TextView tvemail;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.name);
            tvage = itemView.findViewById(R.id.age);
            tvtel = itemView.findViewById(R.id.tel);
            tvaddr = itemView.findViewById(R.id.addr);
            tvemail = itemView.findViewById(R.id.email);
        }
    }
    //insert
    public void addItem(Member member){
        memberList.add(member);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.member_list,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    Member member = memberList.get(position);
    holder.tvname.setText(member.getName());
    holder.tvage.setText(member.getAge());
    holder.tvtel.setText(member.getTel());
    holder.tvaddr.setText(member.getAddr());
    holder.tvemail.setText(member.getEamil());
    }

    @Override
    public int getItemCount() {
        return memberList == null ? 0 :memberList.size();
    }

}
