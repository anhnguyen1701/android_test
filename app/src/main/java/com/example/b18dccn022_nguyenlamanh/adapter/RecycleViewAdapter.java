package com.example.b18dccn022_nguyenlamanh.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b18dccn022_nguyenlamanh.R;
import com.example.b18dccn022_nguyenlamanh.model.Item;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private ItemListener itemListener;
    private List<Item> list;

    public RecycleViewAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = list.get(position);
        holder.ten.setText(item.getTen());
        holder.tinhtrang.setText(item.getTinhtrang());
        holder.date.setText(item.getDate());
        holder.congtac.setText(item.getCongtac());
        holder.noidung.setText(item.getNoidung());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView ten, tinhtrang, date, congtac, noidung;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tvTen);
            tinhtrang = itemView.findViewById(R.id.tvTinhTrang);
            date = itemView.findViewById(R.id.tvDate);
            congtac = itemView.findViewById(R.id.tvCongtac);
            noidung = itemView.findViewById(R.id.tvNoidung);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemListener != null) {
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }


    //customize method

    public interface ItemListener {
        void onItemClick(View view, int position);
    }


    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public Item getItem(int position) {
        return list.get(position);
    }
}
