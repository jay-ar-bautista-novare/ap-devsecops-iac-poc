package com.aboitizpower.app.util;

import com.aboitizpower.app.exception.DevSecOpsException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {

    private static final String PATH = "src/main/properties/environment_dev.properties";
    private static final Properties properties = new Properties();

    static {
        try(InputStream inputStream = new FileInputStream(PATH)){
            properties.load(inputStream);
        } catch (IOException e) {
            throw new DevSecOpsException("Unable to load environment.properties file", e);
        }
    }

    private Config() {
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
