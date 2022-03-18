package com.yallage.mango.core.log;

import com.yallage.mango.core.MangoBungeeCordCore;
import org.bukkit.ChatColor;

import java.util.logging.Logger;

public class MangoBungeeCordLogger {
    static Logger logger = MangoBungeeCordCore.getInstance().getLogger();

    public static void info(String message) {
        logger.info(ChatColor.GREEN + "[YaMangoCore] " + message);
    }

    public static void warning(String message) {
        logger.warning(ChatColor.YELLOW + "[YaMangoCore] " + message);
    }

    public static void severe(String message) {
        logger.severe(ChatColor.RED + "[YaMangoCore] " + message);
    }
}
