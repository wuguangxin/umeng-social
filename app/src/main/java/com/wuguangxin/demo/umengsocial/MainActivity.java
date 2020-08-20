package com.wuguangxin.demo.umengsocial;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    int REQUEST_PERMISSIONS = 100;
    String[] permissions = {Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permissions[0]) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
                return;
            }
        }
        printDeviceInfo();
    }

    private void printDeviceInfo() {
        String testDeviceInfo = UMengManager.getTestDeviceInfo(this);
        System.out.println("printDeviceInfo testDeviceInfo = " + testDeviceInfo);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults[0] != PackageManager.PERMISSION_DENIED) {
                printDeviceInfo();
            } else {
                Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
