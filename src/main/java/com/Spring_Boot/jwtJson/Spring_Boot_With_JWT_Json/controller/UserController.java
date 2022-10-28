package com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.controller;


import com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.model.User;
import com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/signUp" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public String savingAUser(@RequestBody User user){
        return userService.signUp(user);
    }


    @PostMapping(value = "/login" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginAUser(@RequestBody User user){
        return userService.login(user);
    }


    @GetMapping("/getUser/{id}")
    public User GatUser(@PathVariable("id") ObjectId id)  {
        return  userService.getUser(id);
    }

}
