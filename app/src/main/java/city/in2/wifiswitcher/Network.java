package city.in2.wifiswitcher;

public class Network {

    String _network, _ssid, _bssid, _level, _date_created;
    int _id;

    public Network(int id, String network) {
        this._id = id;
        this._network = network;
//        this._ssid = ssid;
//        this._bssid = bssid;
//        this._level = level;
//        this._date_created = date_created;
    }

    public Network(String network) {
        this._network = network;
//        this._ssid = ssid;
//        this._bssid = bssid;
//        this._level = level;
//        this._date_created = date_created;
    }

    public Network() {

    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int v) {
        this._id = v;
    }

    public String get_network() {
        return this._network;
    }

    public void set_network(String v) {
        this._network = v;
    }

    public String get_ssid() {
        return this._ssid;
    }

    public void set_ssid(String v) {
        this._ssid = v;
    }

    public String get_bssid() {
        return this._bssid;
    }

    public void set_bssid(String v) {
        this._bssid = v;
    }

    public String get_level() {
        return this._level;
    }

    public void set_level(String v) {
        this._level = v;
    }

    public String get_date_created() {
        return this._date_created;
    }

    public void set_date_created(String v) {
        this._date_created = v;
    }
}
