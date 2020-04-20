package com.pppfreak.Hired.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pppfreak.Hired.Entity.TextileEmployee;
import com.pppfreak.Hired.Entity.UserEmployee;
import com.pppfreak.Hired.SpringApplicationContext;
import com.pppfreak.Hired.form.request.LoginRequest;
import com.pppfreak.Hired.form.request.UserEmployeeRequestForm;
import com.pppfreak.Hired.service.EmployeeService;
import com.pppfreak.Hired.service.UserEmployeeService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest cred = new ObjectMapper().readValue(request.getInputStream(),
                                                                  LoginRequest.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            cred.getEmail()
                            ,cred.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
        {

        String userEmail = ((UserEntity)authResult.getPrincipal()).getUsername();
        String token = Jwts.builder().setSubject(userEmail).claim("authorities",authResult.getAuthorities())
                                     .setIssuedAt(new Date(System.currentTimeMillis()))
                                     .setExpiration(new Date(System.currentTimeMillis()+SecurityConstrants.EXPERATION_TIME))
                                     .signWith(SignatureAlgorithm.HS512,SecurityConstrants.SECRET_TOKEN)
                                     .compact();

        UserEmployeeService userEmployeeService =
                    (UserEmployeeService) SpringApplicationContext.getBean("userEmployeeServiceImpl");
        UserEmployee userEmployee = userEmployeeService.getUserByEmail(userEmail);

        response.addHeader(SecurityConstrants.HEADER_STRING,SecurityConstrants.TOKEN_PREFIX+token);
        response.addHeader("UserId", userEmployee.getUserId());
    }
}
