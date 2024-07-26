package com.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private Properties properties;

    public Config() {
        properties = new Properties();
        String configFileName = "config/config.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("property file '" + configFileName + "' not found in the classpath");
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Properties getProperties() {
        return properties;
    }
}
