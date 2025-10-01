package ru.d51x.kaierutils;

import static java.lang.Math.abs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import java.util.Objects;

public class FloatingWindow implements View.OnClickListener, View.OnTouchListener {

    private WindowManager windowManager;
    private Context context;
    private View floatingView;
    private WindowManager.LayoutParams layoutParams;

    private ImageButton ibFloatingPanel;
    private boolean isShowing = false;
    private boolean touchConsumedByMove = false;
    private int lastX = 0;
    private int lastY = 0;
    private int firstX = 0;
    private int firstY = 0;

    @SuppressLint("InflateParams")
    public FloatingWindow(Context context) {
        this.context = context;
        floatingView = LayoutInflater.from(this.context).inflate(R.layout.floating_panel, null);
        floatingView.setOnTouchListener(this);

        ibFloatingPanel = (ImageButton) floatingView.findViewById(R.id.ibFloatingPanel);
        ibFloatingPanel.setOnClickListener (this);

        layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
    }

    public void show() {
        if (Settings.canDrawOverlays(context)) {
            dismiss();
            isShowing = true;
            getWindowManager().addView(floatingView, layoutParams);
        }
    }

    public void dismiss() {
        if (isShowing) {
            getWindowManager().removeView(floatingView);
            isShowing = false;
        }
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
            case R.id.ibFloatingPanel:
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
