package com.example.reminisce_ticketing.ui.scan_with_camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.reminisce_ticketing.R;
import com.example.reminisce_ticketing.adapter.HomeItemAdapter;
import com.example.reminisce_ticketing.apiservice.ApiInterface;
import com.example.reminisce_ticketing.apiservice.RetrofitClient;
import com.example.reminisce_ticketing.auth.login.LoginActivity;
import com.example.reminisce_ticketing.constant.Constants;
import com.example.reminisce_ticketing.model.EventListModel;
import com.example.reminisce_ticketing.model.ScannerData;
import com.example.reminisce_ticketing.model.ValidationData;
import com.example.reminisce_ticketing.ui.scanResult.ScanResult;
import com.example.reminisce_ticketing.utils.SharedPref;
import com.example.reminisce_ticketing.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.zxing.Result;

import org.json.JSONException;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanWithCamera extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    public ProgressBar progressBar;
    CodeScannerView scannerView;
    private CodeScanner mCodeScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_with_camera);
        scannerView = findViewById(R.id.scanner_view);
        progressBar = findViewById(R.id.progressBar);
        mCodeScanner = new CodeScanner(this, scannerView);

        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // Permission is already granted, proceed with camera operations
            openCamera();
        } else {
            // Request camera permission if it's not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            // Check if the camera permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Camera permission granted, proceed with camera operations
                openCamera();
            } else {
                // Camera permission denied, handle accordingly (e.g., show a message)
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void openCamera() {
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("", "onDecoded_1 : " + new Gson().toJson(result.getText()));

                        if (result.getText() != null && !result.getText().equals("")) {
                            try {
                            Gson gson = new Gson();
                            ScannerData p = gson.fromJson(result.getText(), ScannerData.class);
                            Log.e("", "onDecoded_1 : " + new Gson().toJson(p));
                                callApiValidation(p);
                            } catch (JsonSyntaxException e) {
                                Utils.makeToast(getApplication(),getString(R.string.invalid_ticket));
                            }
                        } else {
                            Utils.makeToast(getApplication(),getString(R.string.invalid_data));
                        }
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    private void callApiValidation(ScannerData data) {
        progressBar.setVisibility(View.VISIBLE);
        ApiInterface service = RetrofitClient.getClient(this).create(ApiInterface.class);

        Call<ValidationData> call = service.validation(data);
        call.enqueue(new Callback<ValidationData>() {
            @Override
            public void onResponse(Call<ValidationData> call, Response<ValidationData> response) {
                progressBar.setVisibility(View.GONE);
                Log.e("Tag", "Response" + response.body());
                if (response.isSuccessful()) {
                    Log.e("EventList", "data" + response.body());
                    if (response.body() != null) {
                         Intent intent = new Intent(ScanWithCamera.this, ScanResult.class);
                         intent.putExtra(Constants.data,new Gson().toJson(response.body()));
                         startActivity(intent);
                    }
                } else {
                    Utils.makeToast(getApplication(),response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ValidationData> call, Throwable t) {
                Log.e("","onFailure : "+t.getMessage());
                progressBar.setVisibility(View.GONE);
                Utils.makeToast(getApplication(),t.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }
}