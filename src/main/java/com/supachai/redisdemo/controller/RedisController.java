package com.supachai.redisdemo.controller;

import com.supachai.redisdemo.models.RedisRequest;
import com.supachai.redisdemo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/redis")
public class RedisController {


    private final RedisService redisService;

    @Autowired
    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }


    @GetMapping(path = "/get/{key}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(
            @PathVariable("key") String key) {

        return ResponseEntity.status(HttpStatus.OK).body(redisService.getDataFromRedis(key));
    }


    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@Validated @RequestBody RedisRequest request) {
        redisService.saveToRedis(request);
        return ResponseEntity.status(HttpStatus.OK).body("insert");
    }

    @DeleteMapping(value = "/delete/{key}")
    public ResponseEntity<?> delete(@PathVariable String key) {
        redisService.deleteDataFromRedis(key);
        return ResponseEntity.status(HttpStatus.OK).body("delete");
    }

}
