package ru.d51x.kaierutils.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RadioListDialog {
    private String title;
    private String description;
    private AlertDialog.Builder dialog;
    private Context context;
    private RadioListDialogListener listener;
    public RadioListDialog(Context context, String title, String description) {
        this.title = title;
        this.description = description;
        this.context = context;
        this.dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        //dialog.setMessage(description);
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (listener != null)
                    listener.onDialogResult(-1);
                dialogInterface.dismiss();
            }
        });
        dialog.setPositiveButton("Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ListView lv = ((AlertDialog) dialogInterface).getListView();
                Integer selected = (Integer) lv.getTag();
                //Log.d("DLG", "Selected = " + selected);
                if (listener != null)
                    listener.onDialogResult(selected);
            }
        });
    }

    public void setValuesList(CharSequence[] values) {
        dialog.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int item) {
                //Log.d("DLG", "Selected = " + item);
                ListView lv = ((AlertDialog)dialogInterface).getListView();
                lv.setTag(new Integer(item));
                //dialogInterface.dismiss();
            }
        });
    }

    public void show() {
        dialog.create();
        dialog.show();
    }

    public interface RadioListDialogListener{
        public void onDialogResult(int selected);
    }


    public void setListener(RadioListDialogListener listener) {
        this.listener = listener;
    }
}
