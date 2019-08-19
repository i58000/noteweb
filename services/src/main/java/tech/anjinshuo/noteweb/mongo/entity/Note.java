package tech.anjinshuo.noteweb.mongo.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Document
public class Note {

    @Id
    private ObjectId _id = new ObjectId();

    @Field
    private String title;
    
    @Field
    private String content;
    
    @Field
    private String username;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date updateDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date creationDate;
    
    public Note(){
    	super();
    	this.creationDate = this.updateDate = new Date();
    }
    
    public String get_id() {
		return _id.toString();
    }
    
    public void setContent(String content){
    	this.content = content;
    	this.updateDate = new Date();
    }
    public void setTitle(String title){
    	this.title = title;
    	this.updateDate = new Date();
    }

}

