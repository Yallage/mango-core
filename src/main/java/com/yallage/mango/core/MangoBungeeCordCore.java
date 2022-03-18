package com.yallage.mango.core;

import net.md_5.bungee.api.plugin.Plugin;

public class MangoBungeeCordCore extends Plugin {
    static MangoBungeeCordCore instance;

    public MangoBungeeCordCore() {
        instance = this;
    }

    public static MangoBungeeCordCore getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
