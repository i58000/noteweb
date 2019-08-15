package tech.anjinshuo.noteweb.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import tech.anjinshuo.noteweb.mongo.entity.Note;

public interface NoteRepository extends MongoRepository<Note, Long> {
	
//    List<Note> findAll();
    Note findBy_id(String id);
    List<Note> findAllByUsername(String username);
}