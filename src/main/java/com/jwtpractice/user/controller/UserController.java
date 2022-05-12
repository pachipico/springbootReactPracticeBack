package com.jwtpractice.user.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtpractice.response.result.CommonResult;
import com.jwtpractice.response.result.ListResult;
import com.jwtpractice.response.result.SingleResult;
import com.jwtpractice.response.service.ResponseService;
import com.jwtpractice.user.dto.UserRequestDto;
import com.jwtpractice.user.dto.UserResponseDto;
import com.jwtpractice.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	private final ResponseService responseService;
	
	@GetMapping("/users")
	public ListResult<UserResponseDto> users(){
		return responseService.getListResult(userService.findAllUser());
	}
	
	@GetMapping("/user/id/{userId}")
	public SingleResult<UserResponseDto> findByUserId(@PathVariable("userId") Long userId){
		return responseService.getSingleResult(userService.findByUserId(userId));
	}
	
	@GetMapping("/user/email/{email}")
	public SingleResult<UserResponseDto> findByEmail(@PathVariable("email")String email){
		return responseService.getSingleResult(userService.findByEmail(email));
	}
	
	@GetMapping("/user/name/{name}")
	public ListResult<UserResponseDto> findByName(@PathVariable("name") String name) {
		return responseService.getListResult(userService.findByName(name));
	}
	
	@PutMapping("/user/{userId}")
	public SingleResult<Long> modify(@PathVariable("userId") Long userId, @RequestBody UserRequestDto userRequestDto){
		return responseService.getSingleResult(userService.modify(userId, userRequestDto));
	}
	
	@DeleteMapping("/user/{userId}")
	public CommonResult delete (@PathVariable("userId") Long userId) {
		userService.deleteUser(userId);
		return responseService.getSuccessResult();
	}
}
