package com.jwtpractice.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwtpractice.board.dto.BoardRequestDto;
import com.jwtpractice.board.dto.BoardResponseDto;
import com.jwtpractice.board.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper mapper;

	@Transactional
	@Override
	public BoardResponseDto save(BoardRequestDto boardRequestDto) {
		mapper.save(boardRequestDto.toEntity());
		
		return new BoardResponseDto(mapper.findLastSaved());
	}

	@Transactional
	@Override
	public List<BoardResponseDto> findAll() {
		
		return mapper.findAll().stream().map(b -> new BoardResponseDto(b)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public BoardResponseDto findBoardById(Long bid) {
		mapper.modifyBoardHit(bid);
		return new BoardResponseDto(mapper.findBoardById(bid));
	}

	@Transactional
	@Override
	public List<BoardResponseDto> findBoardByTitle(String title) {

		return mapper.findBoardByTitle(title).stream().map(b -> new BoardResponseDto(b)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public List<BoardResponseDto> findBoardByWriter(String writer) {

		return mapper.findBoardByWriter(writer).stream().map(b -> new BoardResponseDto(b)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public Long modifyBoard(BoardRequestDto boardRequestDto) {
		mapper.modifyBoard(boardRequestDto.toEntity());
		return boardRequestDto.getBid();
	}

	@Transactional
	@Override
	public Long deleteBoard(Long bid) {
		mapper.deleteBoard(bid);
		return bid;
	}

}
