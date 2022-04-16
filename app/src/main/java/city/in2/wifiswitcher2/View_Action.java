package city.in2.wifiswitcher2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class View_Action extends Activity {

    private static String ID = "id";

    Database db;
    Library library;
    ArrayList<Action> tasks = new ArrayList<Action>();

    TextView customer_txt;
    Vibrator vibe;
    RelativeLayout scroll;
    Intent in;
    String _id, _name;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_action);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        library = new Library(getApplicationContext());
        in = getIntent();
        db = new Database(getApplicationContext());

        _id = in.getStringExtra(ID);
        Action action = db.getAction(_id);
//        library.Toast("Task size: " + tasks.size());
//        for(int x = 0; x < tasks.size(); x++) {
//            Task t = tasks.get(x);
//            ArrayList<Taskdata> taskdata = db.getTaskdata(t.getId());
//            for(int y = 0; y < taskdata.size(); y++) {
//                Taskdata w = taskdata.get(y);
//                library.Toast("Task data: " + w.getStart() + " minutes -> " + w.getEnd());
//
//            }
//        }


        customer_txt = (TextView) findViewById(R.id.customer_txt);
        customer_txt.setText(action.get_ssid());
//        lv.setDivider(new ColorDrawable(0x001072ba));
//        lv.setDividerHeight(1);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                String _id = ((TextView) view.findViewById(R.id.pid)).getText().toString();
////                Intent in = new Intent(getApplicationContext(), View_Task.class);
////                in.putExtra(ID, _id);
////                startActivity(in);
////                overridePendingTransition(R.anim.fade_in, R.anim.fade_in);
//            }
//        });

//        close_categorie_details = (RelativeLayout) findViewById(R.id.close_categorie_details);
//        close_categorie_details.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
////        tasks = db.getTasks(_id);
////        library.Toast("Customer: "  + _id + " " + " tasks->" + tasks.size());
////        setData();
////
//      Referentie van scroll view
        scroll = (RelativeLayout) findViewById(R.id.customer_finish);
        scroll.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            public void onSwipeTop() { }

            public void onSwipeRight() { }

            public void onSwipeLeft() { finish(); }

            public void onSwipeBottom() { }

        });
    }

    public void setData() {
//        tasks = db.getFinished(Integer.parseInt(_id));
//        finished_tasks_adapter = new TaskArrayAdapter(this, R.layout.task_finished, tasks);
//        lv.setAdapter(finished_tasks_adapter);
//        finished_tasks_adapter.notifyDataSetChanged();
    }

}
