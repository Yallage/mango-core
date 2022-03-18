package com.yallage.mango.core.event;

import com.google.gson.Gson;
import net.md_5.bungee.api.plugin.Event;

import java.util.ArrayList;
import java.util.List;

public class MangoBungeeAsyncEvent extends Event {
    String asyncId;
    Gson gson;
    List<String> json;

    public MangoBungeeAsyncEvent(String asyncId, Gson gson, List<String> list) {
        this.asyncId = asyncId;
        this.gson = gson;
        this.json = list;
    }

    public String getAsyncId() {
        return asyncId;
    }

    public <T> List<T> getData(Class<T> type) {
        List<T> list = new ArrayList<>(json.size());
        json.forEach(s -> list.add(gson.fromJson(s, type)));
        return list;
    }
}
