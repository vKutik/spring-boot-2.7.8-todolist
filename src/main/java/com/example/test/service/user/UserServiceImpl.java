package com.example.test.service.user;

import static org.springframework.security.core.userdetails.User.withUsername;

import com.example.test.model.Board;
import com.example.test.model.Role;
import com.example.test.model.User;
import com.example.test.service.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        return (User) userRepository.save(user);
    }

    @Override
    public User update(User user, Long id) {
        User userFromDb = getById(user.getId());
        userFromDb.setUsername(user.getUsername());
        userFromDb.setPassword(user.getPassword());
        userFromDb.setRoles(user.getRoles());
        return userFromDb;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    @Override
    public User getById(Long id) {
        return userRepository.getUserById(id)
            .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userRepository.findAll());
    }

}
