package city.in2.wifiswitcher2;

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
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wifi_updater";

    private static final String TABLE_ACTIONS = "actions";

    private static final String KEY_ID = "_id";
    private static final String TIME = "_time";
    private static final String ACTION = "_action";
    private static final String SSID = "_ssid";
    private static final String LEVEL = "_level";
    private static final String DESCRIPTION = "description";
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
        insertOrders(values);
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
        updateOrder(values, action.get_id());
    }

    public void insertOrders(ContentValues values) {
//        Log.d("database", "insert invoice values: " + values.toString());
        insert(TABLE_ACTIONS, values);
    }

    public void updateOrder(ContentValues v, int i) {
        update(TABLE_ACTIONS, KEY_ID, i, v);
    }

    public void removeAction(int id) {
        remove(TABLE_ACTIONS, KEY_ID, id);
    }


    private void remove(String t, String w, int id) {
        SQLiteDatabase db = db();
        db.delete(t, w + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onDrop(db);
        onCreate(db);
    }

    public void onDrop(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIONS);
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
        if(count >= 1) {
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
            } catch(Exception e) {
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
