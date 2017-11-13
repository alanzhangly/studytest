package com.alan.todo;

/**
 *
 * @author Ke Zhang
 * @date 2017/10/24
 */
public interface Frontier {

    /**
     * 获取下一个待操作的URL
     * @return
     * @throws Exception
     */
    CrawlUrl getNext()throws Exception;

    /**
     * URL入队
     * @param url
     * @return
     * @throws Exception
     */
    boolean putUrl(CrawlUrl url) throws Exception;
    //public boolean visited(CrawlUrl url);
}
