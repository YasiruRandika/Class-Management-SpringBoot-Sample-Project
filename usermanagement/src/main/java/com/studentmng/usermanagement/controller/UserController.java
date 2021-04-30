package com.studentmng.usermanagement.controller;

import javax.annotation.security.PermitAll;

import com.studentmng.usermanagement.model.AuthenticationRequest;
import com.studentmng.usermanagement.model.AuthenticationResponse;
import com.studentmng.usermanagement.service.StudentServiceImpl;
import com.studentmng.usermanagement.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PermitAll
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @PermitAll
    @RequestMapping(value="/authenticate", method=RequestMethod.POST)
    public ResponseEntity<?> createaAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        } catch (Exception e) {
            throw new Exception("Incorrect username or password ", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
