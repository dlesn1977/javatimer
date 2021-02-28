package com.javaserv.javaserv;
import java.util.*;


public class Cluster {
    public ArrayList <Timer> lsttm;
    public int id;
    public String nm;
    public int tp;
    public int tostore;
    public String msg ;
    public String userid;
    public Cluster(int idin, String useridin){

    }
    public Cluster(int idin, String nmin, int tpin, int tostorein)
    {
        setvalues(idin, nmin, tpin, tostorein);
    }
    public void setvalues(int idin, String nmin, int tpin, int tostorein){
        id = idin;
        nm = nmin;
        tp = tpin;
        tostore = tostorein;
    }
    public boolean gettrms()
    {


        lsttm = new ArrayList<Timer>();

        String s = "exec web.spClsTimr '" + id + "' ";
        try
        {
            ArrayList<DBRow> b = new DB().getalldata(s);
            if (b.size() > 0)
            {
                for (DBRow a : b)
                {
                    String nm = a.getval("nm");

                    int seq = a.getint("seqid");
                    int repcnt = a.getint("repcnt");
                    int recdurant = a.getint("recdurant");
                    int isset = a.getint("isset");
                    String cl = a.getval("col");
                    int attngr = a.getint("attngrabsec");
                    int tostore = a.getint("tostore");
                    int toshow = a.getint("toshow");


                    Timer c = new Timer(nm, seq, repcnt, recdurant, isset, cl, attngr, tostore, toshow);
                    lsttm.add(c);

                }
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            msg = "Error getting cls:" + e.getMessage();
            return false;
        }

    }
}
