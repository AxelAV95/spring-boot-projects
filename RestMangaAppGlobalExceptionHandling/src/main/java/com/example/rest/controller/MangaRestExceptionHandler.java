package com.example.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.rest.entity.MangaErrorResponse;
import com.example.rest.exceptions.MangaNotFoundException;

@ControllerAdvice
public class MangaRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<MangaErrorResponse> handleException(MangaNotFoundException e){
		MangaErrorResponse error = new MangaErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<MangaErrorResponse>(error, HttpStatus.NOT_FOUND);
		
	}
}
