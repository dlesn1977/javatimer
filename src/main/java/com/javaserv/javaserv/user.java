package com.javaserv.javaserv;

public class user {
    String t;
    String login;
    String pass;
    String msg;
    int userid ;
    public user(String tin){
        t= tin;
    }
    public user(String log, String ps){
        login = log;
        pass = ps;
    }
    public String userlogin(){
        String s = "exec [web].[spLogin] '" + login + "', '" + pass+ "'";
        String r = t = new DB().getfirststring(s);
        return r;
    }
    public boolean verified(){
        return true;
    }
}
