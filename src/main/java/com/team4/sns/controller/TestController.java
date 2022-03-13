package com.team4.sns.controller;

import com.team4.sns.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping(value = "/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity(testService.test(), HttpStatus.OK);
    }
}
