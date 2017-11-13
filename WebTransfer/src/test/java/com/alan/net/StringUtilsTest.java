package com.alan.net;

import org.junit.Test;

/**
 * Created by Ke Zhang on 2017/10/23.
 */
public class StringUtilsTest {

    @Test
    public void areNotEmptyTest(){
        boolean re = StringUtils.areNotEmpty("xax","  ");
        System.out.println(re);
    }
}
