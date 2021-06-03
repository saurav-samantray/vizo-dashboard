package com.vizo.dash.service;

import com.vizo.dash.dto.UserDto;
import com.vizo.dash.model.User;

import java.util.List;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}