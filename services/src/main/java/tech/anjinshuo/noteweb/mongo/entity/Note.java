package tech.anjinshuo.noteweb.mongo.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    
    public String get_id() {
		return _id.toString();
    }

}

