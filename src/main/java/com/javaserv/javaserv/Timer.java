package com.javaserv.javaserv;

public class Timer {
    public String nm ;
    public int seqid ;
    public int repcnt ;
    public int recdur ;
    public int isset ;
    public String col ;
    public int attngrbsec ;
    public int tostore ;
    public int toshow ;
    public Timer(){}
    public Timer(String nmin, int seqin, int repcntin, int repdurin, int issetin, String colin, int attnsec, int tostorin, int toshowin)
    {
        nm = nmin;
        seqid = seqin;
        repcnt = repcntin;
        recdur = repdurin;
        isset = issetin;
        col = colin;
        attngrbsec = attnsec;
        tostore = tostorin;
        toshow = toshowin;
    }
    private String gethidstring()
    {
        String s;
        s = "trmnameseq" + seqid + ":" + nm + ";trmrepcnt" + seqid + ":" + repcnt + ";trmdur" + seqid + ":" + recdur + "trmend";
        return s;
    }
    public void settest(){
        nm = "Name";
        seqid = 20;
        repcnt = 25;
        recdur = 30;
        isset = 1;
        col ="Color";
        attngrbsec = 36;
        tostore = 2;
        toshow = 4;
    }

}
