package ru.d51x.kaierutils;

import android.view.View;
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
		mLayout = (RelativeLayout) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.radio_toast, null);
		tvRadioText1 = (TextView) this.mLayout.findViewById (R.id.tvRadioText1);
		tvRadioText2 = (TextView) this.mLayout.findViewById(R.id.tvRadioText2);
	}

	public void SetRadioText(String text1, String text2) {
		if ( (text1 == null) || (text1.isEmpty () ) || (text1 == "" ) || (text1 == "null")) { // есть название
			tvRadioText1.setText ( String.format("%1$s MHz", text2) );
			tvRadioText2.setText ( "" );
			tvRadioText2.setVisibility ( View.GONE);

		} else {
			tvRadioText1.setText ( text1 );
			tvRadioText2.setText ( String.format("%1$s MHz", text2) );
			tvRadioText2.setVisibility (View.VISIBLE);
		}
	}

	public void showToast() {

		tvRadioText1.setTextSize ( App.GS.radioToastLine1TextSize );
		tvRadioText2.setTextSize ( App.GS.radioToastLine2TextSize );

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
			mToast = null;
		}
	}

}
