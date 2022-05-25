package com.jwtpractice.board.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtpractice.board.dto.BoardRequestDto;
import com.jwtpractice.board.dto.BoardResponseDto;
import com.jwtpractice.board.service.BoardService;
import com.jwtpractice.response.result.CommonResult;
import com.jwtpractice.response.result.ListResult;
import com.jwtpractice.response.result.SingleResult;
import com.jwtpractice.response.service.ResponseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardController {

	private final BoardService boardService;
	
	private final ResponseService responseService;
	
	@GetMapping("/boards")
	public ListResult<BoardResponseDto> boards () {
		return responseService.getListResult(boardService.findAll());
	}
	
	@PostMapping("/board")
	public SingleResult<BoardResponseDto> registerBoard(@RequestBody BoardRequestDto boardRequestDto){
		log.debug("BoardController boardDto >>>> {}", boardRequestDto);
		return responseService.getSingleResult(boardService.save(boardRequestDto));
	}
	
	@GetMapping("/board/{bid}")
	public CommonResult findById(@PathVariable("bid") Long bid){
		BoardResponseDto board = boardService.findBoardById(bid);
		return board != null ? responseService.getSingleResult(boardService.findBoardById(bid)) : responseService.getFailResult();
	}
	
	@PutMapping("/board/{bid}")
	public SingleResult<Long> modifyBoard(@PathVariable("bid") Long bid , @RequestBody BoardRequestDto boardRequestDto){	
		
		return responseService.getSingleResult(boardService.modifyBoard(boardRequestDto));
	}
	
	@DeleteMapping("/board/{bid}")
	public SingleResult<Long> deleteBoard(@PathVariable("bid") Long bid){
		return responseService.getSingleResult(boardService.deleteBoard(bid));
	}
	
	
}
