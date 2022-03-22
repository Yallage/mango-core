package com.yallage.mango.core.server;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.yallage.mango.core.client.Clients;
import com.yallage.mango.core.interfaces.Config;
import com.yallage.mango.core.log.MangoBukkitLogger;

public class MangoServer {
    public static void load(Config config) {
        config.getDatabases().forEach(
                (name, database) -> {
                    MongoClient client = new MongoClient(database.getHost(), database.getPort());
                    MongoCredential.createCredential(database.getUsername(),
                            database.getDatabase(),
                            database.getPassword().toCharArray());
                    Clients.connections.put(database, client);
                    MangoBukkitLogger.info("链接到数据库 " + name + " 成功");
                }
        );
    }
}
