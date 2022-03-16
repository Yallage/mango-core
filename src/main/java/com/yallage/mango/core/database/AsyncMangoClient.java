package com.yallage.mango.core.database;

import java.util.concurrent.Future;

import com.yallage.mango.core.interfaces.AsyncDatabaseClient;

public class AsyncMangoClient implements AsyncDatabaseClient{

    @Override
    public Future<Boolean> create(String database, String collection, Object data) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> Future<T> read(String database, String collection, T data) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Future<Boolean> update(String database, String collection, String id, Object data) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Future<Boolean> delete(String database, String collection, Object data) {
        // TODO Auto-generated method stub
        return null;
    }
}
