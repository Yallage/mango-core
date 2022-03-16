package com.yallage.mango.core.interfaces;

import java.util.concurrent.Future;

public interface AsyncDatabaseClient {
    Future<Boolean> create(String database, String collection, Object data);

    <T> Future<T> read(String database, String collection, T data);

    Future<Boolean> update(String database, String collection, String id, Object data);

    Future<Boolean> delete(String database, String collection, Object data);
}
