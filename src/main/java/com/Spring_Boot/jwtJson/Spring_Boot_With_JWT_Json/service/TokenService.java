package com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class TokenService
{
    public static final String Token_Secret = "sfdgseyg1023";

    public String createToken(ObjectId userid)
    {

        try{
            // Random Generating String Using with Token Secret. We are using HMAC256 Algorithms to Generate the token.
            Algorithm algorithm = Algorithm.HMAC256(Token_Secret);

            // We are Using claims of UserId and Created Date using Date Object.
            String token = JWT.create()
                    .withClaim("userId",userid.toString())
                    .withClaim("createdAt",new Date())
                    .sign(algorithm);
            return token;
        }
        catch(UnsupportedEncodingException exception)
        {
            exception.printStackTrace();
        }
        catch (JWTCreationException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    public String getUserIdFromToken(String token)
    {
        try{
            // Random Generating String Using with Token Secret. We are using HMAC256 Algorithms to Generate the token.
            Algorithm algorithm = Algorithm.HMAC256(Token_Secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT.getClaim("userId").asString();
        }
        catch(UnsupportedEncodingException exception)
        {
            exception.printStackTrace();
        }
        catch (JWTCreationException exception)
        {
            exception.printStackTrace();
        }
        return null;
    }

    public boolean isTokenValid(String token)
    {
        String userId = this.getUserIdFromToken(token);
        return userId != null;
    }


}
