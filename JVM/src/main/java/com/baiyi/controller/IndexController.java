package com.baiyi.controller;

import com.baiyi.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @Author: BaiYi
 * @Description: 首页控制器
 * @Date: 2022/6/14 23:32
 */
@RestController
public class IndexController {

    @RequestMapping("/user/process")
    public String processUserData() {
        ArrayList<User> users = queryUsers();
        for (User user : users) {
            // 相关业务处理
            System.out.println("user = " + user.toString());
        }
        return "success";
    }

    public ArrayList<User> queryUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            users.add(new User(i, "baiyi"));
        }
        return users;
    }


}
