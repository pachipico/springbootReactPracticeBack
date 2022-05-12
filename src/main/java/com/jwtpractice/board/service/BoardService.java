package com.jwtpractice.board.service;

import java.util.List;

import com.jwtpractice.board.dto.BoardRequestDto;
import com.jwtpractice.board.dto.BoardResponseDto;

import lombok.extern.slf4j.Slf4j;


public interface BoardService {
	BoardResponseDto save(BoardRequestDto boardRequestDto);
	List<BoardResponseDto> findAll();
	BoardResponseDto findBoardById(Long bid);
	List<BoardResponseDto> findBoardByTitle(String title);
	List<BoardResponseDto> findBoardByWriter(String writer);
	Long modifyBoard(BoardRequestDto boardRequestDto);
	Long deleteBoard(Long bid);
}
