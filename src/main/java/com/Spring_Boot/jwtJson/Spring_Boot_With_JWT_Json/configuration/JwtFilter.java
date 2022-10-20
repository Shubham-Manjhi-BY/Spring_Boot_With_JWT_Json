package com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.configuration;

import com.Spring_Boot.jwtJson.Spring_Boot_With_JWT_Json.service.TokenService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
public class JwtFilter extends GenericFilterBean
{

    private TokenService tokenService;


    public JwtFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }



    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;

        String token = httpServletRequest.getHeader("Authorization");
        if("OPTIONS".equalsIgnoreCase(httpServletRequest.getMethod()))
        {
            httpServletResponse.sendError(HttpServletResponse.SC_OK,"success");
            return;
        }
        if(allowRequestWithoutToken(httpServletRequest))
        {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req,res);
        }
        else
        {
            ObjectId userId = new ObjectId(tokenService.getUserIdFromToken(token));
            httpServletRequest.setAttribute("userId",userId);
            filterChain.doFilter(req,res);
        }
    }

    public boolean allowRequestWithoutToken(HttpServletRequest httpServletRequest)
    {
        System.out.println(httpServletRequest.getRequestURI());
        if(httpServletRequest.getRequestURI().contains("/saveUser")) return true;
        return false;
    }

}
