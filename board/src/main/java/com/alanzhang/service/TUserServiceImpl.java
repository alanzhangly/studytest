package com.alanzhang.service;

import com.alanzhang.core.cache.IRedisOperation;
import com.alanzhang.dao.TUserMapper;
import com.alanzhang.domain.TUser;
import com.alanzhang.util.TinyUUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ke Zhang on 2017/11/2.
 */
@Service
public class TUserServiceImpl {

    @Autowired
    private TUserMapper tUserMapper;

    @Autowired
    private IRedisOperation iRedisOperation;

    public void insertUser(){
        TUser tUser = new TUser();
        String userid = TinyUUIDGenerator.generate();
        tUser.setUserId(userid);
        tUser.setUserName("xasxa");
        tUser.setPassword("xxx");
        System.out.println(tUserMapper == null);
        tUserMapper.insert(tUser);
        iRedisOperation.setValue("lalal",tUser.toString());
    }
}
