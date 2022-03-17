package com.yallage.mango.core.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Database {
    String database;
    String host;
    int port;
    String username;
    String password;
}
