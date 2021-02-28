package com.javaserv.javaserv;

import java.util.HashMap;

public class DBRow {
    HashMap<String, String> lst = new HashMap<String, String>();

    public int getint(String fld){
        int a = 0;
        if (lst.containsKey(fld)){
            String b = lst.get(fld);
            try{
                a = Integer.parseInt(b);
            }
            catch(Exception e){a = 0;}
        }
        return a;
    }
    public String getval(String fld){
        String a = "";
        if (lst.containsKey(fld)){
            a = lst.get(fld);
        }
        return a;
    }
    public boolean storeval(String fld, String vl){
        // if there is already such fld then ignore
        if (lst.containsKey(fld)){
            return false;
        }
        else{
            lst.put(fld, vl);
        }
        return true;
    }

}
