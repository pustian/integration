package com.wotung.integration.member.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wotung.integration.member.entity.Test;
import com.wotung.integration.member.service.ITestService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(Thread.currentThread().getStackTrace()[1].getClassName());

    @ApiOperation(value = "测试用", notes = "测试用")
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }

    @Autowired
    ITestService testService;

    @ApiOperation(value = "POST method", notes = "POST method")
    @PostMapping("/post")
    @ResponseBody
    public Boolean post(@RequestBody Test test) {
        boolean bool = testService.insert(test);
        return bool;
    }

    @ApiOperation(value = "GET method", notes = "GET method")
    @GetMapping("/get")
    @ResponseBody
    public List<Test> get() {
        return testService.selectList(new EntityWrapper<Test>());
    }
//
//    @ApiOperation(value = "测试用", notes = "测试用")
//    @GetMapping("/hello")
//    @ResponseBody
//    public String hello() {
//        return "hello world";
//    }
//
//    @ApiOperation(value = "测试用", notes = "测试用")
//    @DeleteMapping("/hello")
//    @ResponseBody
//    public String hello() {
//        return "hello world";
//    }
//
//    @ApiOperation(value = "测试用", notes = "测试用")
//    @DeleteMapping("/hello")
//    @ResponseBody
//    public String hello() {
//        return "hello world";
//    }
//    @ApiOperation(value = "测试用", notes = "测试用")
//    @DeleteMapping("/hello")
//    @ResponseBody
//    public String hello() {
//        return "hello world";
//    }
}
