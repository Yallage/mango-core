package com.yallage.mango.core.database;

import com.google.gson.Gson;
import com.yallage.mango.core.interfaces.AsyncMangoClient;

import java.util.concurrent.Future;

public class AsyncMongodbMangoClient implements AsyncMangoClient {
    Gson gson;

    public AsyncMongodbMangoClient(Gson gson) {
        this.gson = gson;
    }

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
