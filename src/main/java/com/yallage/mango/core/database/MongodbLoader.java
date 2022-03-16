package com.yallage.mango.core.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.yallage.mango.core.config.Configuring;
import com.yallage.mango.core.log.MangoLogger;

import java.util.ArrayList;
import java.util.List;

public class MongodbLoader {
    public static void load() {
        // 加载 Mongodb Client
        Configuring.getConfig().getDatabases().forEach(
                (databaseName, database) -> {
                    MongoClient client = new MongoClient(database.getHost(), database.getPort());
                    MongoCredential credential = MongoCredential.createCredential(database.getUsername(),
                            database.getDatabase(),
                            database.getPassword().toCharArray());
                    Connection.connections.put(database, client);
                    MangoLogger.info("链接到数据库 " + database.getName());
                }
        );

        // 收集所有的数据库
        Connection.connections.keySet().forEach(
                database -> {
                    Connection.databases.add(database.getDatabase());
                }
        );

        // 收集所有的 mongodb 集合
        Connection.connections.forEach((database, client) -> {
            List<String> collections = new ArrayList<>();
            client.getDatabase(database.getDatabase())
                    .listCollectionNames()
                    .iterator()
                    .forEachRemaining(collections::add);
            Connection.collections.put(database.getDatabase(), collections);
        });
    }
}
