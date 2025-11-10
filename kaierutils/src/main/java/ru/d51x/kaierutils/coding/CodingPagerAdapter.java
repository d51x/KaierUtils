package ru.d51x.kaierutils.coding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class CodingPagerAdapter extends FragmentStateAdapter {
    private final List<String> titles;
    public CodingPagerAdapter(@NonNull FragmentActivity activity, List<String> titles) {
        super(activity);
        this.titles = titles;
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                    EtacsCustomFragment fragment = new EtacsCustomFragment();
                    Bundle args = new Bundle();
                    args.putString("title", titles.get(position));
                    fragment.setArguments(args);
                    return fragment;
                }
            case 1: {
                    EtacsVariantFragment fragment = new EtacsVariantFragment();
                    Bundle args = new Bundle();
                    args.putString("title", titles.get(position));
                    fragment.setArguments(args);
                    return fragment;
                }
            case 2: {
                    EngineCodingFragment fragment = new EngineCodingFragment();
                    Bundle args = new Bundle();
                    args.putString("title", titles.get(position));
                    fragment.setArguments(args);
                    return fragment;
                }
            default:
                return null;
        }
    }
}
