package com.client.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServeiceController {
    @GetMapping("/getMsg")
    public String getMsg(){
        return "msg";
    }
}
