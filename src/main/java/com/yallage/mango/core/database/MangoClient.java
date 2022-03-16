package com.yallage.mango.core.database;

import com.yallage.mango.core.interfaces.DatabaseClient;

public class MangoClient implements DatabaseClient {
    @Override
    public void create(String database, String collection, Object data) {

    }

    @Override
    public <T> T read(String database, String collection, T data) {
        return null;
    }

    @Override
    public void update(String database, String collection, String id, Object data) {

    }

    @Override
    public void delete(String database, String collection, Object data) {

    }
}
