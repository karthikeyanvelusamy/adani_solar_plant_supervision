package com.kovaitech.adani.solar.supervision.service;

import com.kovaitech.adani.solar.supervision.bean.User;
import com.kovaitech.adani.solar.supervision.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void upsert(User user) {

        if( userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("User Can not created! Username already exists!");
        }
        userRepo.save(user);
    }

    public void deleteUser(String username) {
        userRepo.deleteByUsername(username);
    }

    public User getUser(String username) {
        Optional<User> userOptional = userRepo.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new RuntimeException("No user found with this username!");
        }

        return userOptional.get();
    }
}
