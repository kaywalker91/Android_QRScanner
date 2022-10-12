package com.kaywalker.qrscanner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    Button btn_QR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_QR = findViewById(R.id.btn_QR);
        btn_QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanCode();
            }
        });
    }

    private void scanCode() {

        ScanOptions scanOptions = new ScanOptions();
        scanOptions.setPrompt("QR코드를 스캔 해주세요");
        //인식시 소리 삑소리 허용
        scanOptions.setBeepEnabled(true);
        //움직임에 따라 스캔코드가 변화 허용
        scanOptions.setOrientationLocked(true);
        //QR스캐너 생성
        scanOptions.setCaptureActivity(QRScannerClass.class);
        barLauncher.launch(scanOptions);

    }

    //액티비티에서 QR스캐너를 실행
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(),result ->
    {
        if(result.getContents() != null){

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("스캔결과");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }

            }).show();

        }

    });

}