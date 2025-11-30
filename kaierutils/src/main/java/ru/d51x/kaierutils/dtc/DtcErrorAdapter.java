package ru.d51x.kaierutils.dtc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ru.d51x.kaierutils.R;
import ru.d51x.kaierutils.coding.EtacsVariantCoding;

public class DtcErrorAdapter  extends RecyclerView.Adapter<DtcErrorAdapter.ViewHolder> {

    private int resourceLayout;
    private Context mContext;
    private List<DtcError> dtcErrors;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final  TextView tvDtcCode;
        private final  TextView tvDtcState;
        private final  TextView tvDtcDescription;
        private final  TextView tvDtcBlock;

        public ViewHolder(View v) {
            super(v);
            tvDtcCode = v.findViewById(R.id.tvDtcCode);
            tvDtcState = v.findViewById(R.id.tvDtcState);
            tvDtcDescription = v.findViewById(R.id.tvDtcDescription);
            tvDtcBlock = v.findViewById(R.id.tvDtcBlock);
        }

        public TextView getTvDtcCode() {
            return tvDtcCode;
        }

        public TextView getTvDtcState() {
            return tvDtcState;
        }

        public TextView getTvDtcDescription() {
            return tvDtcDescription;
        }

        public TextView getTvDtcBlock() {
            return tvDtcBlock;
        }
    }

    public DtcErrorAdapter(Context context, int resource, List<DtcError> items) {
        this.resourceLayout = resource;
        this.mContext = context;
        this.dtcErrors = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(resourceLayout, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        DtcError dtcError = dtcErrors.get(position);
        if (dtcError != null) {
            viewHolder.getTvDtcCode().setText(dtcError.getCode());
            viewHolder.getTvDtcDescription().setText(dtcError.getDescription());
            viewHolder.getTvDtcBlock().setText(dtcError.getBlock());
            viewHolder.getTvDtcState().setText(dtcError.isActive() ? "Active" : "Stored");
        }
    }

    @Override
    public int getItemCount() {
        return dtcErrors.size();
    }
}
