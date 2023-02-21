package com.example.test.service.impl;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.example.test.model.Role;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import com.example.test.service.UserService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

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
        return userRepository.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }


    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {;
        User user = getByUsername(username);

        UserBuilder builder =
            withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(Role::getName).toArray(String[]::new));
        return builder.build();
    }
}
