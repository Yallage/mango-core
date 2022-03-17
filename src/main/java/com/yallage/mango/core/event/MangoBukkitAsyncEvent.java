package com.yallage.mango.core.event;

import com.google.gson.Gson;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;
import java.util.List;

public class MangoBukkitAsyncEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    String asyncId;
    Gson gson;
    List<String> json;

    public MangoBukkitAsyncEvent(String asyncId, Gson gson, List<String> list) {
        super(true);
        this.asyncId = asyncId;
        this.gson = gson;
        this.json = list;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public String getAsyncId() {
        return asyncId;
    }

    public <T> List<T> getData(Class<T> type) {
        List<T> list = new ArrayList<>(json.size());
        json.forEach(s -> list.add(gson.fromJson(s, type)));
        return list;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
