package com.javaserv.javaserv;

public class TimerTime {
    int clsid;
    String name;
    int code;
    String dt;
    String tm;
    int tostore;
    int tp ;

    public TimerTime(int idin, String nmin, int codein, String dtin, String tmin, int tostorein, int tpin){
        clsid = idin;
        name = nmin;
        code = codein;
        dt = dtin;
        tm = tmin;
        tostore = tostorein;
        tp = tpin;
    }


}
