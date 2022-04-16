package city.in2.wifiswitcher2;

public class Action {

    String _time, _action, _ssid, _level, _description, _date_created;
    int _id;

    public Action(int id, String time, String action, String ssid, String level, String description, String date_created) {
        this._id = id;
        this._time = time;
        this._action = action;
        this._ssid = ssid;
        this._level = level;
        this._description = description;
        this._date_created = date_created;
    }

    public Action(String time, String action, String ssid, String level, String description, String date_created) {
        this._time = time;
        this._action = action;
        this._ssid = ssid;
        this._level = level;
        this._description = description;
        this._date_created = date_created;
    }

    public Action() {

    }

    public void set_id(int v) {
        this._id = v;
    }

    public int get_id() {
        return this._id;
    }

    public void set_time(String v) {
        this._time = v;
    }

    public String get_time( ) {
        return this._time;
    }

    public void set_action(String v) {
        this._action = v;
    }

    public String get_action() {
        return this._action;
    }

    public void set_ssid(String v) {
        this._ssid = v;
    }

    public String get_ssid() {
        return this._ssid;
    }

    public void set_level(String v) {
        this._level = v;
    }

    public String get_level() {
        return this._level;
    }

    public void set_description(String v) {
        this._description = v;
    }

    public String get_description() {
        return this._description;
    }

    public void set_date_created(String v) {
        this._date_created = v;
    }

    public String get_date_created() {
        return this._date_created;
    }

}
