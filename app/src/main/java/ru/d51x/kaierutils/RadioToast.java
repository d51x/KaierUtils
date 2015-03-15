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
    private boolean isShowSecondLine = true;
    //public boolean isShowToastWhenActive = true;

	RadioToast(Context context) {
		this.context = context;
		mLayout = (RelativeLayout) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.radio_toast, null);
		tvRadioText1 = (TextView) this.mLayout.findViewById (R.id.tvRadioText1);
		tvRadioText2 = (TextView) this.mLayout.findViewById(R.id.tvRadioText2);
	}

	public void SetRadioText(String text1, String text2) {
		if ( (text1.equals( null )) || (text1.isEmpty () ) || (text1.equals("") ) || (text1.equals( "null"))) { // есть название
            isShowSecondLine = false;
            Text1 = String.format("%1$s MHz", text2);
            Text2 = "";
    	} else {
            isShowSecondLine = true;
			Text1 = text1;
            Text2 = String.format("%1$s MHz", text2);
		}
	}

	public void showToast() {
        if ( mToast != null) { mToast.cancel();	mToast = null; }
        if ( !App.GS.isShowRadioToast /*|| ( App.GS.isDontShowRadioToastWhenMainActivity && !isShowToastWhenActive )*/ ) return;

        tvRadioText1.setTextSize ( App.GS.radioToastLine1TextSize );
        tvRadioText2.setTextSize ( App.GS.radioToastLine2TextSize );
        if ( isShowSecondLine ) {
            tvRadioText1.setText ( Text1 );
            tvRadioText2.setText ( Text2 );
            tvRadioText2.setVisibility (View.VISIBLE);
        } else {
            tvRadioText1.setText ( Text2 );
            tvRadioText2.setText ( "" );
            tvRadioText2.setVisibility ( View.GONE);
        }
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
