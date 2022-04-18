package city.in2.wifiswitcher;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Database extends SQLiteOpenHelper {

    static SQLiteDatabase db;

    byte[] image;
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "wifi_updater";

    private static final String TABLE_ACTIONS = "actions";
    private static final String TABLE_NETWORKS = "networks";
    private static final String TABLE_BSSID = "bssid";
    private static final String TABLE_LEVELS = "levels";

    private static final String KEY_ID = "_id";
    private static final String NETWORK = "network";
    private static final String TIME = "_time";
    private static final String ACTION = "_action";
    private static final String SSID = "_ssid";
    private static final String LEVEL = "_level";
    private static final String DESCRIPTION = "description";

    private static final String BSSID = "bssid";

    private static final String LEVEL1 = "_level1";
    private static final String LEVEL2 = "_level2";
    private static final String LEVEL3 = "_level3";
    private static final String LEVEL4 = "_level4";
    private static final String LEVEL5 = "_level5";
    private static final String LEVEL6 = "_level6";
    private static final String LEVEL7 = "_level7";
    private static final String LEVEL8 = "_level8";
    private static final String LEVEL9 = "_level9";
    private static final String LEVEL10 = "_level10";

    private static final String DATE_CREATED = "date_created";

    // Tables int id, String time, String action, String ssid, String level, String description, String date_created
    private static final String CREATE_ACTIONS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_ACTIONS + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TIME + " TEXT, "
            + ACTION + " TEXT, "
            + SSID + " TEXT, "
            + LEVEL + " TEXT, "
            + DESCRIPTION + " TEXT, "
            + DATE_CREATED + " TEXT )";

    private static final String CREATE_NETWORKS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NETWORKS + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NETWORK + " TEXT, "
            + SSID + " TEXT, "
            + DATE_CREATED + " TEXT )";


    private static final String CREATE_BSSID_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_BSSID + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SSID + " TEXT, "
            + BSSID + " TEXT, "

            + LEVEL + " TEXT, "

            + LEVEL1 + " TEXT, "
            + LEVEL2 + " TEXT, "
            + LEVEL3 + " TEXT, "
            + LEVEL4 + " TEXT, "
            + LEVEL5 + " TEXT, "
            + LEVEL6 + " TEXT, "
            + LEVEL7 + " TEXT, "
            + LEVEL8 + " TEXT, "
            + LEVEL9 + " TEXT, "
            + LEVEL10 + " TEXT, "
            + DATE_CREATED + " TEXT )";


    public Action getAction(String id) {
        Cursor c = query("SELECT * FROM " + TABLE_ACTIONS + " WHERE " + KEY_ID + " = ? LIMIT 1", new String[]{String.valueOf(id)});
        Action item = new Action();
        if (c.moveToFirst())
            item = setAction(c);
        c.close();
        return item;
    }

    public ArrayList<Action> getActionsDate() {
        ArrayList<Action> items = new ArrayList<Action>();
        Cursor c = query("SELECT * FROM " + TABLE_ACTIONS + " WHERE " + DATE_CREATED + " = ?");
        if (c.moveToFirst()) {
            c.moveToFirst();
            do {
                items.add(setAction(c));
            } while (c.moveToNext());
        }
        c.close();
        return items;
    }

    public Network getNetworkByID(String id) {
        Cursor c = query("SELECT * FROM " + TABLE_NETWORKS + " WHERE " + KEY_ID + " = ? LIMIT 1", new String[]{String.valueOf(id)});
        Network item = new Network();
        if (c.moveToFirst())
            item = setNetwork(c);
        c.close();
        return item;
    }

    public Network getNetworkByName(String name) {
        Cursor c = query("SELECT * FROM " + TABLE_NETWORKS + " WHERE " + NETWORK + " = ? LIMIT 1", new String[]{String.valueOf(name)});
        Network item = new Network();
        if (c.moveToFirst())
            item = setNetwork(c);
        c.close();
        return item;
    }

    public int checkNetwork(String name) {
        Cursor c = query("SELECT * FROM " + TABLE_NETWORKS + " WHERE " + NETWORK + " = ?", new String[]{String.valueOf(name)});
        int count = 0;
        if (c.moveToFirst())
            count = c.getCount();
        c.close();
        return count;
    }

    public int checkBSSID(String name) {
        Cursor c = query("SELECT * FROM " + TABLE_BSSID + " WHERE " + BSSID + " = ?", new String[]{String.valueOf(name)});
        int count = 0;
        if (c.moveToFirst())
            count = c.getCount();
        c.close();
        return count;
    }

    public Network getNetworkName(String v) {
        Cursor c = query("SELECT * FROM " + TABLE_NETWORKS + " WHERE " + NETWORK + " = ? LIMIT 1", new String[]{String.valueOf(v)});
        Network item = new Network();
        if (c.moveToFirst())
            item = setNetwork(c);
        c.close();
        return item;
    }

    public ArrayList<BSSID> getBSSIDSName(String v) {
        Cursor c = query("SELECT * FROM " + TABLE_BSSID + " WHERE " + SSID + " = ? LIMIT 1", new String[]{String.valueOf(v)});
        ArrayList<BSSID> items = new ArrayList<BSSID>();
        if (c.moveToFirst())
            items.add(setBSSID(c));
        c.close();
        return items;
    }

    public ArrayList<Network> getNetworksByDate() {
        ArrayList<Network> items = new ArrayList<Network>();
        Cursor c = query("SELECT * FROM " + TABLE_NETWORKS + " WHERE " + DATE_CREATED + " = ?");
        if (c.moveToFirst()) {
            c.moveToFirst();
            do {
                items.add(setNetwork(c));
            } while (c.moveToNext());
        }
        c.close();
        return items;
    }

//    public void updateOrder(Order c) {
//        ContentValues values = new ContentValues();
//        Log.d("Updating company: ", "id: " + c.get_id() + " name " + c.get_name() + " updated at: " + c.get_name());
////        values.put(CUSTOMER_ID, c.get_customer_id());
////        values.put(TITLE, c.get_title());
////        values.put(COMPANY_NAME, c.get_name());
////        values.put(ADDRESS, c.get_address());
////        values.put(ZIP, c.get_zip());
////        values.put(CITY, c.get_city());
////        values.put(WEBSITE, c.get_website());
////        values.put(EMAIL, c.get_email());
////        values.put(PHONE, c.get_phone());
////        values.put(FAX, c.get_fax());
////        values.put(ACTIVE, c.get_active());
////        values.put(CREATED, c.get_date_created());
////        values.put(DATE_UPDATED, c.get_date_updated());
//        updateOrder(values, c.get_id());
//    }

    public ArrayList<Network> getNetworks() {
        ArrayList<Network> actions = new ArrayList<Network>();
        Cursor c = query("SELECT * FROM " + TABLE_NETWORKS, null);
        if (c.moveToFirst()) {
            c.moveToFirst();
            do {
                actions.add(setNetwork(c));
            } while (c.moveToNext());
        }
        c.close();
        return actions;
    }

    public ArrayList<Action> getActions() {
        ArrayList<Action> actions = new ArrayList<Action>();
        Cursor c = query("SELECT * FROM " + TABLE_ACTIONS, null);
        if (c.moveToFirst()) {
            c.moveToFirst();
            do {
                actions.add(setAction(c));
            } while (c.moveToNext());
        }
        c.close();
        return actions;
    }

    public void addAction(Action action) {
        ContentValues values = new ContentValues();
        values.put(TIME, action.get_time());
        values.put(ACTION, action.get_action());
        values.put(SSID, action.get_ssid());
        values.put(LEVEL, action.get_level());
        values.put(DESCRIPTION, action.get_description());
        values.put(DATE_CREATED, action.get_date_created());
        insertActionDatabase(values);
    }

    public Action setAction(Cursor c) {
        Action action = new Action();
        action.set_id(getInt(c, KEY_ID));
        action.set_time(getString(c, TIME));
        action.set_action(getString(c, ACTION));
        action.set_ssid(getString(c, SSID));
        action.set_level(getString(c, LEVEL));
        action.set_description(getString(c, DESCRIPTION));
        action.set_date_created(getString(c, DATE_CREATED));
        return action;
    }

    public void updateAction(Action action) {
        ContentValues values = new ContentValues();
        values.put(TIME, action.get_time());
        values.put(ACTION, action.get_action());
        values.put(SSID, action.get_ssid());
        values.put(DESCRIPTION, action.get_description());
        values.put(DATE_CREATED, action.get_date_created());
        updateActionDatabase(values, action.get_id());
    }

    public void addNetwork(Network network) {
        ContentValues values = new ContentValues();
        values.put(NETWORK, network.get_network());
        values.put(SSID, network.get_ssid());
        values.put(BSSID, network.get_bssid());
        values.put(LEVEL, network.get_level());
        values.put(DATE_CREATED, network.get_date_created());
        insertNetwork(values);
    }

    public void addBSSID(BSSID bssid) {
        ContentValues values = new ContentValues();
        values.put(SSID, bssid.get_ssid());
        values.put(BSSID, bssid.get_bssid());
        values.put(LEVEL, bssid.get_level());

        values.put(LEVEL1, bssid.get_level1());
        values.put(LEVEL2, bssid.get_level2());
        values.put(LEVEL3, bssid.get_level3());
        values.put(LEVEL4, bssid.get_level4());
        values.put(LEVEL5, bssid.get_level5());
        values.put(LEVEL6, bssid.get_level6());
        values.put(LEVEL7, bssid.get_level7());
        values.put(LEVEL8, bssid.get_level8());
        values.put(LEVEL9, bssid.get_level9());
        values.put(LEVEL10, bssid.get_level10());

        insertBSSIDDatabase(values);
    }

    public Network setNetwork(Cursor c) {
        Network network = new Network();
        network.set_id(getInt(c, KEY_ID));
        network.set_network(getString(c, NETWORK));
        network.set_ssid(getString(c, SSID));
        network.set_bssid(getString(c, BSSID));
        network.set_level(getString(c, LEVEL));
        network.set_date_created(getString(c, DATE_CREATED));
        return network;
    }

    public void updateNetwork(Network network) {
        ContentValues values = new ContentValues();
        values.put(NETWORK, network.get_network());
        values.put(SSID, network.get_ssid());
        values.put(BSSID, network.get_bssid());
        values.put(DATE_CREATED, network.get_date_created());
        updateNetworkDatabase(values, network.get_id());
    }

    public BSSID setBSSID(Cursor c) {
        BSSID action = new BSSID();
        action.set_id(getInt(c, KEY_ID));
        action.set_ssid(getString(c, SSID));
        action.set_bssid(getString(c, BSSID));

        action.set_level(getInt(c, LEVEL));

        action.set_level1(getInt(c, LEVEL1));
        action.set_level2(getInt(c, LEVEL2));
        action.set_level3(getInt(c, LEVEL3));
        action.set_level4(getInt(c, LEVEL4));
        action.set_level5(getInt(c, LEVEL5));
        action.set_level6(getInt(c, LEVEL6));
        action.set_level7(getInt(c, LEVEL7));
        action.set_level8(getInt(c, LEVEL8));
        action.set_level9(getInt(c, LEVEL9));
        action.set_level10(getInt(c, LEVEL10));
        return action;
    }

    public void updateBSSID(BSSID bssid) {
        ContentValues values = new ContentValues();
        values.put(SSID, bssid.get_ssid());
        values.put(BSSID, bssid.get_bssid());

        values.put(LEVEL, bssid.get_level());

        values.put(LEVEL1, bssid.get_level1());
        values.put(LEVEL2, bssid.get_level2());
        values.put(LEVEL3, bssid.get_level3());
        values.put(LEVEL4, bssid.get_level4());
        values.put(LEVEL5, bssid.get_level5());
        values.put(LEVEL6, bssid.get_level6());
        values.put(LEVEL7, bssid.get_level7());
        values.put(LEVEL8, bssid.get_level8());
        values.put(LEVEL9, bssid.get_level9());
        values.put(LEVEL10, bssid.get_level10());
        updateLevelDatabase(values, bssid.get_id());
    }

    public void insertActionDatabase(ContentValues values) {
//        Log.d("database", "insert action values: " + values.toString());
        insert(TABLE_ACTIONS, values);
    }

    public void insertBSSIDDatabase(ContentValues values) {
//        Log.d("database", "insert action values: " + values.toString());
        insert(TABLE_BSSID, values);
    }

    public void insertNetwork(ContentValues values) {
//        Log.d("database", "insert network values: " + values.toString());
        insert(TABLE_NETWORKS, values);
    }

    public void updateActionDatabase(ContentValues v, int i) {
        update(TABLE_ACTIONS, KEY_ID, i, v);
    }

    public void updateNetworkDatabase(ContentValues v, int i) {
        update(TABLE_NETWORKS, KEY_ID, i, v);
    }

    public void updateLevelDatabase(ContentValues v, int i) {
        update(TABLE_LEVELS, KEY_ID, i, v);
    }

    public void removeAction(int id) {
        remove(TABLE_ACTIONS, KEY_ID, id);
    }

    public void removeNetwork(int id) {
        remove(TABLE_NETWORKS, KEY_ID, id);
    }

    private void remove(String t, String w, int id) {
        SQLiteDatabase db = db();
        db.delete(t, w + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACTIONS_TABLE);
        db.execSQL(CREATE_NETWORKS_TABLE);
        db.execSQL(CREATE_BSSID_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onDrop(db);
        onCreate(db);
    }

    public void onDrop(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NETWORKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BSSID);
    }

    public void onClear() {
        onDrop(db());
        onCreate(db());
    }

    public void ClearOrders() {
        // db().execSQL("DROP TABLE IF EXISTS " + TABLE_BESTELLING);
        // db().execSQL(CREATE_BESTELLING_TABLE);
        // Close();
    }

    @SuppressLint("Range")
    private String getString(Cursor c, String tag) {
        return c.getString(c.getColumnIndex(tag)).toString();
    }

    @SuppressLint("Range")
    private int getInt(Cursor c, String tag) {
        return c.getInt(c.getColumnIndex(tag));
    }

    public String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()).toString();
    }

    public Cursor query(String sql, String[] args) {
        SQLiteDatabase db = db();
        Cursor c = db.rawQuery(sql, args);
        return c;
    }

    public SQLiteDatabase db() {
        return this.getWritableDatabase();
    }

    private void L(String s) {
        Log.d("data", s);
    }

    public void update(String t, String w, int i, ContentValues v) {
        db = db();
        db.update(t, v, w + " = ? ", new String[]{String.valueOf(i)});
        db.close();
    }

    private boolean _boolean(String value) {
        boolean returnValue = false;
        if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value) || "on".equalsIgnoreCase(value))
            returnValue = true;
        return returnValue;
    }

    public boolean exists(String t, String w, String i) {
        Cursor c = query("SELECT * FROM " + t + " WHERE " + w + " = ?", new String[]{i});
        int count = c.getCount();
        L("Count->" + t + "->" + w + "->" + i + "=>" + String.valueOf(c.getCount()));
        c.close();
        if (count >= 1) {
            return true;
        }
        return false;
    }

    private Cursor where(String t, String w, int id) {
        return query("SELECT * FROM " + t + " WHERE " + w + " = ?", new String[]{String.valueOf(id)});
    }


    private void insert(String table, ContentValues c) {
        SQLiteDatabase db = db();
        db.insert(table, null, c);
        db.close();
    }

    private int count(String t, String w, String id) {
        Cursor c = query("SELECT * FROM " + t + " WHERE " + w + " = ?", new String[]{String.valueOf(id)});
        int count = c.getCount();
        c.close();
        return count;
    }

    public int count(String t) {
        Cursor c = query("SELECT * FROM " + t, null);
        int count = c.getCount();
        c.close();
        return count;
    }

    public void showCursor(Cursor c) {
        for (int i = 0; i < c.getColumnCount(); i++) {
            try {
                Log.d("setTask", "String => " + (c.getColumnName(i) + " => " + getString(c, c.getColumnName(i))));
            } catch (Exception e) {
                Log.d("setTask", "Int    => " + (c.getColumnName(i) + " => " + getInt(c, c.getColumnName(i))));
            }
        }
    }

    private Cursor query(String t) {
        return query("SELECT * FROM " + t, null);
    }

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = db();
    }
}
