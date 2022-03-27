package com.yallage.mango.core;

import com.yallage.mango.core.client.Clients;
import com.yallage.mango.core.config.MangoBungeeConfiguring;
import com.yallage.mango.core.interfaces.Config;
import com.yallage.mango.core.log.MangoBungeeLogger;
import com.yallage.mango.core.server.MangoBungeeServer;
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
        MangoBungeeLogger.info("芒果核心 YaMangoCore 加载中...");
        MangoBungeeConfiguring.loadConfig();
    }

    @Override
    public void onEnable() {
        MangoBungeeLogger.info("芒果核心 YaMangoCore 检查中...");
        Config config = MangoBungeeConfiguring.getConfig();

        MangoBungeeLogger.info("初始化资源");
        // Mongodb 客户端池
        Clients.connections = new ConcurrentHashMap<>();
        MangoBungeeLogger.info("初始化资源完成");

        // 初始化数据库
        MangoBungeeLogger.info("初始化数据库");
        MangoBungeeServer.load(config);
        MangoBungeeLogger.info("初始化数据库完成");

        // 报告加载结果
        MangoBungeeLogger.info("共加载 " + Clients.connections.size() + " 个 mongodb 链接");
        MangoBungeeLogger.info("检查完成");
    }

    @Override
    public void onDisable() {
        MangoBungeeLogger.info("芒果核心 YaMangoCore 关闭中...");
        // 清理 mongodb 链接
        Clients.connections.forEach((key, value) -> {
            value.close();
        });
    }
}
