package com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.service;



import com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.model.Movie;
import com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;
    @Autowired
    private TokenService tokenService;

    public List<Movie> getMovies(){
        List<Movie> result = repository.findAll();
        return result;
    }

    public String saveMovie(Movie movie){
        Movie savedMovie = repository.save(movie);
        return "{" +
                "\"message\":"+"\"Successfully Created A Movie\","+
                "\"data\":"+savedMovie+"}";
    }
}
