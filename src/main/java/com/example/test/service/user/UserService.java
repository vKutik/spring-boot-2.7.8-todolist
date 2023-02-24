package com.example.test.service.user;

import com.example.test.model.Board;
import com.example.test.model.User;
import java.util.List;

public interface UserService {

    User create(User user);

    User update(User user, Long id);
    void delete(User user);

    User getByUsername(String username);

    User getById(Long id);

    List<User> findAll();

    Board addBoardToUser(Long userId, Board board);
}
