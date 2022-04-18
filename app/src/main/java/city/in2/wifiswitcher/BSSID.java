package city.in2.wifiswitcher;

public class BSSID {

    String _ssid, _bssid;
    int _id, _level, _level1, _level2, _level3, _level4,  _level5, _level6, _level7, _level8, _level9, _level10;

    public BSSID(int id, String ssid, int level, String bssid,  int level1, int level2, int level3, int level4, int level5, int level6, int level7, int level8, int level9, int level10) {
        this._id = id;
        this._ssid = ssid;
        this._bssid = bssid;

        this._level = level;

        this._level1 = level1;
        this._level2 = level2;
        this._level3 = level3;
        this._level4 = level4;
        this._level5 = level5;
        this._level6 = level6;
        this._level7 = level7;
        this._level8 = level8;
        this._level9 = level9;
        this._level10 = level10;
    }

    public BSSID(String ssid, String bssid, int level, int level1, int level2, int level3, int level4, int level5, int level6, int level7, int level8, int level9, int level10) {
        this._ssid = ssid;
        this._bssid = bssid;

        this._level = level;

        this._level1 = level1;
        this._level2 = level2;
        this._level3 = level3;
        this._level4 = level4;
        this._level5 = level5;
        this._level6 = level6;
        this._level7 = level7;
        this._level8 = level8;
        this._level9 = level9;
        this._level10 = level10;
    }

    public BSSID() {

    }

    public int get_id() {
        return this._id;
    }

    public void set_id(int v) {
        this._id = v;
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

    public int get_level() {
        return this._level;
    }

    public void set_level(int v) {
        this._level = v;
    }

    public int get_level1() {
        return this._level1;
    }

    public void set_level1(int v) {
        this._level1 = v;
    }

    public int get_level2() {
        return this._level2;
    }

    public void set_level2(int v) {
        this._level2 = v;
    }

    public int get_level3() {
        return this._level3;
    }

    public void set_level3(int v) {
        this._level3 = v;
    }

    public int get_level4() {
        return this._level4;
    }

    public void set_level4(int v) {
        this._level4 = v;
    }

    public int get_level5() {
        return this._level5;
    }

    public void set_level5(int v) {
        this._level5 = v;
    }

    public int get_level6() {
        return this._level6;
    }

    public void set_level6(int v) {
        this._level6 = v;
    }

    public int get_level7() {
        return this._level7;
    }

    public void set_level7(int v) {
        this._level7 = v;
    }

    public int get_level8() {
        return this._level8;
    }

    public void set_level8(int v) {
        this._level8 = v;
    }

    public int get_level9() {
        return this._level9;
    }

    public void set_level9(int v) {
        this._level9 = v;
    }

    public int get_level10() {
        return this._level10;
    }

    public void set_level10(int v) {
        this._level10 = v;
    }
}
