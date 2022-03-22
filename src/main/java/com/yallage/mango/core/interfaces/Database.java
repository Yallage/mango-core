package com.yallage.mango.core.interfaces;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Database {
    String database;
    String host;
    int port;
    String username;
    String password;
}
