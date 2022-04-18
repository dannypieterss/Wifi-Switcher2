package city.in2.wifiswitcher;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("ALL")
public class BackgroundService extends android.app.Service {

    WifiManager wifiManager;
    //    String ssid = "Tele2-2";
    String ssid = "Home";
    String key = "1A2b3c4d5e";
    int netId;
    Vibrator v;

    int interval = 2000;
    byte imageInByte[];

    private static ThreadDemo td;
    Library library;
    Database db;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        library = new Library(getApplicationContext());
        db = new Database(getApplicationContext());
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (td != null && td.isAlive()) {
            Log.d("BackgroundService", "Thread running");
        } else {
            Log.d("BackgroundService", "Thread starting");
            td = new ThreadDemo();
            td.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private class ThreadDemo extends Thread {
        @Override
        public void run() {
            super.run();
            do {
                // Wifi check
                wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                int rssi = wifiManager.getConnectionInfo().getRssi();
                int level = WifiManager.calculateSignalLevel(rssi, 10);

                library._set("current_level", "" + level);

                WifiInfo info = wifiManager.getConnectionInfo();

                String SSID = info.getSSID().replace("\"", "");
                library.logImportant(SSID);
                ssid = library._get("ssid");
                if (info.getSSID().contains(ssid)) {
                    WifiConfiguration wifiConfig = new WifiConfiguration();
                    wifiConfig.SSID = String.format("\"%s\"", ssid);
                    wifiConfig.preSharedKey = String.format("\"%s\"", key);

                    String macAddr = info.getMacAddress();
                    String test = info.getMacAddress();

                    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    String format = s.format(new Date());
                    if (library._getInt("service") == 1) {
                        switch (level) {
                            case 1:
                                if (library._getInt("level1") == 1) {
                                    library.logImportant("CASE 1");
                                    db.addAction(new Action(format, "Reconnect", wifiConfig.SSID.replace("\"", ""), "" + level, "description", format));
                                    netId = wifiManager.addNetwork(wifiConfig);
                                    wifiManager.enableNetwork(netId, true);
                                    wifiManager.reconnect();
                                    Success();
                                }
                                break;
                            case 2:
                                if (library._getInt("level2") == 1) {
                                    library.logImportant("CASE 2");
                                    db.addAction(new Action(format, "Reconnect", wifiConfig.SSID.replace("\"", ""), "" + level, "description", format));
                                    netId = wifiManager.addNetwork(wifiConfig);
                                    wifiManager.enableNetwork(netId, true);
                                    wifiManager.reconnect();
                                    Success();
                                }
                                break;
                            case 3:
                                if (library._getInt("level3") == 1) {
                                    library.logImportant("CASE 3");
                                    db.addAction(new Action(format, "Reconnect", wifiConfig.SSID.replace("\"", ""), "" + level, "description", format));
                                    netId = wifiManager.addNetwork(wifiConfig);
                                    wifiManager.enableNetwork(netId, true);
                                    wifiManager.reconnect();
                                    Success();
                                }
                                break;
                            case 4:
                                if (library._getInt("level4") == 1) {
                                    library.logImportant("CASE 4");
                                    db.addAction(new Action(format, "Reconnect", wifiConfig.SSID.replace("\"", ""), "" + level, "description", format));
                                    netId = wifiManager.addNetwork(wifiConfig);
                                    wifiManager.enableNetwork(netId, true);
                                    wifiManager.reconnect();
                                    Success();
                                }
                                break;
                            case 5:
                                if (library._getInt("level5") == 1) {
                                    library.logImportant("CASE 5");
                                    db.addAction(new Action(format, "Reconnect", wifiConfig.SSID.replace("\"", ""), "" + level, "description", format));
                                    netId = wifiManager.addNetwork(wifiConfig);
                                    wifiManager.enableNetwork(netId, true);
                                    wifiManager.reconnect();
                                    Success();
                                }
                                break;
                            case 6:
                                if (library._getInt("level6") == 1) {
                                    library.logImportant("CASE 6");
                                    db.addAction(new Action(format, "Reconnect", wifiConfig.SSID.replace("\"", ""), "" + level, "description", format));
                                    netId = wifiManager.addNetwork(wifiConfig);
                                    wifiManager.enableNetwork(netId, true);
                                    wifiManager.reconnect();
                                    Success();
                                }
                                break;
                            case 7:
                                if (library._getInt("level7") == 1) {
                                    library.logImportant("CASE 7");
                                    db.addAction(new Action(format, "Reconnect", wifiConfig.SSID.replace("\"", ""), "" + level, "description", format));
                                    netId = wifiManager.addNetwork(wifiConfig);
                                    wifiManager.enableNetwork(netId, true);
                                    wifiManager.reconnect();
                                    Success();
                                }
                                break;
                            case 8:
                                if (library._getInt("level8") == 1) {
                                    library.logImportant("CASE 8");
                                    db.addAction(new Action(format, "Reconnect", wifiConfig.SSID.replace("\"", ""), "" + level, "description", format));
                                    netId = wifiManager.addNetwork(wifiConfig);
                                    wifiManager.enableNetwork(netId, true);
                                    wifiManager.reconnect();
                                    Success();
                                }
                                break;
                            case 9:
                                if (library._getInt("level9") == 1) {
                                    library.logImportant("CASE 9");
                                    db.addAction(new Action(format, "Reconnect", wifiConfig.SSID.replace("\"", ""), "" + level, "description", format));
                                    netId = wifiManager.addNetwork(wifiConfig);
                                    wifiManager.enableNetwork(netId, true);
                                    wifiManager.reconnect();
                                    Success();
                                }
                                break;
                            case 10:
                                if (library._getInt("level10") == 1) {
                                    library.logImportant("CASE 10");
                                    db.addAction(new Action(format, "Reconnect", wifiConfig.SSID.replace("\"", ""), "" + level, "description", format));
                                    netId = wifiManager.addNetwork(wifiConfig);
                                    wifiManager.enableNetwork(netId, true);
                                    wifiManager.reconnect();
                                    Success();
                                }
                                break;
                        }
                    } else {
                        library.logImportant("Service is sleeping");
                    }
                } else {
                    library.biglog("Else Not the SSID we are looking for " + ssid + " ===> " + info.getSSID());
                }
                try {
                    library.logImportant("Sleep ===> " + interval);
                    sleep(interval);
                } catch (InterruptedException e) {
                    library.biglog("Error sleeping");
                    e.printStackTrace();
                }
            } while (1 != 0);
        }
    }

    public void Alert() {
        v.vibrate(new long[]{0, 200, 50, 250, 0}, -1);
    }

    public void Success() {
        v.vibrate(new long[]{100, 150, 50}, -1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//		Toast.makeText(this, "BackgroundService Destroy", 300);
        // Log.d("BackgroundService..", "1");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
//		Toast.makeText(this, "BackgroundService LowMemory", 300);
        // Log.d("BackgroundService..", "2");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    public void onLowBattery() {
        // Nog uitwerken
    }
}
