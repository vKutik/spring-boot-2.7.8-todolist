package com.example.test.service.board;

import com.example.test.model.Board;
import com.example.test.model.Task;
import com.example.test.service.repository.BoardRepository;
import com.example.test.service.task.TaskService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final TaskService taskService;

    public BoardServiceImpl(BoardRepository boardRepository, TaskService taskService) {
        this.boardRepository = boardRepository;
        this.taskService = taskService;
    }

    @Override
    public Board create(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Board update(Board board, Long id) {
        Board boardFromDb = getBoardById(id);
        boardFromDb.setName(board.getName());
        boardFromDb.setTasks(board.getTasks());
        return boardFromDb;
    }

    @Override
    public void deleteBoardById(Long id) {
        boardRepository.deleteById(id);
    }

    @Override
    public Board getBoardById(Long id) {
        return boardRepository.getBoardById(id)
            .orElseThrow(() -> new IllegalArgumentException("Board not found"));
    }

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board addTaskToBoard(Long boardId, Task task) {
        Board board = getBoardById(boardId);
        board.getTasks().add(taskService.create(task));
        return boardRepository.save(board);
    }

    @Override
    public Task getTaskByIdFromBoard(Long boardId, Long taskId) {
        Board board = getBoardById(boardId);
        Optional<Task> tasksOptional = board
            .getTasks()
            .stream()
            .filter(t -> Objects.equals(t.getId(), taskId))
            .findFirst();
        return tasksOptional.orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public Task updateTaskFromBoard(Long boardId, Long taskId, Task task) {
        Task newTask = getTaskByIdFromBoard(boardId, taskId);
        newTask.setName(task.getName());
        newTask.setDescription(task.getDescription());
        newTask.setStatus(task.getStatus());
        return taskService.update(newTask, taskId);
    }

    @Override
    public void deleteTaskFromBoard(Long boardId, Long taskId) {
        taskService.deleteById(getTaskByIdFromBoard(boardId, taskId).getId());
    }

    @Override
    public List<Task> getAllTaskFromBoard(Long boardId) {
        return getBoardById(boardId).getTasks();
    }
}
