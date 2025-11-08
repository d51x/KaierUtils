package ru.d51x.kaierutils.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.List;

import ru.d51x.kaierutils.R;

public class FileSelectorDialog extends Dialog {
    public static final String TAG = "FileSelector";

    @SuppressLint("MissingInflatedId")
    public FileSelectorDialog(@NonNull Context context, List<File> files, OnFileSelectListener listener) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_files);

        ListView lv = findViewById(R.id.lvFilesList);
        ArrayAdapter<File> adapter = new ArrayAdapter<>(context, R.layout.list_item_file, files) {
            @SuppressLint("InflateParams")
            @NonNull
            @Override
            public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
                View v = view;
                if (v == null) {
                    LayoutInflater vi;
                    vi = LayoutInflater.from(context);
                    v = vi.inflate(R.layout.list_item_file, null);
                }
                if (v != null) {
                    TextView tvItemFile = v.findViewById(R.id.tvFileName);

                    tvItemFile.setText(files.get(i).getName());
                }
                assert v != null;
                return v;
            }
        };
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            listener.onSelect(files.get(i));
            dismiss();
        });
    }


}
