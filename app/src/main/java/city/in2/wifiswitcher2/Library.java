package city.in2.wifiswitcher2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("CommitPrefEdits")
public class Library extends Activity {

	SharedPreferences p;
	SharedPreferences.Editor e;
//	public static Database db;
	static Context c;

	public Library(Context context) {
		c = context;
		p = PreferenceManager.getDefaultSharedPreferences(c);
		e = p.edit();
//		db = new Database(c);
	}

	public void logImportant(String v) {
        Log.d("", " ");
        Log.d("", " ");
        Log.d("", " ");
        Log.d("", " ");
        Log.d("", " ");
        Log.d("", " ");
        Log.d("", " ");
		Log.d("", "=========> " + v + ":");
        Log.d("", " ");
        Log.d("", " ");
        Log.d("", " ");
        Log.d("", " ");
        Log.d("", " ");
        Log.d("", " ");
        Log.d("", " ");
	}

	public void Toast(final String msg) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(c, msg, Toast.LENGTH_LONG).show();
			}
		});
	}

	public static boolean visible(View v) {
		if (v.getVisibility() == View.VISIBLE) {
			return true;
		}
		return false;
	}

	public void setText(final TextView t, final String text) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				t.setText(text);
			}
		});
	}

	public String getText(TextView t) {
		return t.getText().toString();
	}

	public boolean _check(String t) {
		if (p.getString(t, "").equals("")) {
			return false;
		}
		return true;
	}

	public void _set(String tag, String value) {
		e.putString(tag, value);
		e.commit();
	}

	public void _setInt(String tag, Integer v) {
		e.putInt(tag,v);
		e.commit();
	}

	public void _setBoolean(String tag, Boolean v) {
		e.putBoolean(tag,v);
		e.commit();
	}

	public int _getInt(String tag) {
		return (int)p.getInt(tag, 99999);
	}
	public boolean _getBoolean(String tag) {
		return p.getBoolean(tag, false);
	}

	public String _get(String tag) {
		return p.getString(tag, "");
	}

	public void _start(Intent _i) {
		Intent in = new Intent(_i);
		in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(in);
		// overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
	}

	public void Background(ImageView v, int id) {
		v.setBackgroundResource(id);
	}

	public void setActive(final TextView t) {
		t.setTextColor(Color.parseColor("#ffffff"));
		t.setTypeface(null, Typeface.BOLD);
	}

	public void setDisable(final TextView t) {
		t.setTextColor(Color.parseColor("#aaaaaa"));
		t.setTypeface(null, Typeface.NORMAL);
	}

	public void hide(View v) {
		v.setVisibility(View.INVISIBLE);
		v.setClickable(false);
	}

	public void gone(View v) {
		v.setVisibility(View.GONE);
		v.setClickable(false);
	}

	public void show(View v) {
		v.setVisibility(View.VISIBLE);
		v.setClickable(true);
	}
	
	public static void biglog(String s) {
		Log.d("data", s);
		Log.d("data", s);
		Log.d("data", s);
		Log.d("data", s);
		Log.d("data", s);
		Log.d("data", s);
		Log.d("data", s);
	}

	public void Li(int i) {
		Log.d("data", String.valueOf(i));
	}
}
