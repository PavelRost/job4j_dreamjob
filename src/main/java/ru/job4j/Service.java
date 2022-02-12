package ru.job4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

public class Service {

    private static final Properties cfg = new Properties();

    public Service() {
        try (BufferedReader io = new BufferedReader(
                new InputStreamReader(
                        Service.class.getClassLoader()
                                .getResourceAsStream("app.properties")
                )
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

    }

    private static final class Lazy {
        private static final Service INST = new Service();
    }

    public static Service instOf() {
        return Service.Lazy.INST;
    }

    public String getPath() {
        return cfg.getProperty("pathToImage");
    }


}
