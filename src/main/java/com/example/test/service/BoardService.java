package com.example.test.service;

import com.example.test.model.Board;
import com.example.test.model.Task;
import java.util.List;

public interface BoardService {

    Board insert(Board board);

    void deleteBoardById(Long id);

    Board getBoardById(Long id);

    List<Board> getAllBoards();

    Board addTaskToBoard(Long boardId, Task task);

    Task getTaskByIdFromBoard(Long boardId, Long taskId);

    Task updateTaskFromBoard(Long boardId, Long taskId, Task task);

    void deleteTaskFromBoard(Long boardId, Long taskId);

    List<Task> getAllTaskFromBoard(Long boardId);
}
