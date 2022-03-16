package com.yallage.mango.core.interfaces;

import com.mongodb.MongoClient;
import com.yallage.mango.core.data.Database;

public interface MongodbConnection {
    MongoClient getMongodbClient(Database database);
}
