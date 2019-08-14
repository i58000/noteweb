package tech.anjinshuo.noteweb.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import tech.anjinshuo.noteweb.mongo.entity.Foto;

public interface FotoRepository extends MongoRepository<Foto, Long> {

    List<Foto> findByCateId(String cateId);
    Foto findBy_id(String id);

}