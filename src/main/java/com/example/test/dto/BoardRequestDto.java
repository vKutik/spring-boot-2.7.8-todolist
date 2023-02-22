package com.example.test.dto;

import com.example.test.model.Board;
import com.example.test.model.Task;
import com.sun.istack.NotNull;
import java.util.List;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardRequestDto {

    @NotNull
    @Size(min = 3)
    private String name;
    private List<Task> tasks;

    public Board toModel() {
        Board board = new Board();
        board.setName(name);
        board.setTasks(tasks);
        return board;
    }


}
