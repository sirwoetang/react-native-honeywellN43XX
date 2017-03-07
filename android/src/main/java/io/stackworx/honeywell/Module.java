package io.stackworx.honeywell;

//import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.app.Activity;
import android.content.Intent;
import android.device.ScanDevice;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Module extends ReactContextBaseJavaModule {
    ScanDevice sm;
    private final static String SCAN_ACTION = "scan.rcv.message";
    private String barcodeStr;

    public Module(ReactApplicationContext reactContext) {
        super(reactContext);
        sm = new ScanDevice();
        Log.i("HONEY777",sm.toString());
        reactContext.addActivityEventListener(new ActivityEventListener() {
            public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
                Log.i("HONEY777", String.format("Request Code: %s, Result Code: %s, Data: %s.", requestCode, resultCode, data));
            }

            public void onNewIntent(Intent intent) {
                Log.i("HONEY777", intent.toString());
                byte[] barocode = intent.getByteArrayExtra("barocode");
                int barocodelen = intent.getIntExtra("length", 0);
                byte temp = intent.getByteExtra("barcodeType", (byte) 0);
                barcodeStr = new String(barocode, 0, barocodelen);
                Log.i("HONEY777", barcodeStr);
                sm.stopScan();
            }
        });
    }

    @Override
    public String getName() {
        return "Honeywell";
    }

    @Override
    public Map<String, Object> getConstants() {
            final Map<String, Object> constants = new HashMap<>();
            return constants;
    }

    @ReactMethod
    public void open() {
        Log.i("HONEY", "OPEN");
        sm.openScan();
    }

    @ReactMethod
    public void start() {
//        Log.i("HONEY", "START");
//        sm.startScan();
        Log.i("HONEY", "CONTINUOUS");
        sm.setScanLaserMode(4);
    }

    @ReactMethod
    public void stop() {
        Log.i("HONEY", "OFF CONTINUOUS");
        sm.setScanLaserMode(8);
//        Log.i("HONEY", "STOP");
//        sm.stopScan();
    }

    @ReactMethod
    public void close() {
        Log.i("HONEY", "CLOSE");
        sm.closeScan();
    }
}