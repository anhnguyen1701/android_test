package com.example.b18dccn022_nguyenlamanh.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.b18dccn022_nguyenlamanh.fragment.FragmentDanhSach;
import com.example.b18dccn022_nguyenlamanh.fragment.FragmentSearch;
import com.example.b18dccn022_nguyenlamanh.fragment.FragmentThongTin;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentDanhSach();
            case 1:
                return new FragmentThongTin();
            case 2:
                return new FragmentSearch();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
