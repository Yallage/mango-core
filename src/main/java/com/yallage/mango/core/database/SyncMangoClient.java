package com.yallage.mango.core.database;

import com.google.gson.Gson;
import com.yallage.mango.core.config.Configuring;
import com.yallage.mango.core.data.Config;
import com.yallage.mango.core.interfaces.MangoClient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SyncMangoClient implements MangoClient {
    // json 解析器
    Gson gson;
    // 数据库配置文件
    Config config = Configuring.getConfig();

    public SyncMangoClient(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void create(String database, String collection, Object data) {
        MongodbConnection.connections.get(config.getDatabases().get(database))
                .getDatabase(database)
                .getCollection(collection)
                .insertOne(Document.parse(gson.toJson(data)));
    }

    @Override
    public void update(String database, String collection, Map<String, Object> index, Object data) {
        MongodbConnection.connections.get(config.getDatabases().get(database))
                .getDatabase(database)
                .getCollection(collection)
                .updateMany(Document.parse(gson.toJson(index)), Document.parse(gson.toJson(data)));
    }

    @Override
    public void delete(String database, String collection, Map<String, Object> index) {
        MongodbConnection.connections.get(config.getDatabases().get(database))
                .getDatabase(database)
                .getCollection(collection)
                .deleteMany(Document.parse(gson.toJson(index)));
    }

    @Override
    public <T> List<T> read(String database, String collection, Map<String, Object> index, Class<T> type) {
        List<T> list = new ArrayList<>();
        // 获取数据库连接
        MongodbConnection.connections.get(config.getDatabases().get(database))
                .getDatabase(database)
                .getCollection(collection)
                // 转换 index 为 Document
                .find(Document.parse(gson.toJson(index)))
                .iterator()
                .forEachRemaining(document -> {
                    // 将 document 转换回对象
                    list.add(gson.fromJson(document.toJson(), type));
                });
        return list;
    }

    @Override
    public void createMany(String database, String collection, List<Object> data) {
        List<Document> documents = new ArrayList<>();
        data.forEach(item -> documents.add(Document.parse(gson.toJson(item))));
        MongodbConnection.connections.get(config.getDatabases().get(database))
                .getDatabase(database)
                .getCollection(collection)
                .insertMany(documents);
    }

    @Override
    public void updateOne(String database, String collection, Map<String, Object> index, Object data) {
        MongodbConnection.connections.get(config.getDatabases().get(database))
                .getDatabase(database)
                .getCollection(collection)
                .updateOne(Document.parse(gson.toJson(data)), Document.parse(gson.toJson(data)));
    }

    @Override
    public void deleteOne(String database, String collection, Map<String, Object> index) {
        MongodbConnection.connections.get(config.getDatabases().get(database))
                .getDatabase(database)
                .getCollection(collection)
                .deleteOne(Document.parse(gson.toJson(index)));
    }
}
