package com.yallage.mango.core;

import com.yallage.mango.core.client.Clients;
import com.yallage.mango.core.config.MangoBukkitConfiguring;
import com.yallage.mango.core.interfaces.Config;
import com.yallage.mango.core.server.MangoServer;
import org.bukkit.Bukkit;
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
        MangoLogger.logger(Bukkit.getLogger());
        MangoLogger.info("芒果核心 YaMangoCore 加载中...");
        MangoBukkitConfiguring.loadConfig();
    }

    @Override
    public void onEnable() {
        MangoLogger.info("芒果核心 YaMangoCore 检查中...");
        Config config = MangoBukkitConfiguring.getConfig();

        MangoLogger.info("初始化资源");
        // Mongodb 客户端池
        Clients.connections = new ConcurrentHashMap<>();
        MangoLogger.info("初始化资源完成");

        // 初始化数据库
        MangoLogger.info("初始化数据库");
        MangoServer.load(config);
        MangoLogger.info("初始化数据库完成");

        // 报告加载结果
        MangoLogger.info("共加载 " + Clients.connections.size() + " 个 mongodb 链接");
        MangoLogger.info("检查完成");
    }

    @Override
    public void onDisable() {
        MangoLogger.info("芒果核心 YaMangoCore 关闭中...");
        // 清理 mongodb 链接
        Clients.connections.forEach((key, value) -> {
            value.close();
        });
    }
}
