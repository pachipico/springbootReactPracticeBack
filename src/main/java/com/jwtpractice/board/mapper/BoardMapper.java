package com.jwtpractice.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jwtpractice.board.domain.Board;

@Mapper
public interface BoardMapper {
	
	int save(Board board);
	List<Board> findAll();
	Board findLastSaved();
	Board findBoardById(Long bid);
	List<Board> findBoardByTitle(String title);
	List<Board> findBoardByWriter(String writer);
	int modifyBoardHit(Long bid);
	int modifyBoard(Board board);
	int deleteBoard(Long bid);
}
