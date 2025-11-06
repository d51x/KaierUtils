package ru.d51x.kaierutils;

import static ru.d51x.kaierutils.Data.EtacsCustomCoding.getCurrentValue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.d51x.kaierutils.Data.EtacsCustomCoding;

public class CodingAdapter extends ArrayAdapter<EtacsCustomCoding> {

    private int resourceLayout;
    private Context mContext;
    private ArrayList<Integer> buffer;
    public CodingAdapter(Context context, ArrayList<Integer> buffer, int resource, List<EtacsCustomCoding> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
        this.buffer = buffer;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }
        Log.d("ETACS", "position = " + position);
        EtacsCustomCoding p = getItem(position);
        if (p != null) {
            TextView tt1 = v.findViewById(R.id.tvCodingTitle);
            TextView tt2 = v.findViewById(R.id.tvCodingSummary);
            TextView tt3 = v.findViewById(R.id.tvCodingValue);
            //EtacsCustomCoding etacsCustomCoding = EtacsCustomCoding.values()[position];
            if (tt1 != null) {
                tt1.setText(String.format("%02d. %s", p.getIdx(), p.getName()));
            }
            if (tt2 != null) {
                tt2.setText(p.getTitle());
            }
            if (tt3 != null) {
                if (position < EtacsCustomCoding.values().length) {
                    int byteValue = buffer.get(p.getByteIdx());
                    int maskValue = byteValue & p.getMask();
                    int shiftValue = maskValue >> p.getStartBit();
                    String sValue = getCurrentValue(p.getIdx(), shiftValue);
                    tt3.setText(sValue);
                    v.setTag(R.id.ITEM_OBJECT_TAG, (EtacsCustomCoding)p);
                    v.setTag(R.id.ITEM_BYTE_VALUE_TAG, byteValue);
                    v.setTag(R.id.ITEM_CURRENT_VALUE_TAG, sValue);
                }
            }
        }
        return v;
    }

    private int pow(int val, int exp) {
        int newVal = val+1;
        for (int i = 2; i <= exp; i++) {
            newVal = newVal * (val+1);
        }
        return newVal;
    }
}
