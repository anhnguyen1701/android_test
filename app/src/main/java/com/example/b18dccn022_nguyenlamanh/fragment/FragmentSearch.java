package com.example.b18dccn022_nguyenlamanh.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

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

public class FragmentSearch extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Button btSearch;
    private SearchView searchView;
    private TextView tvTong;
    private Spinner spCategory;
    private RecycleViewAdapter adapter;
    private SQLiteHelper db;


    // inflate xml layout
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        //declare
        adapter = new RecycleViewAdapter();
        db = new SQLiteHelper(getContext());

        //show list in layout
        List<Item> list = db.getAll();
        adapter.setList(list);
        tvTong.setText("Tổng" + tong(list) + "việc");


        //make recycleviewAdapter run
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        //emit event
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Item> list = db.searchByTitle(s);
                adapter.setList(list);
                tvTong.setText("Tổng" + tong(list) + "việc");
                return true;
            }
        });

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String cate = spCategory.getItemAtPosition(i).toString();
                List<Item> list;

                if (!cate.equalsIgnoreCase("all")) {
                    list = db.searchByCategory(cate);
                } else {
                    list = db.getAll();
                }
                adapter.setList(list);
                tvTong.setText("Tổng" + tong(list) + "việc");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btSearch.setOnClickListener(this);

    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recycleView);
        btSearch = view.findViewById(R.id.btSearch);
        tvTong = view.findViewById(R.id.tvTong);
        searchView = view.findViewById(R.id.search);
        spCategory = view.findViewById(R.id.spCategory);

        String[] arr = getResources().getStringArray(R.array.category);
        String[] arr1 = new String[arr.length + 1];

        arr1[0] = "All";
        for (int i = 0; i < arr.length; i++) {
            arr1[i + 1] = arr[i];
        }

        spCategory.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.item_spinner, arr1));
    }

    @Override
    public void onClick(View view) {

    }

    private int tong(List<Item> list) {
//        int t = 0;
//        for (Item i : list) {
//            t += Integer.parseInt(i.getPrice());
//        }
//
//        return t;
        return list.size();
    }
}
