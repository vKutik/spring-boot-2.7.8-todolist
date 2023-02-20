package com.example.test.controller;

import com.example.test.dto.BoardRequestDto;
import com.example.test.dto.BoardResponseDto;
import com.example.test.dto.TaskRequestDto;
import com.example.test.dto.TaskResponseDto;
import com.example.test.dto.mapper.BoardMapperDto;
import com.example.test.dto.mapper.TaskMapperDto;
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
    private final TaskMapperDto taskMapperDto;
    private final BoardMapperDto boardMapperDto;

    @GetMapping("/{id}")
    public BoardResponseDto getByIdBoard(@PathVariable Long id) {
        return boardMapperDto.toResponse(boardService.getBoardById(id));
    }

    @GetMapping()
    public List<BoardResponseDto> findAll() {
        Board board = new Board();
        return boardService.getAllBoards()
            .stream()
            .map(boardMapperDto::toResponse)
            .collect(Collectors.toList());
    }

    @PostMapping
    public BoardResponseDto createBoard(@RequestBody @Valid BoardRequestDto boardRequestDto) {
        return boardMapperDto.toResponse(
            boardService.insert(boardMapperDto.toModel(boardRequestDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoardById(id);
    }

    @PutMapping("/{id}")
    public BoardResponseDto updateBoard(@PathVariable Long id,
        @RequestBody BoardRequestDto boardRequestDto) {
        Board board = boardMapperDto.toModel(boardRequestDto);
        board.setId(id);
        return boardMapperDto.toResponse(boardService.insert(board));
    }

    @PostMapping("/{id}/task")
    public BoardResponseDto addTaskForBoard(@PathVariable Long id,
        @RequestBody TaskRequestDto taskRequestDto) {
        return boardMapperDto.toResponse(
            boardService.addTaskToBoard(id, taskMapperDto.toModel(taskRequestDto)));
    }

    @PutMapping("/{boardId}/task/{taskId}")
    public TaskResponseDto updateTaskFromBoard(@PathVariable Long boardId,
        @PathVariable Long taskId,
        @RequestBody TaskRequestDto taskRequestDto) {
        return taskMapperDto.toResponse(
            boardService.updateTaskFromBoard(boardId, taskId,
                taskMapperDto.toModel(taskRequestDto)));
    }

    @DeleteMapping("/{boardId}/task/{taskId}")
    public void deleteTask(@PathVariable Long boardId, @PathVariable Long taskId) {
        boardService.deleteTaskFromBoard(boardId, taskId);
    }

    @GetMapping("/{boardId}/task/{taskId}")
    public TaskResponseDto getTaskByIdFromBoard(@PathVariable Long boardId,
        @PathVariable Long taskId) {
        return taskMapperDto.toResponse(boardService.getTaskByIdFromBoard(boardId, taskId));
    }

    @GetMapping("/{id}/task")
    public List<TaskResponseDto> getAllTasksFromBoard(@PathVariable Long id) {
        return boardService.getAllTaskFromBoard(id).stream().map(taskMapperDto::toResponse)
            .collect(Collectors.toList());
    }
}
