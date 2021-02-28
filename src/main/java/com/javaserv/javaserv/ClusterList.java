package com.javaserv.javaserv;
//import java.util.HashMap;
import java.util.*;

public class ClusterList extends user {
    public List<Cluster> lstClust = new ArrayList<Cluster>();

    public List<clstime> clstimlst = new ArrayList<clstime>();
    public Cluster onecls;
    public boolean getcls(int clsid)
    {
        String s = "exec web.spClsGet '" + clsid + "', '" + t + "'";
        try
        {
            ArrayList<DBRow> b = new DB().getalldata(s);
            if (b.size() > 0)
            {
                if (clsid == 0)
                {
                    for (DBRow a : b)
                    {
                        int id = a.getint("id");
                        String nm = a.getval("name");
                        int tostore = a.getint("tostore");
                        int tp = a.getint("tp");
                        Cluster c = new Cluster(id, nm, tp, tostore);
                        lstClust.add(c);
                    }
                }
                else
                {
                    int id = b.get(0).getint("id");
                    String nm = b.get(0).getval("name");
                    int tp = b.get(0).getint("tp");
                    onecls = new Cluster(id, nm, tp, 0);
                    onecls.gettrms();
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
    public int savedata(int clsid, String clsname, int tp, int clstostore, String data)
    {

        int res = -1;
        String s = "exec web.spTmrSave '" + t + "', '" + clsid + "', '" +
                clsname + "', '" + tp + "', '" + clstostore + "', '" + data + "' ";
        try
        {
            res = new DB().getfirstint(s);

        }
        catch (Exception e)
        {
            res = -3;
        }
        return res;
    }
    public int savedone(int id, int trmid, int dur, String dsc)
    {

        int res = -1;
        String s = "exec web.spStoreDone '" + t + "', '" + id + "', '" + trmid + "', '" + dur + "',  '" + dsc + "' ";
        try
        {
            res = new DB().getfirstint(s);

        }
        catch (Exception e)
        {
            res = -3;
        }
        return res;
    }

    public int deletecls(int clsid)
    {
        int res = -1;
        String s = "exec web.spTmrDeleteCls '" + t + "', '" + clsid +  "' ";
        try
        {
            res = new DB().getfirstint(s);

        }
        catch (Exception e)
        {
            res = -3;
        }
        return res;
    }
    public int saveclstime(String dt)
    {
        int res = -1;
        String s = "exec web.spClsSaveTime  '" + t + "', '" + dt + "' ";
        try
        {
            res = new DB().getfirstint(s);

        }
        catch (Exception e)
        {
            res = -3;
        }
        return res;
    }

    public boolean getallclstimes()
    {
        String s = "exec web.spClsGetTime '" + t + "' ";
        try
        {
            ArrayList<DBRow> b = new DB().getalldata(s);
            if (b.size() > 0)
            {

                for (DBRow a : b)
                {
                    int clsid = a.getint("id");
                    int code = a.getint("code");
                    String dt = a.getval("dt");
                    dt = dt.split(" ")[0];
                    String name = a.getval("name");
                    String tm = a.getval("tm");
                    int tostore = a.getint("tostore");
                    int tp = a.getint("tp");
                    clstime c = new clstime(clsid, name, code, dt, tm, tostore, tp);
                    clstimlst.add(c);
                }
            }

            return true;
        }
        catch (Exception e)
        {
            msg = "Error getting cls:" + e.getMessage();
            return false;
        }
    }
    public boolean savetimelist(String lst)
    {
        int b = 0;
        String s = "exec web.spClsSaveTime '" + t + "', '" + lst + "' ";
        try
        {
            b = new DB().getfirstint(s);
            if (b == 1)
            {
                msg = "List is saved.";
                return true;
            }
            else
            {
                msg = "Database error.";
                return false;
            }
        }
        catch (Exception e)
        {
            msg = "Error getting cls:" + e.getMessage();
            return false;
        }

    }




    public ClusterList(String t){
        super(t);
    }

    public String test(){
        return "testing";
    }
}
