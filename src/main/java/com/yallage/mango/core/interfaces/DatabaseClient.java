package com.yallage.mango.core.interfaces;

public interface DatabaseClient {
    void create(String database, String collection, Object data);

    <T> T read(String database, String collection, T data);

    void update(String database, String collection, String id, Object data);

    void delete(String database, String collection, Object data);
}
