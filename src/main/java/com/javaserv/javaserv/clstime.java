package com.javaserv.javaserv;

public class clstime
{
    public int clsid;
    public String name;
    public int code ;
    public String dt ;
    public String tm ;
    public int tostore ;
    public int tp ;
    public clstime(int idin, String nmin, int codein, String dtin, String tmin, int tostorein, int tpin)
    {
        clsid = idin;
        name = nmin;
        code = codein;
        dt = dtin;
        tm = tmin;
        tostore = tostorein;
        tp = tpin;
    }
}
