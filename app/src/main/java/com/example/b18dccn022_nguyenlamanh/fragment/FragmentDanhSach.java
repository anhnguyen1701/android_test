package com.example.b18dccn022_nguyenlamanh.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b18dccn022_nguyenlamanh.R;
import com.example.b18dccn022_nguyenlamanh.adapter.RecycleViewAdapter;
import com.example.b18dccn022_nguyenlamanh.dal.SQLiteHelper;
import com.example.b18dccn022_nguyenlamanh.model.Item;

import java.util.List;

public class FragmentDanhSach extends Fragment implements RecycleViewAdapter.ItemListener {
    //declare
    private RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    private SQLiteHelper db;

    // inflate xml layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_danhsach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //declare
        recyclerView = view.findViewById(R.id.recycleView);
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());

        //show list in layout
        List<Item> list = db.getAll();
        adapter.setList(list);
        Log.d("list", String.valueOf(list));

        //make recycleviewAdapter run
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        //add listener
        adapter.setItemListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Item> list = db.getAll();
        adapter.setList(list);
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
