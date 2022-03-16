package com.yallage.mango.core.database;

import com.google.gson.Gson;
import com.yallage.mango.core.config.Configuring;
import com.yallage.mango.core.data.Database;
import com.yallage.mango.core.interfaces.DatabaseClient;
import org.bson.Document;

public class MangoClient implements DatabaseClient {
    @Override
    public void create(String database, String collection, Object data) {
        Database db = Configuring.getConfig().getDatabases().get(database);
        Connection.connections.get(db).getDatabase(database)
                .getCollection(collection)
                .insertOne(Document.parse(new Gson().toJson(data)));
    }

    @Override
    public <T> T read(String database, String collection, T data) {
        Database db = Configuring.getConfig().getDatabases().get(database);
        Document documents = Connection.connections.get(db).getDatabase(database)
                .getCollection(collection)
                .findOneAndDelete(Document.parse(new Gson().toJson(data)));
        //TODO
        return null;
    }

    @Override
    public void update(String database, String collection, String id, Object data) {
        Database db = Configuring.getConfig().getDatabases().get(database);
        Connection.connections.get(db).getDatabase(database)
                .getCollection(collection)
                .insertOne(Document.parse(new Gson().toJson(data)));
    }

    @Override
    public void delete(String database, String collection, Object data) {
        Database db = Configuring.getConfig().getDatabases().get(database);
        Connection.connections.get(db).getDatabase(database)
                .getCollection(collection)
                .deleteOne(Document.parse(new Gson().toJson(data)));
    }
}
