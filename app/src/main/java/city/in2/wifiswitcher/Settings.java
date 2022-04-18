package city.in2.wifiswitcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Settings extends Activity {

    Database db;
    Library library;
    WifiManager wifiManager;
    int netId;

    Vibrator vibe;
    RelativeLayout scroll;
    LinearLayout networks_from_database;

    Button save_network_name, reconnect, clear_data, save_settings;
    EditText network_name;
    CheckBox level1, level2, level3, level4, level5, level6, level7, level8, level9, level10;
    int _level1 = 0, _level2 = 0, _level3 = 0, _level4 = 0, _level5 = 0, _level6 = 0, _level7 = 0, _level8 = 0, _level9 = 0, _level10 = 0;

    Context context;
    String ssid, bssid;
    Display display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        library = new Library(getApplicationContext());
        db = new Database(getApplicationContext());
        display = getWindowManager().getDefaultDisplay();
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

//        if (android.os.Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        }

        // you need to have a list of data that you want the spinner to display




        String SSID = library._get("ssid");

        networks_from_database = (LinearLayout) findViewById(R.id.networks_from_database);
        updateUI();


        network_name = (EditText) findViewById(R.id.network_name);
//        network_name.setText(SSID);

        save_network_name = (Button) findViewById(R.id.save_network_name);
        save_network_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getCurrentSsid()) {
                    if (db.checkNetwork(network_name.getText().toString()) > 0) {
                        Network network = db.getNetworkByName(network_name.getText().toString());
                        library.Toast("Network does exist" + network.get_ssid() + " <---");
                        if (db.checkBSSID(bssid) > 0) {
                            ArrayList<BSSID> ssids = db.getBSSIDSName(network.get_ssid());
                            if (ssids.size() > 0) {
                                library.Toast("We have found more than one!");
                            } else if (ssids.size() == 1) {
                                library.Toast("We have found one!");
                            } else {
                                library.Toast("We have to create a BSSID!");
                                db.addBSSID(new BSSID(ssid, bssid, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
                            }
                        } else {
                            library.Toast("BSSID does exist");
                        }
                    } else {
                        // network name does not exist in de the database
                        library.Toast("Network does not exist");
                        db.addNetwork(new Network(network_name.getText().toString()));
                        if (db.checkBSSID(ssid) == 0) {
                            library.Toast("Create BSSID");
                            db.addBSSID(new BSSID(ssid, bssid, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
                            updateUI();
                        }
                    }
                } else {

//                    db.addNetwork(new Network("", "", "", "", ""));
//                    library._set("ssid", network_name.getText().toString());
//                library.Toast("SSID -> " + network_name.getText().toString());
                    if (!db.checkNetwork(network_name.getText().toString())) {
                        library.Toast("Network exists!");
                    } else {
                        db.addNetwork(new Network(network_name.getText().toString()));
                    }
                }
            }
        });

        save_network_name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent myIntent = new Intent(Settings.this, Scan.class);
                Settings.this.startActivity(myIntent);
                return false;
            }
        });

        level1 = (CheckBox) findViewById(R.id.level1);
        level2 = (CheckBox) findViewById(R.id.level2);
        level3 = (CheckBox) findViewById(R.id.level3);
        level4 = (CheckBox) findViewById(R.id.level4);
        level5 = (CheckBox) findViewById(R.id.level5);
        level6 = (CheckBox) findViewById(R.id.level6);
        level7 = (CheckBox) findViewById(R.id.level7);
        level8 = (CheckBox) findViewById(R.id.level8);
        level9 = (CheckBox) findViewById(R.id.level9);
        level10 = (CheckBox) findViewById(R.id.level10);

        if (library._getInt("level1") == 1) {
            level1.setChecked(true);
            _level1 = 1;
        }

        if (library._getInt("level2") == 1) {
            level2.setChecked(true);
            _level2 = 1;
        }

        if (library._getInt("level3") == 1) {
            level3.setChecked(true);
            _level3 = 1;
        }

        if (library._getInt("level4") == 1) {
            level4.setChecked(true);
            _level4 = 1;
        }

        if (library._getInt("level5") == 1) {
            level5.setChecked(true);
            _level5 = 1;
        }

        if (library._getInt("level6") == 1) {
            level6.setChecked(true);
            _level6 = 1;
        }

        if (library._getInt("level7") == 1) {
            level7.setChecked(true);
            _level7 = 1;
        }

        if (library._getInt("level8") == 1) {
            level8.setChecked(true);
            _level8 = 1;
        }

        if (library._getInt("level9") == 1) {
            level9.setChecked(true);
            _level9 = 1;
        }

        if (library._getInt("level10") == 1) {
            level10.setChecked(true);
            _level10 = 1;
        }

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _level1 = library._getInt("level1");
                if (_level1 == 1) {
                    library._setInt("level1", 0);
                    _level1 = 0;
                } else {
                    library._setInt("level1", 1);
                    _level1 = 1;
                }
            }
        });

        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _level2 = library._getInt("level2");
                if (_level2 == 1) {
                    library._setInt("level2", 0);
                } else {
                    library._setInt("level2", 1);
                }
            }
        });

        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _level3 = library._getInt("level3");
                if (_level3 == 1) {
                    library._setInt("level3", 0);
                } else {
                    library._setInt("level3", 1);
                }
            }
        });

        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _level4 = library._getInt("level3");
                if (_level4 == 1) {
                    library._setInt("level4", 0);
                } else {
                    library._setInt("level4", 1);
                }
            }
        });

        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _level4 = library._getInt("level4");
                if (_level4 == 1) {
                    library._setInt("level4", 0);
                } else {
                    library._setInt("level4", 1);
                }
            }
        });

        level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _level5 = library._getInt("level5");
                if (_level5 == 1) {
                    library._setInt("level5", 0);
                } else {
                    library._setInt("level5", 1);
                }
            }
        });

        level6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _level6 = library._getInt("level6");
                if (_level6 == 1) {
                    library._setInt("level6", 0);
                } else {
                    library._setInt("level6", 1);
                }
            }
        });

        level7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _level7 = library._getInt("level7");
                if (_level7 == 1) {
                    library._setInt("level7", 0);
                } else {
                    library._setInt("level7", 1);
                }
            }
        });

        level8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _level8 = library._getInt("level8");
                if (_level8 == 1) {
                    library._setInt("level8", 0);
                } else {
                    library._setInt("level8", 1);
                }
            }
        });

        level9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _level9 = library._getInt("level9");
                if (_level9 == 1) {
                    library._setInt("level9", 0);
                } else {
                    library._setInt("level9", 1);
                }
            }
        });

        level10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _level10 = library._getInt("level10");
                if (_level10 == 1) {
                    library._setInt("level10", 0);
                } else {
                    library._setInt("level10", 1);
                }
            }
        });
        reconnect = (Button) findViewById(R.id.reconnect);
        reconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                WifiConfiguration wifiConfig = new WifiConfiguration();

                wifiConfig.SSID = String.format("\"%s\"", ssid);
//                wifiConfig.preSharedKey = String.format("\"%s\"", key);
                netId = wifiManager.addNetwork(wifiConfig);
                wifiManager.enableNetwork(netId, true);
                wifiManager.reconnect();

                library.Toast("We are reconnecting!");

            }
        });

        clear_data = (Button) findViewById(R.id.clear_data);
        clear_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.onClear();
            }
        });

        save_settings = (Button) findViewById(R.id.save_settings);
        save_settings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });


//      Referentie van scroll view
        scroll = (RelativeLayout) findViewById(R.id.customer_finish);
        scroll.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
            }

            public void onSwipeLeft() {
                finish();
            }

            public void onSwipeBottom() {
            }

        });
    }

    public boolean getCurrentSsid() {
        WifiManager wifiManager;
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        int rssi = wifiManager.getConnectionInfo().getRssi();
        int level = WifiManager.calculateSignalLevel(rssi, 10);

        WifiInfo info = wifiManager.getConnectionInfo();

        ssid = info.getSSID().replace("\"", "");
        bssid = info.getBSSID().replace("\"", "");

        if (ssid.equals(network_name.getText().toString())) {
            library.Toast("Is connected to network " + ssid);
            return true;
        } else {
            ssid = "";
            bssid = "";
            return false;
        }
    }

    private void updateUI() {
        networks_from_database.removeAllViews();
        ArrayList<Network> networks = db.getNetworks();
        library.Toast("Networks: " + networks.size());
        RelativeLayout.LayoutParams LayoutParamsview = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout _network_name = new LinearLayout(this);
        _network_name.setLayoutParams(LayoutParamsview);
        _network_name.setOrientation(LinearLayout.VERTICAL);
        _network_name.setBackgroundColor(Color.parseColor("#ff7800"));
        TextView tv = new TextView(this);
        tv.setTextSize(40);
        tv.setTextColor(Color.WHITE);
        tv.setText(ssid + " Tele2-2 ");
        _network_name.setPadding(20, 20, 20, 20);
        _network_name.addView(tv);

//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)tv.getLayoutParams();
//        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//        tv.setLayoutParams(layoutParams);

        LinearLayout cols = new LinearLayout(this);
        int width=display.getWidth();
        int height= 60; // display.getHeight();
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,height);

        cols.setOrientation(LinearLayout.VERTICAL);
        cols.setPadding(30, 30, 30, 30);
        cols.setBackgroundColor(Color.WHITE);
        cols.setId(1);
        TextView bssid_textview = new TextView(this);
        bssid_textview.setText("BSSID");
        bssid_textview.setTextSize(25);
        bssid_textview.setTextColor(Color.BLACK);
        cols.addView(bssid_textview);
//        View line = new View(this);
//
//        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 1, getResources().getDisplayMetrics());
//
//        RelativeLayout ruler = new RelativeLayout(this);
//
//        RelativeLayout.LayoutParams _ruler = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ruler.getLayoutParams().height = px);
//        ruler.setLayoutParams(_ruler);
//        cols.addView(ruler);

        TextView ssid_textview = new TextView(this);
        ssid_textview.setText("SSID");
        ssid_textview.setTextSize(25);
        ssid_textview.setTextColor(Color.BLACK);
        cols.addView(ssid_textview);
        _network_name.addView(cols);
        networks_from_database.addView(_network_name);
//        networks_from_database.addView(cols);
    }
}
