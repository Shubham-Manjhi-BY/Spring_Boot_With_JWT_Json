package com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class TokenService {
    public static final String Token_Secrete = "always_put_random_string_like_mkk9313";

    public String createToken(ObjectId userId){
        try {
            //Random Generating String Using with Token Secret.
            //We are using HMAC256 Algo. to generate the Token
            Algorithm algorithm = Algorithm.HMAC256(Token_Secrete);

            // We are using claims of Id and Created Date using Data Object.
            String token = JWT.
                    create().
                    withClaim("userId",userId.toString()).
                    withClaim("createdAt",new Date()).
                    sign(algorithm);
            return token;
        }
        catch(UnsupportedEncodingException exception){
            exception.printStackTrace();
        }
        catch (JWTCreationException exception){
            exception.printStackTrace();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }



    public String getUserIdFromToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(Token_Secrete);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            System.out.println(decodedJWT.getClaim("userId").asString());
            return decodedJWT.getClaim("userId").asString();
        }
        catch(UnsupportedEncodingException exception){
            exception.printStackTrace();
        }
        catch(JWTDecodeException exception){
            //exception.printStackTrace();
            System.out.println("Wrong Token/ Token is Expired.");
        }

        return null;
    }


    public boolean isTokenValid(String token){
        String userId = this.getUserIdFromToken(token);
        return userId != null;
    }
}