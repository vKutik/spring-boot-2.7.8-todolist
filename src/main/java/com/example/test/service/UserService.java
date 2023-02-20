package com.example.test.service;


import com.example.test.model.User;
import java.util.List;

public interface UserService {

    User save(User user);

    void delete(User user);

    User getByUsername(String username);

    List<User> findAll();
}
