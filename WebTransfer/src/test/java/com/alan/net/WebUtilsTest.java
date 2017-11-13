package com.alan.net;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by Ke Zhang on 2017/10/23.
 */

public class WebUtilsTest {

    @Test
    public void testGet(){
        try {
            String url = "http://www.baidu.com";
            String result = WebUtils.doGet(url,null);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
