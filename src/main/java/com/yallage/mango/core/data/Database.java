package com.yallage.mango.core.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Database {
    String host;
    String port;
    String user;
    String password;
}
