package com.jwtpractice.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class Board {
	private Long bid;
	private String title;
	private String writer;
	private String content;
	private String regAt;
	private String modAt;
	private int hit;
	public Board(Long bid,String title, String writer, String content) {
		this.bid = bid;
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
	
	
}
