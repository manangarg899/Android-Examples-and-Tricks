package com.example.manan.barcodereader;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import java.io.IOException;

/**
 * Created by Manan on 02-07-2017.
 */

public class ScanBarcodeActivity extends Activity {

    SurfaceView cameraPreview;
    private Button mBarcodePick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);

        mBarcodePick = (Button) findViewById(R.id.pick_barcode);

        cameraPreview = (SurfaceView) findViewById(R.id.camera_preview);
        createCameraSource();
    }
    private void createCameraSource() {
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this).build();
        final CameraSource cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setAutoFocusEnabled(true).setRequestedPreviewSize(1600, 1024).build();

        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(ScanBarcodeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                       return;
                }
                try {
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcode = detections.getDetectedItems();
                if(barcode.size() > 0)
                {
                    Intent intent = new Intent();
                    intent.putExtra("barcode",barcode.valueAt(0));
                    setResult(CommonStatusCodes.SUCCESS,intent);
                    finish();
                }
            }
        });
    }

}
