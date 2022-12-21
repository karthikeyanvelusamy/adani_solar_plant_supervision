package com.kovaitech.adani.solar.supervision.service;

import com.kovaitech.adani.solar.supervision.bean.LoginRequest;
import com.kovaitech.adani.solar.supervision.bean.LoginResponse;
import com.kovaitech.adani.solar.supervision.bean.User;
import com.kovaitech.adani.solar.supervision.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    UserRepo userRepo;


    public LoginResponse login(LoginRequest loginReq) {
        boolean status = true;
        String responseMsg = "Success";

        Optional<User> userOptional = userRepo.findByUsername(loginReq.getUsername());
        if (userOptional.isPresent()) {
            status = loginReq.getPassword().equals(userOptional.get().getPassword());
        } else {
            responseMsg = "There is no user with the username " + loginReq.getUsername() + " ";
            status = false;
        }

        return LoginResponse.builder()
                .loginStatus(status).responseMsg(responseMsg).build();
    }

}
