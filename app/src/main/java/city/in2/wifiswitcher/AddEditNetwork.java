package city.in2.wifiswitcher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class AddEditNetwork extends Activity {

    Database db;
    Library library;

    Vibrator vibe;
    RelativeLayout scroll;

    Button save_network_name, clear_data, save_settings;
    EditText network_name;
    CheckBox level1, level2, level3, level4, level5, level6, level7, level8, level9, level10;
    int _level1 = 0, _level2 = 0, _level3 = 0, _level4 = 0, _level5 = 0, _level6 = 0, _level7 = 0, _level8 = 0, _level9 = 0, _level10 = 0;

    Context context;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.add_edit_network);
    vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    library = new Library(getApplicationContext());
    db = new Database(getApplicationContext());


}
}
