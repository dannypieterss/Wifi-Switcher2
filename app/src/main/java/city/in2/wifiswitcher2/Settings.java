package city.in2.wifiswitcher2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class Settings extends Activity {

    Database db;
    Library library;

    Vibrator vibe;
    RelativeLayout scroll;

    Button save_network_name, clear_data, save_settings;
    EditText network_name;
    CheckBox level1, level2, level3, level4, level5, level6, level7, level8, level9, level10;
    int _level1 = 0, _level2 = 0, _level3 = 0, _level4 = 0, _level5 = 0, _level6 = 0, _level7 = 0, _level8 = 0, _level9 = 0, _level10 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        library = new Library(getApplicationContext());
        db = new Database(getApplicationContext());

        String SSID = library._get("ssid");
        network_name = (EditText) findViewById(R.id.network_name);
        network_name.setText(SSID);

        save_network_name = (Button) findViewById(R.id.save_network_name);
        save_network_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                library._set("ssid", network_name.getText().toString());
                library.Toast("SSID -> " + network_name.getText().toString());
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
}
