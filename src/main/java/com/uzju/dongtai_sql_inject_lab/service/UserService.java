package com.uzju.dongtai_sql_inject_lab.service;

import com.uzju.dongtai_sql_inject_lab.entity.User;
import com.uzju.dongtai_sql_inject_lab.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getAllUserInfo(String desc){
        return userMapper.getAllUserInfo(desc);
    }

    public User getUserInfo(int id) {
        return userMapper.getUserInfo(id);
    }

    public User getUserInfo_inject(String id){
        return userMapper.getUserInfo_inject(id);
    }

    public User getUserInfo_Orderby_Inject(String id, String getparse) {
        return userMapper.getUserInfo_Orderby_Inject(id, getparse);
    }

    public User getUserInfo_like_Inject(String id, String username){
        return userMapper.getUserInfo_like_Inject(id, username);
    };

    public User getUserInfo_in_Inject(String id){
        return userMapper.getUserInfo_in_Inject(id);
    }
}

