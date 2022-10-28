package com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.repository;

import com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    @Query("{email:\"?0\"}")
    public List<User> getUsersByEmail(String email);
}
