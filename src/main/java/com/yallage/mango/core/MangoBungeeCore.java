package com.yallage.mango.core;

import com.yallage.mango.core.config.MangoBungeeConfiguring;
import com.yallage.mango.core.data.Config;
import com.yallage.mango.core.database.MongodbConnection;
import com.yallage.mango.core.database.MongodbLoader;
import com.yallage.mango.core.log.MangoBungeeLogger;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.concurrent.ConcurrentHashMap;

public class MangoBungeeCore extends Plugin {
    static MangoBungeeCore instance;

    public MangoBungeeCore() {
        instance = this;
    }

    public static MangoBungeeCore getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        MangoBungeeLogger.info("芒果核 YaMangoCore 加载中...");
        MangoBungeeConfiguring.loadConfig();
    }

    @Override
    public void onEnable() {
        MangoBungeeLogger.info("芒果核 YaMangoCore 检查中...");
        Config config = MangoBungeeConfiguring.getConfig();

        MangoBungeeLogger.info("初始化资源");
        // Mongodb 客户端池
        MongodbConnection.connections = new ConcurrentHashMap<>();
        MangoBungeeLogger.info("初始化资源完成");

        // 初始化数据库
        MangoBungeeLogger.info("初始化数据库");
        MongodbLoader.load(config);
        MangoBungeeLogger.info("初始化数据库完成");

        // 报告加载结果
        MangoBungeeLogger.info("共加载 " + MongodbConnection.connections.size() + " 个 mongodb 链接");
        MangoBungeeLogger.info("检查完成");
    }

    @Override
    public void onDisable() {
        MangoBungeeLogger.info("芒果核 YaMangoCore 关闭中...");
        // 清理 mongodb 链接
        MongodbConnection.connections.forEach((key, value) -> {
            value.close();
        });
    }
}
