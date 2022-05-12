package com.jwtpractice.board.dto;

import com.jwtpractice.board.domain.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class BoardResponseDto {
	private Long bid;
	private String title;
	private String writer;
	private String content;
	private String regAt;
	private String modAt;
	private int hit;
	public BoardResponseDto(Board board) {
		this.bid = board.getBid();
		this.title = board.getTitle();
		this.writer = board.getWriter();
		this.content = board.getContent();
		this.regAt = board.getRegAt();
		this.modAt = board.getModAt();
		this.hit = board.getHit();
	}
	
	
}
