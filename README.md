## React Native Honeywell N43XX Scanning Package
README

## Getting started
1. Clone the project

import io.stackworx.honeywell.Package;

include ':react-native-honeywellN43XX'
project(':react-native-honeywellN43XX').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-honeywellN43XX/android')

compile project(':react-native-honeywellN43XX')


import Honeywell from 'react-native-honeywellN43XX'

  openHoneywell() {
    console.log(Honeywell)
    const response = Honeywell.open()
  }

  closeHoneywell() {
    console.log(Honeywell)
    const response = Honeywell.close()
  }

  startHoneywell() {
    console.log(Honeywell)
    const response = Honeywell.start()
  }


  stopHoneywell() {
    console.log(Honeywell)
    const response = Honeywell.stop()
  }
  
  
  
  In your mainApplicationActivity.java
  
  
public class MainActivity extends ReactActivity {
    ScanDevice sm;
    private final static String SCAN_ACTION = "scan.rcv.message";
    private String barcodeStr;
    private EditText showScanResult;

    @Override
    protected String getMainComponentName() {
        return "StarterKit";
    }

    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("HONEY", "SUCCESS RECEIVE");
            Log.i("HONEY", intent.toString());
            byte[] barocode = intent.getByteArrayExtra("barocode");
            int barocodelen = intent.getIntExtra("length", 0);
            byte temp = intent.getByteExtra("barcodeType", (byte) 0);
            Log.i("HONEY", "----codetype--" + temp);
            barcodeStr = new String(barocode, 0, barocodelen);
            Log.i("HONEY", barcodeStr);
//            sm.stopScan();
        }

    };

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
//        if(sm != null) {
//            sm.stopScan();
//        }
        unregisterReceiver(mScanReceiver);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SCAN_ACTION);
        registerReceiver(mScanReceiver, filter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SCAN_ACTION);
        registerReceiver(mScanReceiver, filter);
    }
}
