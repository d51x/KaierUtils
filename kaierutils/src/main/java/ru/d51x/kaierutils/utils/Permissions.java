package ru.d51x.kaierutils.utils;

import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.app.ActivityCompat.startActivityForResult;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Permissions {
    private static final int REQUEST_CODE_DRAW_OVERLAY_PERMISSION = 5;
    private static final int REQUEST_CODE_PERMISSION = 6;

    private final Fragment context;
    public Permissions(Fragment context) {
        this.context = context;
    }
    public void requestPermissions2() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return;

        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                //Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.BLUETOOTH_SCAN
        };

        List<String> permissionsToRequest = new ArrayList<>();

        for (String permission : permissions) {
            assert context.getActivity() != null;
            if (checkSelfPermission(context.getActivity(), permission) != PermissionChecker.PERMISSION_GRANTED) {
                Log.e("Permissions", String.format("Permission %s is not granted", permission));
                permissionsToRequest.add(permission);
            }
        }

        if (!permissionsToRequest.isEmpty()) {
            requestPermissions(
                    context.getActivity(),
                    permissionsToRequest.toArray(new String[0]), // Convert list to array
                    REQUEST_CODE_PERMISSION // Pass the request code
            );
        } else {
            Log.i("Permissions", "All permissions already granted");
        }

        if (!Settings.canDrawOverlays(context.getActivity())) {
            startManageDrawOverlaysPermission();
        }
    }

    private void startManageDrawOverlaysPermission() {
        assert context.getActivity() != null;
        Intent intent = new Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                //Uri.parse("package:${applicationContext.packageName}")
                Uri.parse("package:" + context.getActivity().getPackageName())
        );
        startActivityForResult(context.getActivity(), intent, REQUEST_CODE_DRAW_OVERLAY_PERMISSION, null);
    }
}
