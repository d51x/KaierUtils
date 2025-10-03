package ru.d51x.kaierutils;

import static java.lang.Math.abs;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import ru.d51x.kaierutils.OBD2.OBDII;

public class FloatingWindow implements View.OnClickListener, View.OnTouchListener {

    private WindowManager windowManager;
    private final Context context;
    private final View floatingView;
    private final WindowManager.LayoutParams layoutParams;

    private boolean isShowing = false;
    private boolean touchConsumedByMove = false;
    private int lastX = 0;
    private int lastY = 0;
    private int firstX = 0;
    private int firstY = 0;
    public ImageButton ibHideFloatingPanel;
    private final TextView tvOBD_CoolantTemp;
    private BroadcastReceiver receiver;

    @SuppressLint("InflateParams")
    public FloatingWindow(Context context) {
        this.context = context;
        floatingView = LayoutInflater.from(this.context).inflate(R.layout.floating_panel, null);
        floatingView.setOnTouchListener(this);

        ibHideFloatingPanel = floatingView.findViewById(R.id.ibHideFloatingPanel);
        //ibHideFloatingPanel.setOnClickListener (this);

        tvOBD_CoolantTemp = floatingView.findViewById(R.id.tvOBD_CoolantTemp);

        layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
    }

    public void show() {
        if (Settings.canDrawOverlays(context)) {
            // dismiss();
            isShowing = true;
            getWindowManager().addView(floatingView, layoutParams);

            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (action.equals(OBDII.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED)) {
                        tvOBD_CoolantTemp.setText(String.format(context.getString(R.string.text_obd_coolant_temp_f), intent.getStringExtra("coolantTemp")));
                    }
                }
            };
            context.registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED));
        }
    }

    public void dismiss() {
        if (isShowing) {
            getWindowManager().removeView(floatingView);
            isShowing = false;
            context.unregisterReceiver(receiver);
            App.GS.isShowingFloatingPanel = false;
        }
    }

    public boolean isShowing() {
        return isShowing;
    }
    public WindowManager getWindowManager() {
        if (windowManager == null) {
            windowManager = (WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibHideFloatingPanel:
                dismiss();
                break;
            default:
                break;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int totalDeltaX = lastX - firstX;
        int totalDeltaY = lastY - firstY;
        if (event == null) return false;

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                firstX = lastX;
                firstY = lastY;
                break;
            case MotionEvent.ACTION_UP:
                v.performClick();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) event.getRawX() - lastX;
                int deltaY = (int) event.getRawY() - lastY;
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                if (abs(totalDeltaX) >= 5 || abs(totalDeltaY) >= 5) {
                    if (event.getPointerCount() == 1) {
                        layoutParams.x += deltaX;
                        layoutParams.y += deltaY;
                        touchConsumedByMove = true;
                        getWindowManager().updateViewLayout(floatingView, layoutParams);
                    } else {
                        touchConsumedByMove = false;
                    }
                } else {
                    touchConsumedByMove = false;
                }
                break;
            default:
                break;
        }
        return touchConsumedByMove;
    }
}
