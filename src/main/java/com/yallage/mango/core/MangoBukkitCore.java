package com.yallage.mango.core;

import com.yallage.mango.core.config.MangoBukkitConfiguring;
import com.yallage.mango.core.data.Config;
import com.yallage.mango.core.database.MongodbConnection;
import com.yallage.mango.core.database.MongodbLoader;
import com.yallage.mango.core.log.MangoBukkitLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ConcurrentHashMap;

public class MangoBukkitCore extends JavaPlugin {
    static MangoBukkitCore instance;

    public MangoBukkitCore() {
        instance = this;
    }

    public static MangoBukkitCore getInstance() {
        return instance;
    }


    @Override
    public void onLoad() {
        MangoBukkitLogger.info("芒果核 YaMangoCore 加载中...");
        MangoBukkitConfiguring.loadConfig();
    }

    @Override
    public void onEnable() {
        MangoBukkitLogger.info("芒果核 YaMangoCore 检查中...");
        Config config = MangoBukkitConfiguring.getConfig();

        MangoBukkitLogger.info("初始化资源");
        // Mongodb 客户端池
        MongodbConnection.connections = new ConcurrentHashMap<>();
        MangoBukkitLogger.info("初始化资源完成");

        // 初始化数据库
        MangoBukkitLogger.info("初始化数据库");
        MongodbLoader.load(config);
        MangoBukkitLogger.info("初始化数据库完成");

        // 报告加载结果
        MangoBukkitLogger.info("共加载 " + MongodbConnection.connections.size() + " 个 mongodb 链接");
        MangoBukkitLogger.info("检查完成");
    }

    @Override
    public void onDisable() {
        MangoBukkitLogger.info("芒果核 YaMangoCore 关闭中...");
        // 清理 mongodb 链接
        MongodbConnection.connections.forEach((key, value) -> {
            value.close();
        });
    }
}
