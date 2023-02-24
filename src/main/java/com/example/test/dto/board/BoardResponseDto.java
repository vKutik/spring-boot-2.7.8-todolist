package com.example.test.dto.board;

import com.example.test.model.Board;
import com.example.test.model.Task;
import com.example.test.model.User;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String name;
    private List<Task> tasks;

    private String username;
    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.name = board.getName();
        this.tasks = board.getTasks();
        this.username = board.getUser().getUsername();

    }

}
