package com.javaserv.javaserv;
import com.mongodb.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import java.util.*;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MongoDB {
	MongoDatabase db;
	MongoCollection<Document> col;
	
	public MongoDB(String dbname, String colin) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		db = mongoClient.getDatabase(dbname);
		col = db.getCollection(colin);
	}
	public String getstring(BasicDBObject fnd, String fld) {
		BasicDBObject v = getonevalue(fnd, fld);
		String r = (String) v.get(fld);
		return r;
	}

	public int getint(BasicDBObject fnd, String fld) {	
		String r = getstring(fnd, fld);
		int i = Integer.parseInt(r);
		return i;
	}
	public BasicDBObject getonevalue(BasicDBObject fnd, String fld) {
		List<BasicDBObject> one = getrecords(fnd, true);
		BasicDBObject r = new BasicDBObject();
		if (!one.isEmpty()) {
			BasicDBObject b = one.get(0);
			if (b.containsKey(fld)) {
				r.put(fld, trimdata(b.get(fld)));
			}
			else {
				r.put(fld, "no data");
			}
		}
		r.put(fld, "no data");
		return r;
	}
	// getting rid of mongo special formating object id, date, etc.
	public String trimdata(Object i) {
		String r = "";
		if (i instanceof String) {r = i.toString();}
		if (i instanceof JSONObject) {
			JSONObject b = (JSONObject)i;
			//r = (String)i.get(b.keySet()[0]);
			for (Object fld: b.keySet()) {
				r = (String)b.get(fld);
				return r;
			}
		}
		return r;
	}
	public List<BasicDBObject> getrecords(BasicDBObject qry, boolean onlyfirst) {
		List<BasicDBObject>  docs = new ArrayList<BasicDBObject>();
		JSONParser parser = new JSONParser();
		//FindIterable<Document> lst =  col.find(jsontodb(qry));
		FindIterable<Document> lst =  col.find(qry);
		//FindIterable<Document> lst =  col.find();
		// retrieving Documents - convert to BasicDBObject by constructor
		System.out.print(lst);
		if (!(lst==null)) {
			if (onlyfirst) {
				//lst.first().toJson()
				try {
					//docs.add((BasicDBObject) parser.parse(lst.first().toJson()) );
					docs.add(new BasicDBObject(lst.first()));
				}
				catch(Exception e) {
					System.out.print(e);
				}
			}
			else {
				for(Document d : lst) {
					try {
						docs.add((BasicDBObject) parser.parse(d.toJson()));
					}
					catch(Exception e) {}
				}
			}
		}
		return docs;
	}
	public boolean updateonerecord( BasicDBObject qr, BasicDBObject updt) {
		Document u = new Document("$set", new Document(updt));
		Document fnd = new Document(qr);
		UpdateResult a = col.updateOne(fnd, u );
		if (a.getModifiedCount()>1)
			return true;
		else
			return false;
	}
	public BasicDBObject getcurdateobj(String fld) {
		Date now = new Date();
		BasicDBObject dt = new BasicDBObject(fld, now);
		return dt;
		
	}
	
	public BasicDBObject jsontodb(BasicDBObject jsn) {
		BasicDBObject b =  new BasicDBObject();
	    for (Object keyStr : jsn.keySet()) {
	        Object keyvalue = jsn.get(keyStr);
	        b.put(keyStr.toString(), keyvalue);
	    }
		return b;
	}
	
}
