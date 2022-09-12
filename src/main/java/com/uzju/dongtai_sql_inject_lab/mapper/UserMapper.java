package com.uzju.dongtai_sql_inject_lab.mapper;

import com.uzju.dongtai_sql_inject_lab.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    User getUserInfo(int id);

    User getAllUserInfo(String desc);

    // mybatis xml sql inject ${}
    // 常规的select * from user where id = xx 的注入
    // http://192.168.0.35:8888/testBoot/getUser_inject?id=1
    User getUserInfo_inject(String id);

    // mybatis xml sql inject ${}
    // 常规的select * from user where id = xx order by xxx
    // http://192.168.0.35:8888/testBoot/getUser_inject?id=1
    User getUserInfo_Orderby_Inject(String id, String getparse);

    User getUserInfo_like_Inject(String id, String username);

    User getUserInfo_in_Inject(String id);
}

