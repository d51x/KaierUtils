package ru.d51x.kaierutils;

import android.widget.Toast;
import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.LayoutInflater;

/**
 * Created by Dmitriy on 07.03.2015.
 */
public class RadioToast {
	private Context context;
	private Toast mToast;
	private RelativeLayout mLayout;
	private TextView tvRadioText1;
	private TextView tvRadioText2;
	private String Text1;
	private String Text2;

	RadioToast(Context context) {
		this.context = context;
		this.mLayout = (RelativeLayout) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.radio_toast, null);
		this.tvRadioText1 = (TextView) this.mLayout.findViewById (R.id.tvRadioText1);
		this.tvRadioText2 = (TextView) this.mLayout.findViewById(R.id.tvRadioText2);
	}

	public void SetRadioText(String text1, String text2) {
		if ( text1 != null ) { // есть название
			tvRadioText1.setText ( text1 );
			tvRadioText2.setText ( String.format("%1$s MHz", text2) );
		} else {
			tvRadioText1.setText ( String.format("%1$s MHz", text2) );
			tvRadioText2.setText ( "" );
		}
	}

	public void showToast() {


		if ( mToast != null) { mToast.cancel();	}
		mToast = new Toast(context);
		mToast.setGravity(55, 0, 0);
		mToast.setDuration(Toast.LENGTH_LONG);
		mToast.setView(this.mLayout);
		mToast.show();
	}

	public void cancel() {
		if ( mToast != null) {
			mToast.cancel();
			mToast.
			mToast = null;
		}
	}

}
