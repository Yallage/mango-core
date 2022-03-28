package com.yallage.mango.core;

import org.fusesource.jansi.Ansi;

import java.util.logging.Logger;

public class MangoLogger {
    static Logger logger;

    public static void logger(Logger logger) {
        MangoLogger.logger = logger;
    }

    public static void info(String message) {
        logger.info(Ansi
                .ansi()
                .fg(Ansi.Color.GREEN)
                .a("[YaMangoCore] ")
                .reset() + message);
    }

    public static void warning(String message) {
        logger.warning(Ansi
                .ansi()
                .fg(Ansi.Color.YELLOW)
                .a("[YaMangoCore] ")
                .reset() + message);
    }

    public static void severe(String message) {
        logger.severe(Ansi
                .ansi()
                .fg(Ansi.Color.RED)
                .a("[YaMangoCore] ")
                .reset() + message);
    }
}
