package com.yallage.mango.core.config;

import com.google.gson.Gson;
import com.yallage.mango.core.MangoBungeeCore;
import com.yallage.mango.core.interfaces.Config;
import com.yallage.mango.core.log.MangoBungeeLogger;
import net.md_5.bungee.api.ProxyServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Objects;

public class MangoBungeeConfiguring {
    static Gson gson = new Gson();
    static Config config = new Config();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void loadConfig() {
        File file = new File("plugins" + MangoBungeeCore.getInstance().getDescription().getName() + "/config.json");

        if (!file.exists()) {
            try {
                file.createNewFile();
                Objects.requireNonNull(MangoBungeeCore.getInstance()
                                .getResourceAsStream("/config.json")).
                        transferTo(new FileOutputStream(file));
            } catch (IOException exception) {
                ProxyServer.getInstance().stop();
            }
        }

        // 读取配置文件
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            MangoBungeeLogger.info("配置文件加载中...");
            config = gson.fromJson(reader, Config.class);
            // 反射检查服务器配置任意变量是否为空
            Field[] fields = config.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(config) == null) {
                    MangoBungeeLogger.severe("错误的配置 " + field.getName());
                    ProxyServer.getInstance().stop();
                }
            }
            MangoBungeeLogger.info("配置文件加载完成");
        } catch (FileNotFoundException exception) {
            MangoBungeeLogger.severe("config.json 文件未找到.");
        } catch (IllegalAccessException exception) {
            MangoBungeeLogger.severe("配置文件非法参数");
        }
    }

    public static void saveConfig() {
        File file = new File(MangoBungeeCore.getInstance().getDescription().getName() + "/config.json");
        String json = gson.toJson(config);
        try {
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(json.getBytes());
            MangoBungeeLogger.info("配置文件已保存");
        } catch (IOException exception) {
            MangoBungeeLogger.severe(exception.getMessage());
        }
    }

    public static void reloadConfig() {
        loadConfig();
        MangoBungeeLogger.info("重新加载配置文件");
    }

    public static Config getConfig() {
        return config;
    }
}
