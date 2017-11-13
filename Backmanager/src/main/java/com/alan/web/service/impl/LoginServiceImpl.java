package com.alan.web.service.impl;

import com.alan.web.service.ILoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ke Zhang on 2017/10/27.
 */
@Service
public class LoginServiceImpl implements ILoginService {
    @Override
    public Map login(String userName, String password) {
        Map reMap = new HashMap();
        if("admin".equals(userName) && "123".equals(password)){
//            reMap.put();
        }
        return null;
    }
}
