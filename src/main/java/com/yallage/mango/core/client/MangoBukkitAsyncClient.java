package com.yallage.mango.core.client;

import com.google.gson.Gson;
import com.yallage.mango.core.MangoBukkitCore;
import com.yallage.mango.core.event.MangoBukkitAsyncEvent;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MangoBukkitAsyncClient extends MangoSyncClient {
    public MangoBukkitAsyncClient(Gson gson) {
        super(gson);
    }

    public String read(String database, String collection, Map<String, Object> index) {
        // 生成 async id
        String asyncId = UUID.randomUUID().toString();
        Bukkit.getScheduler().runTaskAsynchronously(MangoBukkitCore.getInstance(), () -> {
            List<String> list = new ArrayList<>();
            // 获取数据库连接
            Clients.connections.get(config.getDatabases().get(database))
                    .getDatabase(database)
                    .getCollection(collection)
                    // 转换 index 为 Document
                    .find(Document.parse(gson.toJson(index)))
                    .iterator()
                    .forEachRemaining(document -> {
                        // 将 document 转换回对象
                        list.add(document.toJson());
                    });
            // 触发事件
            MangoBukkitAsyncEvent event = new MangoBukkitAsyncEvent(asyncId, gson, list);
            Bukkit.getPluginManager().callEvent(event);
        });
        // 将数据放入事件
        return asyncId;
    }
}
