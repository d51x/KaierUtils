package ru.d51x.kaierutils.etacs;

import static ru.d51x.kaierutils.etacs.EtacsCustomCoding.getCurrentValue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.d51x.kaierutils.R;

public class CustomCodingAdapter extends ArrayAdapter<EtacsCustomCoding> {

    private int resourceLayout;
    private Context mContext;
    private ArrayList<Integer> buffer;
    private ArrayList<Integer> prevBuffer;
    public CustomCodingAdapter(Context context, ArrayList<Integer> buffer, ArrayList<Integer> prevBuffer,
                               int resource, List<EtacsCustomCoding> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
        this.buffer = buffer;
        this.prevBuffer = prevBuffer;
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
            TextView tvCodingTitle = v.findViewById(R.id.tvCodingTitle);
            TextView tvCodingSummary = v.findViewById(R.id.tvCodingSummary);
            TextView tvCodingValue = v.findViewById(R.id.tvCodingValue);
            TextView tvIdx = v.findViewById(R.id.tvIdx);
            TextView tvOldValue = v.findViewById(R.id.tvOldValue);

            //EtacsCustomCoding etacsCustomCoding = EtacsCustomCoding.values()[position];
            if (tvIdx != null) {
                tvIdx.setText(String.format("%03d", p.getIdx()));
            }
            if (tvCodingTitle != null) {
                tvCodingTitle.setText(p.getTitle());
            }
            if (tvCodingSummary != null) {
                tvCodingSummary.setText(p.getSummary());
            }
            if (tvCodingValue != null && tvOldValue != null) {
                if (position < EtacsCustomCoding.values().length) {
                    int byteValue = buffer.get(p.getByteIdx());
                    int maskValue = byteValue & p.getMask();
                    int shiftValue = maskValue >> p.getStartBit();

                    String sValue = getCurrentValue(p.getIdx(), shiftValue);
                    tvCodingValue.setText(sValue);
                    tvCodingValue.setTextColor(getContext().getColor(R.color.gray_color));

                    v.setTag(R.id.ITEM_OBJECT_TAG, (EtacsCustomCoding)p);
                    v.setTag(R.id.ITEM_BYTE_VALUE_TAG, byteValue);
                    v.setTag(R.id.ITEM_CURRENT_VALUE_TAG, sValue);

                    int prevByteValue = prevBuffer.get(p.getByteIdx());
                    maskValue = prevByteValue & p.getMask();
                    shiftValue = maskValue >> p.getStartBit();
                    String sPrevValue = getCurrentValue(p.getIdx(), shiftValue);

                    if (sValue.equals(sPrevValue)) {
                        tvOldValue.setText("");
                    } else {
                        tvOldValue.setText(sPrevValue);
                        tvCodingValue.setTextColor(getContext().getColor(R.color.temp_orange));
                    }
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
