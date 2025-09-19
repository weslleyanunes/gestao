package com.gestao.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class DebugLogger {
    private static boolean debug = false;
    private static final String LOG_FILE = "database/app.log";

    public static void setDebug(boolean enabled) { debug = enabled; }
    public static boolean isDebug() { return debug; }

    public static void info(String msg) { write("INFO", msg); }
    public static void error(String msg, Throwable t) { write("ERROR", msg + " - " + t.getMessage()); }
    public static void debug(String msg) { if (debug) write("DEBUG", msg); }

    private static synchronized void write(String level, String msg) {
        String line = String.format("%s [%s] %s%n", LocalDateTime.now(), level, msg);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bw.write(line);
        } catch (IOException ignored) {}
    }
}
