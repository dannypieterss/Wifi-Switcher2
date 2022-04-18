package city.in2.wifiswitcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {

    WifiManager wifiManager;
    Button current, scan, strenght, settings, start_service;
    String ssid = "Tele2-2";
    String key = "1A2b3c4d5e";
    int netId;
    private final int interval = 1000; // 1 Second
    TextView current_level;
    Library library;
    Database db;
    ActionArrayAdapter adapter;
    int active = 0;
    int xListViewHeight;
    Resources r;
    private static String ID = "id";
    ListView lv;
    ArrayList<Action> actions = new ArrayList<Action>();

    Vibrator v;

    String stringMac;
    WifiInfo info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.lv);
        library = new Library(getApplicationContext());
        db = new Database(this);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        r = getResources();

        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 71, r.getDisplayMetrics());
        int pixel = (int) px;

        Intent intent = new Intent(MainActivity.this, BackgroundService.class);
        current_level = (TextView) findViewById(R.id.current_level);

        info = wifiManager.getConnectionInfo();
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        int rssi = wifiManager.getConnectionInfo().getRssi();

        settings = (Button) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, Settings.class);
//                myIntent.putExtra("key", value); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });

        start_service = (Button) findViewById(R.id.start_service);
        start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (active == 0) {
                    startService(intent);
                    library._setInt("service", 1);
                    start_service.setText("Running");
                    active = 1;
                } else {
                    stopService(intent);
                    library._setInt("service", 0);
                    start_service.setText("Start service");
                    active = 0;
                }
            }
        });


        stringMac = getMacAddress();
//        Log.d("MyMacIS", mobile_mac_address);
//        macaddress.setText(mobile_mac_address);

        Intent myIntent = new Intent(MainActivity.this, Settings.class);
        MainActivity.this.startActivity(myIntent);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                int rssi = wifiManager.getConnectionInfo().getRssi();
                int level = WifiManager.calculateSignalLevel(rssi, 10);
                String SSID = info.getSSID().replace("\"", "");

                WifiManager wifiMgr = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                WifiInfo wifiInfo = wifiMgr.getConnectionInfo();

                String Bssi = wifiInfo.getBSSID();

                current_level.setText(SSID + " level: " + level + "/10");
                updateActions();
                handler.postDelayed(this, 5000);
            }
        }, 1000); //the time you want to delay in milliseconds

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String _id = ((TextView) view.findViewById(R.id.pid)).getText().toString();
//                Intent in = new Intent(getApplicationContext(), View_Action.class);
//                in.putExtra(ID, _id);
//                startActivity(in);
//                overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
            }
        });
    }

    public void updateActions() {
        ArrayList<Action> actions = db.getActions();
        Collections.reverse(actions);
        adapter = new ActionArrayAdapter(this, R.layout.action, actions);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void startService() {
        Log.d("service", "Update service is starting");
        Intent service = new Intent(this, BackgroundService.class);
        startService(service);
    }

    public void Alert() {
        v.vibrate(new long[]{0, 200, 50, 250, 0}, -1);
    }

    public void Success() {
        v.vibrate(new long[]{100, 150, 50}, -1);
    }

    public void Critical() {
        v.vibrate(new long[]{0, 500, 110, 500, 110, 450, 110, 200, 110, 170, 40, 450, 110, 200, 110, 170, 40, 500}, -1);
    }

    public String getMacAddress(){
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF) + ":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

//    public void wifimac() {
//        WifiInfo info = wifiMgr.getConnectionInfo();
//        String macAddress = info.getMacAddress();
//    }

}