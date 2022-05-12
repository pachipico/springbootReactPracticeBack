package com.jwtpractice.board.dto;

import com.jwtpractice.board.domain.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {
	private Long bid;
	private String title;
	private String writer;
	private String content;
	
	public Board toEntity () {
		return new Board(bid, title, writer, content);
	}
}
