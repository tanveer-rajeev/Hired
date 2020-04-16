package com.pppfreak.Hired.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pppfreak.Hired.Entity.Employee;
import com.pppfreak.Hired.ServiceImpl.EmployeeServiceImpl;
import com.pppfreak.Hired.SpringApplicationContext;
import com.pppfreak.Hired.request_response_Model.LoginRequestModel;
import com.pppfreak.Hired.service.EmployeeService;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    @Autowired
    EmployeeService employeeService;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestModel cred = new ObjectMapper().readValue(request.getInputStream(),
                                                                  LoginRequestModel.class);

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

        String userEmail = ((User)authResult.getPrincipal()).getUsername();
        String token = Jwts.builder().setSubject(userEmail).claim("authorities",authResult.getAuthorities())
                                     .setIssuedAt(new Date(System.currentTimeMillis()))
                                     .setExpiration(new Date(System.currentTimeMillis()+SecurityConstrants.EXPERATION_TIME))
                                     .signWith(SignatureAlgorithm.HS512,SecurityConstrants.SECRET_TOKEN)
                                     .compact();
        System.out.println(request.getRequestURI());
        EmployeeService employeeService = (EmployeeService) SpringApplicationContext.getBean("employeeServiceImpl");
        Employee employee = employeeService.getEmployee(userEmail);
        response.addHeader(SecurityConstrants.HEADER_STRING,SecurityConstrants.TOKEN_PREFIX+token);
        response.addHeader("UserId",employee.getUserId());
    }
}
