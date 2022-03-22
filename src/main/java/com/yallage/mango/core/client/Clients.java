package com.yallage.mango.core.client;

import com.mongodb.MongoClient;
import com.yallage.mango.core.interfaces.Database;

import java.util.Map;

public class Clients {
    // 数据库连接 数据库配置数据 - mongodb client
    public static Map<Database, MongoClient> connections = null;
}
