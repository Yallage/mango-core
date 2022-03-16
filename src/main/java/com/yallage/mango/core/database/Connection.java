package com.yallage.mango.core.database;

import com.mongodb.client.MongoDatabase;
import com.yallage.mango.core.data.Database;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class Connection {
    // 线程池 用于异步执行
    public static ExecutorService executors = null;
    // 数据库连接 数据库配置数据 - mongodb client
    public static Map<Database, MongoDatabase> connections = null;
    // 快速索引 数据库索引 数据
    public static List<String> databases = null;
    // 快速索引 数据库集合索引 数据库 - 集合
    public static Map<String, List<String>> collections = null;
}
