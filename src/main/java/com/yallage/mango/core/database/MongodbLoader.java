package com.yallage.mango.core.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.yallage.mango.core.data.Config;
import com.yallage.mango.core.log.MangoBukkitLogger;

public class MongodbLoader {
    public static void load(Config config) {
        config.getDatabases().forEach(
                (name, database) -> {
                    MongoClient client = new MongoClient(database.getHost(), database.getPort());
                    MongoCredential.createCredential(database.getUsername(),
                            database.getDatabase(),
                            database.getPassword().toCharArray());
                    MongodbConnection.connections.put(database, client);
                    MangoBukkitLogger.info("链接到数据库 " + name + " 成功");
                }
        );
    }
}
