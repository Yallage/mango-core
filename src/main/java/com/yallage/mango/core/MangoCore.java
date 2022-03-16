package com.yallage.mango.core;

import com.yallage.mango.core.config.Configuring;
import com.yallage.mango.core.data.Config;
import com.yallage.mango.core.database.Connection;
import com.yallage.mango.core.database.MongodbLoader;
import com.yallage.mango.core.log.MangoLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MangoCore extends JavaPlugin {

    @Override
    public void onLoad() {
        MangoLogger.info("芒果核 YaMangoCore 加载中...");
        Configuring.loadConfig();
    }

    @Override
    public void onEnable() {
        MangoLogger.info("芒果核 YaMangoCore 检查中...");
        Config config = Configuring.getConfig();

        MangoLogger.info("初始化资源");
        // 线程池
        Connection.executors = new ThreadPoolExecutor(
                config.getThreadCorePoolSize(),
                config.getThreadMaxPoolSize(),
                config.getThreadKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>()
        );
        // 索引
        Connection.databases = Collections.synchronizedList(new ArrayList<>());
        Connection.collections = new ConcurrentHashMap<>();
        // Mongodb 客户端池
        Connection.connections = new ConcurrentHashMap<>();
        MangoLogger.info("初始化资源完成");

        // 初始化数据库
        MangoLogger.info("初始化数据库");
        MongodbLoader.load();
        MangoLogger.info("初始化数据库完成");

        // 报告加载结果
        MangoLogger.info("共加载 " + Connection.connections.size() + " 个数据库连接");
        MangoLogger.info("共读取 " + Connection.collections.size() + " 个数据库集合");
        MangoLogger.info("检查完成");
    }

    @Override
    public void onDisable() {
        MangoLogger.info("芒果核 YaMangoCore 关闭中...");
        // 清理 mongodb 链接
        Connection.connections.forEach((key, value) -> {
            value.close();
        });
        // 关闭线程池
        Connection.executors.shutdown();
        // 保存配置文件
        Configuring.saveConfig();
    }
}
