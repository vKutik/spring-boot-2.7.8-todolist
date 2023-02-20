package com.example.test.dto.mapper;

import com.example.test.dto.BoardRequestDto;
import com.example.test.dto.BoardResponseDto;
import com.example.test.model.Board;
import org.springframework.stereotype.Component;

@Component
public class BoardMapperDto implements RequestMapperDto<BoardRequestDto, Board>,
    ResponseMapperDto<BoardResponseDto, Board> {

    @Override
    public Board toModel(BoardRequestDto boardRequestDto) {
        Board board = new Board();
        board.setName(boardRequestDto.getName());
        board.setTasks(boardRequestDto.getTasks());
        return board;
    }

    @Override
    public BoardResponseDto toResponse(Board board) {
        BoardResponseDto boardResponse = new BoardResponseDto();
        boardResponse.setId(board.getId());
        boardResponse.setName(board.getName());
        boardResponse.setTasks(board.getTasks());
        return boardResponse;
    }
}
