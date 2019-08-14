package tech.anjinshuo.noteweb.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import tech.anjinshuo.noteweb.mongo.entity.User;

public interface UserRepository extends MongoRepository<User, Long> {

    List<User> findByUsername(String name);
    
}