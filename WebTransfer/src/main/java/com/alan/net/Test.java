package com.alan.net;

import java.io.IOException;

/**
 * Created by Ke Zhang on 2018/1/9.
 */
public class Test {
    public static void main(String[] args){
        String url = "http://192.168.3.86:8080/aio/hello";
        try {
            String response = WebUtils.doGet(url, null);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
