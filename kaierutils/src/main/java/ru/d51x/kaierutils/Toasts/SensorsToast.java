package ru.d51x.kaierutils.Toasts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import ru.d51x.kaierutils.R;

/**
 * Created by Dmitriy on 07.03.2015.
 */
public class SensorsToast {
	private final Context context;
	private Toast mToast;
	private final RelativeLayout mLayout;

    private final ImageView ivSensorRearLeftOuter;
    private final ImageView ivSensorRearLeftInner;
    private final ImageView ivSensorRearRightOuter;
    private final ImageView ivSensorRearRightInner;
	//private TextView tvRadioText1;
	//private TextView tvRadioText2;
	//private String Text1;
	//private String Text2;

	@SuppressLint("InflateParams")
    public SensorsToast(Context context) {
		this.context = context;
		mLayout = (RelativeLayout) ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.sensors_toast, null);
		//tvRadioText1 = (TextView) mLayout.findViewById(R.id.tvRadioText1);
		//tvRadioText2 = (TextView) mLayout.findViewById(R.id.tvRadioText2);


        ivSensorRearLeftOuter = (ImageView) mLayout.findViewById(R.id.iv_sensor_rear_left_outer);
        ivSensorRearLeftInner = (ImageView) mLayout.findViewById(R.id.iv_sensor_rear_left_inner);
        ivSensorRearRightOuter = (ImageView) mLayout.findViewById(R.id.iv_sensor_rear_right_outer);
        ivSensorRearRightInner = (ImageView) mLayout.findViewById(R.id.iv_sensor_rear_right_inner);

	}

	public void SetSensors(ArrayList<Integer> buffer) {

        //int front_outer_left = buffer.get(2) >> 4;
       // int front_outer_right = buffer.get(2);

        //int front_inner_left = buffer.get(3) >> 4;
        //int front_inner_right = buffer.get(3);

        int rear_outer_left = buffer.get(4) >> 4;
        int rear_outer_right = buffer.get(4) & 0xF;

        int rear_inner_left = buffer.get(6) >> 4;
        int rear_inner_right = buffer.get(6) & 0xF;

        set_sensor(ivSensorRearLeftInner, rear_inner_left );
        set_sensor(ivSensorRearLeftOuter, rear_outer_left );
        set_sensor(ivSensorRearRightOuter, rear_outer_right );
        set_sensor(ivSensorRearRightInner, rear_inner_right );

//        Log.d("SENSORS: ", String.format("LO: %1$d     LI: %2$d     RI: %3$d     RO: %4$d",
//                rear_outer_left, rear_inner_left, rear_inner_right, rear_outer_right));
    }


    private void set_sensor(ImageView iv, int value){
        switch ( value ) {
            case 0: iv.setImageResource(R.drawable.sensor_0); break;
            case 1: iv.setImageResource(R.drawable.sensor_1); break;
            case 2: iv.setImageResource(R.drawable.sensor_2); break;
            case 3: iv.setImageResource(R.drawable.sensor_3); break;
            case 4: iv.setImageResource(R.drawable.sensor_4); break;
            case 5: iv.setImageResource(R.drawable.sensor_5); break;
            case 6: iv.setImageResource(R.drawable.sensor_6); break;
            default: iv.setImageResource(R.drawable.sensor_0); break;
        }
    }



	public void showToast() {

		mToast = new Toast(context);
		mToast.setGravity(55, 0, 0);
		mToast.setDuration(Toast.LENGTH_LONG);
		mToast.setView(mLayout);
		mToast.show();
	}

	public void cancel() {
		if ( mToast != null) {
			mToast.cancel();
			mToast = null;
		}
	}

}
