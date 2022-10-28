package com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;




@Data // it will take care all CURD
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    private String name;
    private String releaseDate;


    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id +"\""+
                ", \"name\":'" + name + '\'' +
                ", \"releaseDate\":'" + releaseDate + '\''+
                '}';
    }
}