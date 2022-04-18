package city.in2.wifiswitcher;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ActionArrayAdapter extends ArrayAdapter<Action> {
    Context context;
    int layoutResourceId;
    ArrayList<Action> data = new ArrayList<Action>();

    public ActionArrayAdapter(Context context, int layoutResourceId, ArrayList<Action> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ImageHolder holder = null;
        if (row == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ImageHolder();
            holder.id = (TextView) row.findViewById(R.id.pid);
            holder.time = (TextView) row.findViewById(R.id.time);
            holder.action = (TextView) row.findViewById(R.id.action);
            holder.ssid = (TextView) row.findViewById(R.id.ssid);
            holder.level = (TextView) row.findViewById(R.id.level);

//            holder.description = (TextView) row.findViewById(R.id.description);
//            holder.count = (TextView) row.findViewById(R.id.count);
//            holder.price = (TextView) row.findViewById(R.id.description);

            row.setTag(holder);
        } else {
            holder = (ImageHolder) row.getTag();
        }

        Action action = data.get(position);

        holder.id.setText("" + String.valueOf(action.get_id()));
        String all = action.get_time();
        String[] splitted = all.split(" ");
        holder.time.setText(splitted[1]);
        holder.action.setText(action.get_action());
        holder.ssid.setText(action.get_ssid());
        holder.level.setText("Level " + action.get_level());

        return row;
    }

    static class ImageHolder {
        TextView id, time, action, ssid, level;
    }

    public static Bitmap getRoundedBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.BLUE;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return output;
    }
}
