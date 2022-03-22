package com.yallage.mango.core.client;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.yallage.mango.core.config.MangoBukkitConfiguring;
import com.yallage.mango.core.interfaces.Config;
import com.yallage.mango.core.interfaces.MangoClient;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MangoSyncClient implements MangoClient {
    // json 解析器
    Gson gson;
    // 数据库配置文件
    Config config = MangoBukkitConfiguring.getConfig();

    public MangoSyncClient(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void create(String database, String collection, Object data) {
        this.collections(database, collection).insertOne(Document.parse(gson.toJson(data)));
    }

    @Override
    public void update(String database, String collection, Map<String, Object> index, Object data) {
        this.collections(database, collection).updateMany(Document.parse(gson.toJson(index)), Document.parse(gson.toJson(data)));
    }

    @Override
    public void delete(String database, String collection, Map<String, Object> index) {
        this.collections(database, collection).deleteMany(Document.parse(gson.toJson(index)));
    }

    @Override
    public <T> List<T> read(String database, String collection, Map<String, Object> index, Class<T> type) {
        List<T> list = new ArrayList<>();
        // 获取数据库连接
        this.collections(database, collection)
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
        this.collections(database, collection).insertMany(documents);
    }

    @Override
    public void updateOne(String database, String collection, Map<String, Object> index, Object data) {
        this.collections(database, collection).updateOne(Document.parse(gson.toJson(data)), Document.parse(gson.toJson(data)));
    }

    @Override
    public void deleteOne(String database, String collection, Map<String, Object> index) {
        this.collections(database, collection).deleteOne(Document.parse(gson.toJson(index)));
    }

    public MongoCollection<Document> collections(String database, String collection) {
        return Clients.connections.get(config.getDatabases().get(database))
                .getDatabase(database)
                .getCollection(collection);
    }
}
