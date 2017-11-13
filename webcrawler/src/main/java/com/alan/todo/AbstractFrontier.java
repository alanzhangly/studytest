package com.alan.todo;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.*;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 抽象类封装 Berkeley DB 的操作
 *
 * Created by Ke Zhang on 2017/10/24.
 */
public abstract class AbstractFrontier {
    private Environment env;
    private static final String CLASS_CATALOG = "java_class_catalog";
    protected StoredClassCatalog javaCatalog;
    //用来存储序列化对象的类
    protected Database catalogdatabase;
    //用来存储类信息的数据库
    protected Database database;

    public AbstractFrontier(String homeDirectory) throws DatabaseException,
            FileNotFoundException {
        // 打开env
        System.out.println("Opening environment in: " + homeDirectory);
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setTransactional(true);
        envConfig.setAllowCreate(true);
        env = new Environment(new File(homeDirectory), envConfig);
        // 设置DatabaseConfig
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setTransactional(true);
        dbConfig.setAllowCreate(true);
        // 打开 日志
        catalogdatabase = env.openDatabase(null, CLASS_CATALOG, dbConfig);
        javaCatalog = new StoredClassCatalog(catalogdatabase);
        // 设置DatabaseConfig
        DatabaseConfig dbConfig0 = new DatabaseConfig();
        dbConfig0.setTransactional(true);
        dbConfig0.setAllowCreate(true);
        // 打开 数据库
        database = env.openDatabase(null, "URL", dbConfig);
    }
    //关闭数据库，关闭环境
    public void close() throws DatabaseException {
        database.close();
        javaCatalog.close();
        env.close();
    }
    //put 方法
    protected abstract void put(Object key,Object value);
    //get 方法
    protected abstract Object get(Object key);
    //delete 方法
    protected abstract Object delete(Object key);
}
