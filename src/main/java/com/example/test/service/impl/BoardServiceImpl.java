package com.example.test.service.impl;


import com.example.test.model.Board;
import com.example.test.model.Task;
import com.example.test.repository.BoardRepository;
import com.example.test.service.BoardService;
import com.example.test.service.TaskService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final TaskService taskService;

    @Override
    public Board insert(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public Board getBoardById(Long id) {
        return boardRepository.getReferenceById(id);
    }

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board addTaskToBoard(Long boardId, Task task) {
        Board board = boardRepository.getBoardById(boardId);
        board.getTasks().add(taskService.insert(task));
        return boardRepository.save(board);
    }

    @Override
    public Task getTaskByIdFromBoard(Long boardId, Long taskId) {
        Board board = boardRepository.getBoardById(boardId);
        Optional<Task> taskOptional = board.getTasks()
            .stream()
            .filter(t -> Objects.equals(t.getId(), taskId))
            .findFirst();
        return taskOptional.orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public Task updateTaskFromBoard(Long boardId, Long taskId, Task task) {
        Task newTask = getTaskByIdFromBoard(boardId, taskId);
        newTask.setName(task.getName());
        newTask.setDescription(task.getDescription());
        newTask.setStatus(task.getStatus());
        return taskService.insert(newTask);
    }

    @Override
    public void deleteTaskFromBoard(Long boardId, Long taskId) {
        taskService.deleteById(getTaskByIdFromBoard(boardId, taskId).getId());
    }

    @Override
    public List<Task> getAllTaskFromBoard(Long boardId) {
        return boardRepository.getBoardById(boardId).getTasks();
    }
}
