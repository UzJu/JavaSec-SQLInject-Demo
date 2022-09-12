package com.uzju.dongtai_sql_inject_lab.controller;

import com.uzju.dongtai_sql_inject_lab.entity.User;
import com.uzju.dongtai_sql_inject_lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/mybatis")
public class MyBatisInject {

    @Autowired
    private UserService userService;
    // mybatis xml sql inject ${}
    // 常规的select * from user where id = xx 的注入
    // http://192.168.31.140:8821/mybatis/getUser_inject?id=1+and+sleep(3)
    @RequestMapping(value = "getUser_inject", method = RequestMethod.GET)
    public User GetUser_inject(String id) {
        return userService.getUserInfo_inject(id);
    }

    /**
     * Mybatis xml order by sql注入 ${}
     * http://192.168.31.140:8821/mybatis/getUser_orderby_inject?id=1&getparse=id+and+if(1=1,1,(SELECT(1)FROM(SELECT(SLEEP(2)))test))
     * */
    @RequestMapping(value = "getUser_orderby_inject", method = RequestMethod.GET)
    public User getUserInfo_Orderby_Inject(String id, String getparse) {
        return userService.getUserInfo_Orderby_Inject(id, getparse);
    }

    /**
     * Mybatis xml like sql注入 ${}
     * http://192.168.31.140:8821/mybatis/getUser_like_inject?id=1&username=admin'+and+sleep(3)--+
     * */
    @RequestMapping(value = "getUser_like_inject", method = RequestMethod.GET)
    public User getUserInfo_like_Inject(String id, String username) {
        return userService.getUserInfo_like_Inject(id, username);
    }

    /**
     * Mybatis xml in sql注入 ${}
     * http://192.168.31.140:8821/mybatis/getUser_in_inject?id=1)+and+sleep(3)--+
     * */
    @RequestMapping(value = "getUser_in_inject", method = RequestMethod.GET)
    public User getUserInfo_in_Inject(String id) {
        return userService.getUserInfo_in_Inject(id);
    }
}

