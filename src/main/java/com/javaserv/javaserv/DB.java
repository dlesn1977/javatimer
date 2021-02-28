package com.javaserv.javaserv;
import java.sql.*;
import java.util.*;

public class DB {
    ArrayList<DBRow> table = new ArrayList<DBRow>();
    List<String> ts;
    Connection cn;
    String msg;
    public DB(){// this is a changedrr
        try{
            cn = DriverManager.getConnection(
                    "jdbc:sqlserver://W7050-ALVIN;databaseName=TestMDB",
                    "testuser",
                    "testuser"
            );

        }
        catch (Exception e){
        }
    }
    public String getfirststring(String s){
        String i = "";
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(s);
            rs.next() ;
            i = rs.getString("res");
        }
        catch(Exception e){
            i = e.getMessage();
        }
        return i;
    }
    public String test(){
        String t = "exec [web].[spTest]";
        String i = "";
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(t);
            rs.next() ;
            i = rs.getString("ts");
        }
        catch(Exception e){
            i = e.getMessage();
        }
        return i;
    }

    public int getfirstint(String s){
        int i = 0;
        try{
            i = Integer.parseInt(getfirststring(s));
        }
        catch(Exception e){
            i = 0;
        }
        return i;
    }

    public ArrayList<DBRow> getalldata(String s){
        ArrayList<DBRow> lst = new ArrayList<DBRow>();
        try{
            Statement st = cn.createStatement();
            List<String> nmlst = new ArrayList<String>();
            ResultSet rs = st.executeQuery(s);
            ResultSetMetaData mdt = rs.getMetaData();

            for (int i = 1; i<mdt.getColumnCount()+1; i++){
                nmlst.add(mdt.getColumnName(i));
            }
            while (rs.next()){
                DBRow rw = new DBRow();
                nmlst.forEach ((t)->{
                    String val = "";
                    try{
                          val = rs.getString(t);
                        }
                    catch(Exception e){
                        val = "";
                    }
                    rw.storeval(t, val);

                });
                lst.add(rw);
            }
        }
        catch(Exception e){msg = e.getMessage(); }
        return lst;
    }

}

