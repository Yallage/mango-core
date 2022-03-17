package com.yallage.mango.core.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.yallage.mango.core.config.Configuring;
import com.yallage.mango.core.log.MangoLogger;

public class MongodbLoader {
    public static void load() {
        // 加载 Mongodb Client
        Configuring.getConfig().getDatabases().forEach(
                (name, database) -> {
                    MongoClient client = new MongoClient(database.getHost(), database.getPort());
                    MongoCredential credential = MongoCredential.createCredential(database.getUsername(),
                            database.getDatabase(),
                            database.getPassword().toCharArray());
                    MongodbConnection.connections.put(database, client);
                    MangoLogger.info("链接到数据库 " + name + " 成功");
                }
        );
    }
}
