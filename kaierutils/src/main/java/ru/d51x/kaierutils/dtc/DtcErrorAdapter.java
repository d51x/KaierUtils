package ru.d51x.kaierutils.dtc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.d51x.kaierutils.R;
import ru.d51x.kaierutils.coding.EtacsVariantCoding;

public class DtcErrorAdapter  extends ArrayAdapter<DtcError> {

    private int resourceLayout;
    private Context mContext;
    public DtcErrorAdapter(Context context, int resource, List<DtcError> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }
        DtcError p = getItem(position);
        if (p != null) {
            TextView tvDtcCode = v.findViewById(R.id.tvDtcCode);
            TextView tvDtcState = v.findViewById(R.id.tvDtcState);
            TextView tvDtcDescription = v.findViewById(R.id.tvDtcDescription);
            TextView tvDtcBlock = v.findViewById(R.id.tvDtcBlock);

            if (tvDtcCode != null) {
                tvDtcCode.setText(p.getCode());
            }
            if (tvDtcState != null) {
                tvDtcState.setText(p.isActive() ? "Active" : "Stored");
            }
            if (tvDtcDescription != null) {
                tvDtcDescription.setText(p.getDescription());
            }
            if (tvDtcBlock != null) {
                tvDtcBlock.setText(p.getBlock());
            }
        }
        return v;
    }
}
