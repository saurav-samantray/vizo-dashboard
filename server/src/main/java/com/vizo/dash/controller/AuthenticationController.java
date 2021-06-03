package com.vizo.dash.controller;

import com.vizo.dash.config.TokenProvider;
import com.vizo.dash.model.User;
import com.vizo.dash.payload.request.LoginPayload;
import com.vizo.dash.pojo.AuthToken;
import com.vizo.dash.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/token")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity login(HttpServletRequest request,  @RequestBody LoginPayload loginPayload, HttpSession session) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginPayload.getUsername(),
                        loginPayload.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        //Setting userId at session level
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        User u = userService.findOne(userDetail.getUsername());
        request.getSession().setAttribute("userId", u.getId());
        request.getSession().setAttribute("userName", userDetail.getUsername());

        //Initiate Connection for all active datasources


        return ResponseEntity.ok(new AuthToken(token,authentication.getName()));
    }

}