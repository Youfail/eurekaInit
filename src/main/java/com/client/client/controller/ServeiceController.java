package com.client.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ServeiceController {
    @Autowired
    @Lazy
    private StringRedisTemplate stringRedisTemplate;

    private final static Logger LOGGER = LoggerFactory.getLogger(ServeiceController.class);

    @GetMapping("/getMsg")
    public String getMsg(HttpServletRequest request){
        LOGGER.info("---begin pushing info---");
        stringRedisTemplate.opsForList().rightPush("queen1","wait_pop");
        return "msg";
    }
}
