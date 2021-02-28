package com.javaserv.javaserv;

import com.mongodb.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class contrl {
    @RequestMapping("/")
    @Autowired
    public String index(){
        String t = new DB().test();
        //t = "test spring";
        return t;
    }
    /*
    @RequestMapping(value = "/testmongo", method = GET, produces = "application/json")
    //@Autowired
    public JSONObject indexmongo(){
    	MongoDB m = new MongoDB("timer", "users");
    	JSONObject q = new JSONObject();
    	q.put("pass", "pass");
    	List<BasicDBObject> a = m.getrecords(q, false);
    	System.out.print(a);
        //t = "test spring";String
        return a.get(0);
    }
    */
    
    @RequestMapping(value = "/mlogin", method = POST, produces = "application/json")
    //@Autowired
    public ResponseEntity<String> mlogin(String username, String pass){
    	
    	MongoDB m = new MongoDB("timer", "users");
    	BasicDBObject q = new BasicDBObject();
    	if (!(pass==null))
    		{q.put("pass", pass);}
    	if (!(username==null))
    		{q.put("user", username);}
    	List<BasicDBObject> a = m.getrecords(q, true);
    	if (a.size()>0) {
    		BasicDBObject data = a.get(0);
    		BasicDBObject upd = m.getcurdateobj("lastdt");
    		m.updateonerecord(q, upd);
    		System.out.print(data.toString());
    		return new ResponseEntity<String>(data.toString(), getheaders("json"), HttpStatus.OK);
    	}
    	return new ResponseEntity<String>("Error: User not found", getheaders("json"), HttpStatus.OK);
    	
    };
    
    
    
    
    @RequestMapping(value = "/mloginbad", method = POST, produces = "application/json")
    //@Autowired
    public ResponseEntity<String> mloginbad(String username, String pass){
    	
    	MongoDB m = new MongoDB("timer", "users");
    	BasicDBObject q = new BasicDBObject();
    	if (!(pass==null))
    		{q.put("pass", pass);}
    	if (!(username==null))
    		{q.put("user", username);}
    	BasicDBObject a = m.getonevalue(q, "usert");
    	//System.out.print(a.toString());
        //t = "test spring";
    	return new ResponseEntity<String>(a.toString(), getheaders("json"), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/teststring", method = GET)
    @Autowired
    public String indextest(){
        String t = "return test";// = new DB().test();
        //t = "test spring";
        return t;
    }
    /*
    @RequestMapping(value = "/gettimer", method = GET)
    @ResponseBody
    public HashMap<String, String> gettimer(){
        HashMap<String, String> t = new HashMap<>();
        t.put("test1", "val1");pass
        t.put("test2", "val2");
        t.put("test3", "val3");
        t.put("test4", "val4");
        t.put("test5", "val5");
        return t;
    }*/
    @RequestMapping(value = "/gettimer", method = GET)
    @ResponseBody
    public String gettimer() {
        ObjectMapper mp = new ObjectMapper() ;
        Timer t = new Timer();
        List<Timer> tlst = new ArrayList<Timer>();
        t.settest();
        tlst.add(t);
        String a = "";
        try{
            a = mp.writeValueAsString(tlst);
        }
        catch(JsonProcessingException e){}

        return a;
    }
    @RequestMapping(value = "/alltimers", method = GET, produces = "application/json")
    @CrossOrigin(origins = "http://localhost:4200")

    public String alltimers() {
        //Timer t = new Timer();
        //t.settest();
        //tlst.add(t);
        //Cluster cls = new Cluster();


        ObjectMapper mp = new ObjectMapper() ;
        //if (true) return "returning ";
        List<test> tlst = new ArrayList<test>();
        tlst.add(new test("Myname", 12));
        tlst.add(new test("Noname", 17));


        String a = "";
        try{
            a = mp.writeValueAsString(tlst);
        }
        catch(JsonProcessingException e){a = e.getMessage();}

        return a;
    }

    /*
        cluster management
     */

    @RequestMapping(value = "/getclusters", method = POST, produces = "application/json")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> getclusters(String usert, int clusterid ) {


        ClusterList a = new ClusterList(usert);
        a.getcls(clusterid);
        List <Cluster> ar =  a.lstClust;
        ObjectMapper mp = new ObjectMapper() ;
        String s = "";
        try{
            s = mp.writeValueAsString(ar);
        }
        catch(JsonProcessingException e){s = e.getMessage();}
        final HttpHeaders hd = new HttpHeaders();
        hd.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(s, hd, HttpStatus.OK);
    }

/*
    getting all timers from cluster id
 */
    @RequestMapping(value = "/gettimers", method = POST, produces = "application/json")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> gettimers(String usert, int clusterid ) {


        ClusterList a = new ClusterList(usert);
        a.getcls(clusterid);
        ArrayList <Timer> ar =  a.onecls.lsttm;
        ObjectMapper mp = new ObjectMapper() ;
        String s = "";
        try{
            s = mp.writeValueAsString(ar);
        }
        catch(JsonProcessingException e){s = e.getMessage();}
        final HttpHeaders hd = new HttpHeaders();
        hd.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(s, hd, HttpStatus.OK);
    }




/*
            USER MANAGEMENT
 */
@RequestMapping(value = "/login", method = POST, produces = "application/json")
@CrossOrigin(origins = "http://localhost:4200")
public ResponseEntity<String> login(String username, String pass) {
    user us = new user(username, pass);
    us.userlogin();
    String usert = us.t;
    ObjectMapper mp = new ObjectMapper() ;
    final HttpHeaders hd = new HttpHeaders();
    hd.setContentType(MediaType.TEXT_HTML);
    try{
        usert = mp.writeValueAsString(usert);
    }
    catch(JsonProcessingException e){usert = e.getMessage();}

    return new ResponseEntity<String>(usert  , hd, HttpStatus.OK);
}

private HttpHeaders getheaders(String kind) {
	final HttpHeaders hd = new HttpHeaders();
	if (kind=="text") {hd.setContentType(MediaType.TEXT_HTML);}
	if (kind=="json") {hd.setContentType(MediaType.APPLICATION_JSON);}
	return hd;
}

}
