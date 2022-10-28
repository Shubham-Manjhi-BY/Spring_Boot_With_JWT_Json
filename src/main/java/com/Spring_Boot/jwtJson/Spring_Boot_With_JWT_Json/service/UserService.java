package com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.service;


import com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.model.User;
import com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;
    private TokenService tokenService;

    @Autowired
    public UserService(UserRepository repository,TokenService tokenService) {
        this.repository = repository;
        this.tokenService = tokenService;
    }

    public User getUser(ObjectId id){
        Optional<User> result = repository.findById(id);
        return result.orElseGet(result::get);
    }


    public String signUp(User user){
        User savedUser = repository.save(user);
        return "{" +
                "\"message\":"+"\"Successfully Created A User\","+
                "\"data\":"+savedUser+"}";
    }

    public String login(User user){
        List<User> repoUser = repository.getUsersByEmail(user.getEmail());

        if(repoUser.size()==0){

            return "{" +
                    "\"message\":"+"\"Authorization Failed !!!!!!!!!!!\","+
                    "\"data\":"+user+"}";
        }
        //System.out.println(user.getPassword()+" "+repoUser.get(0).getPassword()+" "+user.getPassword().equals(repoUser.get(0).getPassword()));
        if(!user.getPassword().equals(repoUser.get(0).getPassword())){
            return "{" +
                    "\"message\":"+"\"Authorization Failed !!!!!!!!!!!\","+
                    "\"error\":"+"\"Email or Password is not correct\"}";
        }
        return "{" +
                "\"message\":"+"\"Successfully Login In\","+
                "\"data\":"+repoUser.get(0)+","+
                "\"token\":\""+tokenService.createToken(repoUser.get(0).getId())+"\"}";
    }


}