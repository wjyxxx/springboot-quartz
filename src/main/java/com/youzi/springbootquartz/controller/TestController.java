package com.youzi.springbootquartz.controller;

import com.youzi.springbootquartz.bean.QuartzJobManager;
import com.youzi.springbootquartz.bean.User;
import com.youzi.springbootquartz.service.UserService;
import com.youzi.springbootquartz.jobimpl.QuartzJobImpl;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TestController {

    @Autowired
    QuartzJobManager quartzJobManager;

    @Autowired
    private UserService userService;

    @Autowired
    private User user;

    @GetMapping("/add")
    public void add() {
        //任务名称
        String name = "task-add";
        //任务组名称
        String groupName = "task";
        //cron表达式
        String cron = "0/5 * * * * ?";
        //需要给任务携带的参数
        /*Map<String, Object> map = new HashMap<>();
        map.put("name", "张三");
        map.put("sex", "0");*/
        user.setUsername("张三");
        user.setPassword("0");

        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("className", userService.getClass());
        jobDataMap.put("methodName", "insertUser");
        //设置状态码:标志传递参数的类型
        jobDataMap.put("parameters", user);


        quartzJobManager.addJob(QuartzJobImpl.class, name, groupName, cron, jobDataMap);
    }

    @GetMapping("/del")
    public void del() {
        String name = "task-del";
        String groupName = "task";
        quartzJobManager.deleteJob(name, groupName);
    }

}
