package com.kovaitech.adani.solar.supervision.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kovaitech.adani.solar.supervision.bean.LoginRequest;
import com.kovaitech.adani.solar.supervision.bean.LoginResponse;
import com.kovaitech.adani.solar.supervision.bean.User;
import com.kovaitech.adani.solar.supervision.service.LoginService;
import com.kovaitech.adani.solar.supervision.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(loginService.login(loginRequest));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getLocalizedMessage()).status(500).build();
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> add(@RequestBody User user) {
        try {
            userService.upsert(user);
            return ResponseEntity.ok("Successfully Created User");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getLocalizedMessage()).status(500).build();
        }
    }


    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> delete(@RequestBody String username) {
        try {
            userService.deleteUser(username);
            return ResponseEntity.ok("Successfully Deleted User");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getLocalizedMessage()).status(500).build();
        }
    }


    @RequestMapping(value = "/get/{username}", method = RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getUser(@PathVariable("username") String username) {
        try {
            return  new ResponseEntity<>(new ObjectMapper().writeValueAsString(userService.getUser(username)), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.getLocalizedMessage()).status(500).build();
        }
    }




}
