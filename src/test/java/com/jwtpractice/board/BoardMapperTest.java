package com.jwtpractice.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.jwtpractice.board.domain.Board;
import com.jwtpractice.board.mapper.BoardMapper;

@SpringBootTest
@Transactional
public class BoardMapperTest {
	
	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@BeforeEach
	private void init() {
		jdbcTemplate.execute("alter table `board` auto_increment = 1");
	}

	@Test
	public void save() {
		// given
		
		Board board = new Board(null, "title", "me", "this is a test.");
		
		//when
		
		mapper.save(board);
		
		//then
		
		assertThat(mapper.findBoardById(1L).getTitle()).isEqualTo("title");
	}
	
	@Test 
	public void findAll() {
		//given
		Board board1 = new Board(null, "title1", "me", "test content");
		Board board2 = new Board(null, "title1", "me", "test content");
		Board board3 = new Board(null, "title1", "me", "test content");
		Board board4 = new Board(null, "title1", "me", "test content");
		
		//when
		mapper.save(board1);
		mapper.save(board2);
		mapper.save(board3);
		mapper.save(board4);
		
		
		// then
		
		assertThat(mapper.findAll().size()).isEqualTo(4);
	}
	
	@Test
	public void modify() {
		//given
		Board board = new Board(null, "initial title", "initial writer", "initial content");
		mapper.save(board);
		//when
		Board mod = new Board(null, "modified title", "modified writer", "modified content");
		mod.setBid(1L);
		mapper.modifyBoard(mod);
		//then
		assertThat(mapper.findBoardById(1L).getTitle()).isEqualTo("modified title");
	}
	
	@Test
	public void delete() {
		//given
		Board board = new Board(null, "", "", "");
		mapper.save(board);
		//when
		mapper.deleteBoard(1L);
		
		//then
		assertThat(mapper.findAll()).isEmpty();
	}
	
	
}
