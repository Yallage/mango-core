package com.yallage.mango.core.data;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Config {
    @SerializedName("thread_core_pool_size")
    int threadCorePoolSize;
    @SerializedName("thread_max_pool_size")
    int threadMaxPoolSize;
    @SerializedName("thread_keep_alive_seconds")
    long threadKeepAliveTime;
    List<Database> databases;

    public Config() {
    }
}
