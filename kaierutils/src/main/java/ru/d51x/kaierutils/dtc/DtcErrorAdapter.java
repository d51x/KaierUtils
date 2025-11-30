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
    private static final int ITEM_TYPE_SECTION_HISTORY_ITEM = 2;
    private static final int ITEM_TYPE_SECTION_HISTORY_HEADER= 3;

    public static class SectionHeaderViewHolder extends RecyclerView.ViewHolder {
        private final  TextView tvDtcHeaderTitle;
        private final  TextView tvDtcHeaderCount;
        private final  TextView tvDtcHeaderMileage;
        public SectionHeaderViewHolder(@NonNull View v) {
            super(v);
            tvDtcHeaderTitle = v.findViewById(R.id.tvDtcHeaderTitle);
            tvDtcHeaderCount = v.findViewById(R.id.tvDtcHeaderCount);
            tvDtcHeaderMileage = v.findViewById(R.id.tvDtcHeaderMileage);
        }

        public void bind(DtcHeader item) {
            // display your object
            if (item != null) {
                tvDtcHeaderTitle.setText(item.getTitle());
                tvDtcHeaderCount.setText(String.format("%d error(s)", item.getCount()));
                tvDtcHeaderMileage.setText(String.format("Last DTC at %d km", item.getMileage()));
                tvDtcHeaderMileage.setVisibility(item.getMileage() > 0 ? View.VISIBLE : View.INVISIBLE);
            }
        }
    }

    public static class SectionHistoryHeaderViewHolder extends RecyclerView.ViewHolder {
        private final  TextView tvDtcHeaderTitle;
        private final  TextView tvDtcHeaderCount;
        public SectionHistoryHeaderViewHolder(@NonNull View v) {
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

    public static class SectionItemHistoryViewHolder extends RecyclerView.ViewHolder {
        private final  TextView tvDtcCode;
        private final  TextView tvDtcMileage;
        private final  TextView tvDtcDescription;
        public SectionItemHistoryViewHolder(@NonNull View v) {
            super(v);
            tvDtcCode = v.findViewById(R.id.tvDtcCode);
            tvDtcMileage = v.findViewById(R.id.tvDtcMileage);
            tvDtcDescription = v.findViewById(R.id.tvDtcDescription);
        }

        public void bind(DtcHistoryError item) {
            // display your object
            if (item != null) {
                tvDtcCode.setText(item.getCode());
                tvDtcDescription.setText(item.getDescription());
                tvDtcMileage.setText(String.format("%d km", item.getAtMileage()));
            }
        }
    }

    public DtcErrorAdapter(Context context, List<Object> items) {
        this.mContext = context;
        this.items.addAll(items);
    }

    @Override
    public int getItemViewType(int position) {
        Object item = items.get(position);
        if (item instanceof DtcError) {
            return ITEM_TYPE_SECTION_ITEM;
        }
        else if (item instanceof DtcHeader) {
            return ITEM_TYPE_SECTION_HEADER;
        }
        else if (item instanceof DtcHistoryError) {
            return ITEM_TYPE_SECTION_HISTORY_ITEM;
        } else {
            return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        //View view = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case ITEM_TYPE_SECTION_ITEM -> {
                View view = layoutInflater.inflate(R.layout.list_item_dtc_error, viewGroup, false);
                return new SectionItemViewHolder(view);
            }
            case ITEM_TYPE_SECTION_HEADER -> {
                View view = layoutInflater.inflate(R.layout.list_item_dtc_header, viewGroup, false);
                return new SectionHeaderViewHolder(view);
            }
            case ITEM_TYPE_SECTION_HISTORY_ITEM -> {
                View view = layoutInflater.inflate(R.layout.list_item_dtc_history_error, viewGroup, false);
                return new SectionItemHistoryViewHolder(view);
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Object item = items.get(position);
        if (viewHolder instanceof SectionItemViewHolder) {
            ((SectionItemViewHolder) viewHolder).bind((DtcError) item);
        }
        else if (viewHolder instanceof SectionHeaderViewHolder) {
            ((SectionHeaderViewHolder) viewHolder).bind((DtcHeader) item);
        }
        else if (viewHolder instanceof SectionItemHistoryViewHolder){
            ((SectionItemHistoryViewHolder) viewHolder).bind((DtcHistoryError) item);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
