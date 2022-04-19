package city.in2.wifiswitcher;

public class Network {

    String _network, _ssid, _bssid, _level, _date_created;
    int _id;

    public Network(int id, String network, String date_created) {
        this._id = id;
        this._network = network;
        this._date_created = date_created;
    }

    public Network(String network, String date_created) {
        this._network = network;
        this._date_created = date_created;
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

    public String get_date_created() {
        return this._date_created;
    }

    public void set_date_created(String v) {
        this._date_created = v;
    }
}
