package com.yallage.mango.core.database;

import com.mongodb.MongoClient;
import com.yallage.mango.core.data.Database;

import java.util.Map;
import java.util.concurrent.ExecutorService;

public class MongodbConnection {
    // 线程池 用于异步执行
    public static ExecutorService executors = null;
    // 数据库连接 数据库配置数据 - mongodb client
    public static Map<Database, MongoClient> connections = null;
}
