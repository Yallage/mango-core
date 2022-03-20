package com.yallage.mango.core.database;

import com.mongodb.MongoClient;
import com.yallage.mango.core.data.Database;

import java.util.Map;

public class MongodbConnection {
    // 数据库连接 数据库配置数据 - mongodb client
    public static Map<Database, MongoClient> connections = null;
}
