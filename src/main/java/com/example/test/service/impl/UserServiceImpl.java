package com.example.test.service.impl;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import com.example.test.service.UserService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return (User) userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }


    public User getByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }


    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(list::add);
        return list;
    }
}
