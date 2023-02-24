package com.example.test.controller;

import com.example.test.dto.board.BoardRequestDto;
import com.example.test.dto.board.BoardResponseDto;
import com.example.test.dto.task.TaskRequestDto;
import com.example.test.dto.task.TaskResponseDto;
import com.example.test.model.Board;
import com.example.test.service.board.BoardService;
import com.example.test.service.user.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    private final UserService userService;

    @GetMapping("/{id}")
    public BoardResponseDto getByIdBoard(@PathVariable Long id) {
        return new BoardResponseDto(boardService.getBoardById(id));
    }

    @GetMapping
    public List<BoardResponseDto> findAll() {
        return boardService
            .getAllBoards()
            .stream()
            .map(BoardResponseDto::new)
            .collect(Collectors.toList());
    }

    @PostMapping
    public BoardResponseDto createBoard(@RequestBody @Valid BoardRequestDto boardRequestDto) {

        return new BoardResponseDto(boardService.create(boardRequestDto.toModel()));
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoardById(id);
    }

    @PutMapping("/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id,
        @RequestBody BoardRequestDto boardRequestDto) {
        Board board = boardRequestDto.toModel();
        board.setId(id);
        return new BoardResponseDto(boardService.create(board));
    }

    @PostMapping("/{id}/task")
    public BoardResponseDto addTaskForBoard(@PathVariable Long id,
        @RequestBody TaskRequestDto taskRequestDto) {
        return new BoardResponseDto(
            boardService.addTaskToBoard(id, taskRequestDto.toModel()));
    }

    @PutMapping("/{boardId}/task/{taskId}")
    public TaskResponseDto updateTaskFromBoard(@PathVariable Long boardId,
        @PathVariable Long taskId,
        @RequestBody TaskRequestDto taskRequestDto) {
        return new TaskResponseDto(
            boardService.updateTaskFromBoard(boardId, taskId,
                taskRequestDto.toModel()));
    }

    @DeleteMapping("/{boardId}/task/{taskId}")
    public void deleteTask(@PathVariable Long boardId, @PathVariable Long taskId) {
        boardService.deleteTaskFromBoard(boardId, taskId);
    }

    @GetMapping("/{boardId}/task/{taskId}")
    public TaskResponseDto getTaskByIdFromBoard(@PathVariable Long boardId,
        @PathVariable Long taskId) {
        return new TaskResponseDto(boardService.getTaskByIdFromBoard(boardId, taskId));
    }

    @GetMapping("/{id}/task")
    public List<TaskResponseDto> getAllTasksFromBoard(@PathVariable Long id) {
        return boardService
            .getAllTaskFromBoard(id)
            .stream()
            .map(TaskResponseDto::new)
            .collect(Collectors.toList());
    }
}
