package com.jwtpractice.response.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jwtpractice.response.CommonResponse;
import com.jwtpractice.response.result.CommonResult;
import com.jwtpractice.response.result.ListResult;
import com.jwtpractice.response.result.SingleResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResponseService {

	public <T> SingleResult<T> getSingleResult(T data){
		log.debug("DATA : {}", data);
		SingleResult<T> result = new SingleResult<T>();
		result.setData(data);
		setSuccessResult(result);
		return result;
	}
	
	public <T> ListResult<T> getListResult(List<T> list){
		ListResult<T> result = new ListResult<T>();
		result.setList(list);
		setSuccessResult(result);
		return result;
	}
	
	public CommonResult getSuccessResult() {
		CommonResult result =  new CommonResult();
		setSuccessResult(result);
		return result;
	}
	
	public CommonResult getFailResult() {
		CommonResult result = new CommonResult();
		setFailResult(result);
		return result;
	}
	
	public void setSuccessResult (CommonResult result) {
		result.setSuccess(true);
		result.setCode(CommonResponse.SUCCESS.getCode());
		result.setMsg(CommonResponse.SUCCESS.getMsg());
	}
	
	public void setFailResult(CommonResult result) {
		result.setSuccess(false);
		result.setCode(CommonResponse.FAIL.getCode());
		result.setMsg(CommonResponse.FAIL.getMsg());
	}
}