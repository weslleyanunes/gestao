package com.gestao.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PreferencesManager {
    private static final String FILE_PATH = "database/settings.properties";
    private static final String KEY_THEME = "theme"; // values: light | dark
    private static final String KEY_DEBUG = "debug"; // values: true | false

    private final Properties props = new Properties();

    public PreferencesManager() {
        load();
    }

    public void load() {
        File f = new File(FILE_PATH);
        if (f.exists()) {
            try (FileInputStream fis = new FileInputStream(f)) {
                props.load(fis);
            } catch (IOException ignored) {}
        }
    }

    public void save() {
        File f = new File(FILE_PATH);
        f.getParentFile().mkdirs();
        try (FileOutputStream fos = new FileOutputStream(f)) {
            props.store(fos, "Sistema de Gestão - Preferências");
        } catch (IOException ignored) {}
    }

    public String getTheme() {
        return props.getProperty(KEY_THEME, "light");
    }

    public void setTheme(String theme) {
        props.setProperty(KEY_THEME, theme == null ? "light" : theme);
    }

    public boolean isDebug() {
        return Boolean.parseBoolean(props.getProperty(KEY_DEBUG, "false"));
    }

    public void setDebug(boolean debug) {
        props.setProperty(KEY_DEBUG, Boolean.toString(debug));
    }
}
