package com.kovaitech.adani.solar.supervision.repository;

import com.kovaitech.adani.solar.supervision.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);

}
