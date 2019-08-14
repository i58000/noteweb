package tech.anjinshuo.noteweb.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import tech.anjinshuo.noteweb.mongo.entity.Cate;

public interface CateRepository extends MongoRepository<Cate, Long> {
	
    List<Cate> findAll();
    Cate findBy_id(String id);
    
}