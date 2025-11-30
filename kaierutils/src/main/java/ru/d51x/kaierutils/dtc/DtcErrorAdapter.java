package ru.d51x.kaierutils.dtc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.d51x.kaierutils.R;

public class DtcErrorAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Object> items = new ArrayList<>();

    private static final int ITEM_TYPE_SECTION_HEADER = 0;
    private static final int ITEM_TYPE_SECTION_ITEM = 1;

    public static class SectionHeaderViewHolder extends RecyclerView.ViewHolder {
        private final  TextView tvDtcHeaderTitle;
        private final  TextView tvDtcHeaderCount;
        public SectionHeaderViewHolder(@NonNull View v) {
            super(v);
            tvDtcHeaderTitle = v.findViewById(R.id.tvDtcHeaderTitle);
            tvDtcHeaderCount = v.findViewById(R.id.tvDtcHeaderCount);
        }

        public void bind(DtcHeader item) {
            // display your object
            if (item != null) {
                tvDtcHeaderTitle.setText(item.getTitle());
                tvDtcHeaderCount.setText(String.format("%d error(s)", item.getCount()));
            }

        }
    }

    public static class SectionItemViewHolder extends RecyclerView.ViewHolder {
        private final  TextView tvDtcCode;
        private final  TextView tvDtcState;
        private final  TextView tvDtcDescription;
        public SectionItemViewHolder(@NonNull View v) {
            super(v);
            tvDtcCode = v.findViewById(R.id.tvDtcCode);
            tvDtcState = v.findViewById(R.id.tvDtcState);
            tvDtcDescription = v.findViewById(R.id.tvDtcDescription);
        }

        public void bind(DtcError item) {
            // display your object
            if (item != null) {
                tvDtcCode.setText(item.getCode());
                tvDtcDescription.setText(item.getDescription());
                tvDtcState.setText(item.isActive() ? "Active" : "Stored");
            }
        }
    }

    public DtcErrorAdapter(Context context, List<Object> items) {
        this.mContext = context;
        this.items.addAll(items);
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof DtcError) {
            return ITEM_TYPE_SECTION_ITEM;
        } else {
            return ITEM_TYPE_SECTION_HEADER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        //View view = LayoutInflater.from(viewGroup.getContext());


        if (viewType == ITEM_TYPE_SECTION_ITEM) {
            View view =  layoutInflater.inflate(R.layout.list_item_dtc_error, viewGroup, false);
            return new SectionItemViewHolder(view);
        } else {
            View view =  layoutInflater.inflate(R.layout.list_item_dtc_header, viewGroup, false);
            return new SectionHeaderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Object item = items.get(position);
        if (viewHolder instanceof SectionItemViewHolder) {
            ((SectionItemViewHolder) viewHolder).bind((DtcError) item);
        } else {
            ((SectionHeaderViewHolder) viewHolder).bind((DtcHeader) item);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
