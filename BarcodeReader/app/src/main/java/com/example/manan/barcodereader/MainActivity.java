package com.example.manan.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import static android.R.attr.data;

public class MainActivity extends Activity {

    private TextView mBarcdeResult;
    private Button mScanButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBarcdeResult = (TextView)findViewById(R.id.barcode_result);
        mScanButton = (Button)findViewById(R.id.scan_barcode);
        mScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ScanBarcodeActivity.class);
                startActivityForResult(intent, 0);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0 && resultCode == CommonStatusCodes.SUCCESS && data != null)
        {
            Barcode barcode = data.getParcelableExtra("barcode");
            mBarcdeResult.setText("Barcode value is : " + barcode.displayValue);
        }
        else
        {
            mBarcdeResult.setText("No barcode found");
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}
