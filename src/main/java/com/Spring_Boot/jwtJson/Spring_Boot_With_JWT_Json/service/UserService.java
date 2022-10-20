package com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.service;


import com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.model.User;
import com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService
{
    private UserRepository userRepository;
    private TokenService tokenService;

    //    public UserService(UserRepository userRepository, TokenService tokenService) {
    //        this.userRepository = userRepository;
    //        this.tokenService = tokenService;
    //    }

    @Autowired
    public UserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }


    public User getUser(ObjectId userId)
    {
        Optional<User> optionalUser = userRepository.findById(userId);
        return  optionalUser.orElseGet(optionalUser::get);
    }

    public String saveUser(User user)
    {
        User savedUser = userRepository.save(user);
        return "{" +
                "\"message\":"+"\"Successfully Created User\",\n"+
                "\"data\":"+savedUser+",\n"+
                "\"token\":\""+tokenService.createToken(savedUser.getId())+"\"}";
    }

}
