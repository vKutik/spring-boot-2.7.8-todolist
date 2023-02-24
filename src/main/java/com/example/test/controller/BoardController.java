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
import org.springframework.security.core.context.SecurityContextHolder;
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

    //boards

    @GetMapping("/{id}")
    public BoardResponseDto getByIdBoard(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new BoardResponseDto(boardService.getBoardByIdAndUsername(id, username));
    }

    @PostMapping
    public BoardResponseDto createBoard(@RequestBody @Valid BoardRequestDto boardRequestDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return new BoardResponseDto(
            boardService.createBoardByUsername(boardRequestDto.toModel(), username));
    }

    @GetMapping
    public List<BoardResponseDto> findAll() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return boardService
            .getAllBoardsByUsername(username)
            .stream()
            .map(BoardResponseDto::new)
            .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boardService.deleteBoardByIdAndUsername(id, username);
    }

    @PutMapping("/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id,
        @RequestBody BoardRequestDto boardRequestDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Board board = boardRequestDto.toModel();
        board.setId(id);

        return new BoardResponseDto(boardService.updateBoardByIdAndUsername(board, id, username));
    }

    // tasks

    @PostMapping("/{id}/task")
    public BoardResponseDto addTaskToBoard(@PathVariable Long id,
        @RequestBody TaskRequestDto taskRequestDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return new BoardResponseDto(boardService
            .addTaskToBoardByIdAndUsername(
                id,
                taskRequestDto.toModel(),
                username));
    }

    @PutMapping("/{boardId}/task/{taskId}")
    public TaskResponseDto updateTaskFromBoard(@PathVariable Long boardId,
        @PathVariable Long taskId,
        @RequestBody TaskRequestDto taskRequestDto) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return new TaskResponseDto(boardService
            .updateTaskByBoardIdAndUsernameFromBoard(boardId, taskId, taskRequestDto.toModel(),
                username));
    }

    @GetMapping("/{boardId}/task/{taskId}")
    public TaskResponseDto getTaskByIdFromBoard(@PathVariable Long boardId,
        @PathVariable Long taskId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return new TaskResponseDto(
            boardService.getTaskByIdAndUsernameFromBoard(boardId, taskId, username));
    }

    @DeleteMapping("/{boardId}/task/{taskId}")
    public void deleteTask(@PathVariable Long boardId, @PathVariable Long taskId) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boardService.deleteTaskFromBoard(boardId, taskId, username);
    }

    @GetMapping("/{id}/task")
    public List<TaskResponseDto> getAllTasksFromBoard(@PathVariable Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return boardService
            .getAllTaskFromBoardByUsername(id,username)
            .stream()
            .map(TaskResponseDto::new)
            .collect(Collectors.toList());
    }

}
