package com.yallage.mango.core.database;

import com.yallage.mango.core.config.Configuring;
import com.yallage.mango.core.data.Config;
import com.yallage.mango.core.data.Database;
import com.yallage.mango.core.interfaces.DatabaseClient;
import org.bson.Document;

public class MangoClient implements DatabaseClient {
    @Override
    public void create(String database, String collection, Object data) {
        Config config = Configuring.getConfig();
        Database db = config.getDatabases().get(database);
        Connection.connections.get(db).getDatabase(database).getCollection(collection).insertOne((Document) data);
    }

    @Override
    public <T> T read(String database, String collection, T data) {
        return null;
    }

    @Override
    public void update(String database, String collection, String id, Object data) {

    }

    @Override
    public void delete(String database, String collection, Object data) {

    }
}
