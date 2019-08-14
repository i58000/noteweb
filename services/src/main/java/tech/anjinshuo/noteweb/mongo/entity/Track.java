package tech.anjinshuo.noteweb.mongo.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;

@Data
@Document
public class Track {

    public static Track fromBeacon(JSONObject jobj) {
		// TODO Auto-generated constructor stub
		Track entity = new Track();
    	try {
    		entity.date = new Date((long) jobj.get("date"));
    		entity.action = (String) jobj.get("action");
    		entity.type = (String) jobj.get("type");
    		entity.ip = (String) jobj.get("ip");
    		entity.value = (String) jobj.get("value");
    		entity.isMobile = (boolean) jobj.get("isMobile");
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
		return entity;
	}
    
    public static Track fromLogin(String type, String ip, String value, Boolean isMobile) {
		// TODO Auto-generated constructor stub
		Track entity = new Track();
    	try {
    		entity.date = new Date();
    		entity.action = "login";
    		entity.type = type;
    		entity.ip = ip;
    		entity.value = value;
    		entity.isMobile = isMobile;
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
		return entity;
	}

	@Id
    private ObjectId _id = new ObjectId();
    private String action;  
    private String type;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;
    private String ip;
    private String value;
    private boolean isMobile;
}

