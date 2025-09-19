package com.gestao.repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabaseManager {
    private static final String FILE = "database/data.ser";
    private static DataStore cache;

    public static synchronized DataStore getStore() {
        if (cache == null) {
            cache = load();
        }
        return cache;
    }

    public static synchronized void persist() {
        save(getStore());
    }

    public static void putResetToken(String token, int userId) {
        DataStore s = getStore();
        s.tokensRecuperacao.put(token, userId);
        persist();
    }

    public static Integer consumeResetToken(String token) {
        DataStore s = getStore();
        Integer id = s.tokensRecuperacao.remove(token);
        if (id != null) persist();
        return id;
    }

    private static DataStore load() {
        Path path = Paths.get(FILE);
        if (Files.exists(path)) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
                Object obj = ois.readObject();
                if (obj instanceof DataStore) {
                    return (DataStore) obj;
                }
            } catch (Exception ignored) {
            }
        }
        ensureParent(path);
        return new DataStore();
    }

    private static void save(DataStore store) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(store);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void ensureParent(Path path) {
        try {
            Path parent = path.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
