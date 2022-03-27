package com.yallage.mango.core.config;

import com.google.gson.Gson;
import com.yallage.mango.core.MangoBukkitCore;
import com.yallage.mango.core.interfaces.Config;
import com.yallage.mango.core.log.MangoBukkitLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class MangoBukkitConfiguring {
    static Gson gson = new Gson();
    static Config config = new Config();

    public static void loadConfig() {
        File file = new File("plugins/" + MangoBukkitCore.getInstance().getDescription().getName() + "/config.json");

        if (!file.exists()) {
            MangoBukkitCore.getInstance().saveResource("config.json", false);
        }

        // 读取配置文件
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            MangoBukkitLogger.info("配置文件加载中...");
            config = gson.fromJson(reader, Config.class);
            MangoBukkitLogger.info("配置文件加载完成");
        } catch (FileNotFoundException exception) {
            MangoBukkitLogger.severe("config.json 文件未找到.");
        }
    }

    public static void saveConfig() {
        File file = new File(MangoBukkitCore.getInstance().getDescription().getName() + "config.json");
        String json = gson.toJson(config);
        try {
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(json.getBytes());
            MangoBukkitLogger.info("配置文件已保存");
        } catch (IOException exception) {
            MangoBukkitLogger.severe(exception.getMessage());
        }
    }

    public static void reloadConfig() {
        loadConfig();
        MangoBukkitLogger.info("重新加载配置文件");
    }

    public static Config getConfig() {
        return config;
    }
}
