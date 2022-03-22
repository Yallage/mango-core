package com.yallage.mango.core.interfaces;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Config {
    // 数据库配置文件 key 为显示名称 value 为数据库配置
    Map<String, Database> databases;

    public Config() {
    }
}
