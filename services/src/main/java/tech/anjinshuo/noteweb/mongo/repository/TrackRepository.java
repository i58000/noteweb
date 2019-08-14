package tech.anjinshuo.noteweb.mongo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import tech.anjinshuo.noteweb.mongo.entity.Track;

public interface TrackRepository extends MongoRepository<Track, Long> {

    
}