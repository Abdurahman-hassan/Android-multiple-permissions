package io.greencoder.practice2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class multipale_permissions extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_PERMISSION_CODE = 23;
    String[] PERMISSIONS;
    Button askPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multibale_permissions);

        askPermission = findViewById(R.id.askPermission_btn);
        askPermission.setOnClickListener(this);

        PERMISSIONS = new String[]{
                Manifest.permission.CALL_PHONE,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
    }

    private boolean hasPermissions(Context context, String[] PERMISSIONS) {

        if (context != null && PERMISSIONS != null) {

            for (String permission : PERMISSIONS) {

                if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            
            case REQUEST_PERMISSION_CODE: {

                for (int i = 0; i < grantResults.length; ++i) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, permissions[i].substring(19) + " is granted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, permissions[i].substring(19) + " is denied", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.askPermission_btn:{

                if (!hasPermissions(multipale_permissions.this, PERMISSIONS))
                    ActivityCompat.requestPermissions(multipale_permissions.this, PERMISSIONS, REQUEST_PERMISSION_CODE);

                break;
            }
        }
    }
}