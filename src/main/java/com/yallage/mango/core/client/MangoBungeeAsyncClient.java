package com.yallage.mango.core.client;

import com.google.gson.Gson;
import com.yallage.mango.core.MangoBungeeCore;
import com.yallage.mango.core.event.MangoBungeeAsyncEvent;
import net.md_5.bungee.api.ProxyServer;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MangoBungeeAsyncClient extends MangoSyncClient {
    public MangoBungeeAsyncClient(Gson gson) {
        super(gson);
    }

    public String read(String database, String collection, Map<String, Object> index) {
        // 生成 async id
        String asyncId = UUID.randomUUID().toString();
        ProxyServer.getInstance().getScheduler().runAsync(MangoBungeeCore.getInstance(), () -> {
            List<String> list = new ArrayList<>();
            // 获取数据库连接
            Clients.connections.get(config.getDatabases().get(database))
                    .getDatabase(database)
                    .getCollection(collection)
                    // 转换 index 为 Document
                    .find(Document.parse(gson.toJsonTree(index, Map.class).toString()))
                    .iterator()
                    .forEachRemaining(document -> {
                        // 将 document 转换回对象
                        list.add(document.toJson());
                    });
            // 触发事件
            MangoBungeeAsyncEvent event = new MangoBungeeAsyncEvent(asyncId, gson, list);
            ProxyServer.getInstance().getPluginManager().callEvent(event);
        });
        // 将数据放入事件
        return asyncId;
    }
}
