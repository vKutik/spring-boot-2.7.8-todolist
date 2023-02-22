package com.example.test.controller;

import com.example.test.dto.BoardRequestDto;
import com.example.test.dto.BoardResponseDto;
import com.example.test.dto.TaskRequestDto;
import com.example.test.dto.TaskResponseDto;
import com.example.test.model.Board;
import com.example.test.service.BoardService;
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

    @GetMapping("/{id}")
    public BoardResponseDto getByIdBoard(@PathVariable Long id) {
        return new BoardResponseDto(boardService.getBoardById(id));
    }

    @GetMapping()
    public List<BoardResponseDto> findAll() {
        Board board = new Board();
        return boardService
            .getAllBoards()
            .stream()
            .map(b -> new BoardResponseDto(board))
            .collect(Collectors.toList());
    }

    @PostMapping
    public BoardResponseDto createBoard(@RequestBody @Valid BoardRequestDto boardRequestDto) {
        return new BoardResponseDto(boardService.insert(boardRequestDto.toModel()));
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
        return new BoardResponseDto(boardService.insert(board));
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
