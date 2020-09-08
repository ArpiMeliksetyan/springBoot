package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/interconnect")
public class InterconnectController {


    @GetMapping
    public ResponseEntity test() {
        return ResponseEntity.ok(Collections.singletonMap("success", true));
    }
}
