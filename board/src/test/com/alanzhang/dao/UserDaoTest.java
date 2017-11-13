package com.alanzhang.dao;

import com.alanzhang.domain.TUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Ke Zhang on 2017/11/2.
 */
public class UserDaoTest {

    @Autowired
    private TUserMapper tUserMapper;

    @Test
    public void add(){
        TUser tUser = new TUser();
        tUser.setUserId("111");
        tUser.setUserName("xasxa");
        tUser.setPassword("xxx");
        System.out.println(tUserMapper == null);
        tUserMapper.insert(tUser);
    }
}
