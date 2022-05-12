package com.jwtpractice.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.jwtpractice.board.dto.BoardRequestDto;
import com.jwtpractice.board.dto.BoardResponseDto;
import com.jwtpractice.board.service.BoardService;

@Transactional
@SpringBootTest
public class BoardServiceTest {
	@Autowired
	private BoardService service;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@BeforeEach
	private void init() {
		jdbcTemplate.execute("alter table board auto_increment = 1");
	}
	
	@Test
	public void save() {
		//given
		BoardRequestDto board = new BoardRequestDto(null, "title", "writer", "content");
		
		
		//when
		BoardResponseDto saved = service.save(board);
		
		//then
		
		assertThat(board.getTitle()).isEqualTo(saved.getTitle());
	}
}
