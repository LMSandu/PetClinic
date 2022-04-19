package com.endava.petclinic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvReader {

    private static final Properties properties = new Properties();

    static {
        String env = System.getProperty("env");

        InputStream resourceAsStream = EnvReader.class.getClassLoader().getResourceAsStream("env/" + env + ".properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException("Can't read property file", e);
        }
    }

    public static String getBaseURI() {
        return properties.getProperty("baseUri");
    }

    public static Integer getPort() {
        return Integer.parseInt(properties.getProperty("port"));
    }

    public static String getBasePath() {
        return properties.getProperty("basePath");
    }

    public static String getAdminUsername() {
        return properties.getProperty("adminUsername");
    }

    public static String getAdminPassword() {
        return properties.getProperty("adminPassword");
    }

    public static String getDBURL() {
        return properties.getProperty("db.url");
    }

    public static String getDBusername() {
        return properties.getProperty("db.username");
    }

    public static String getDBpassword() {
        return properties.getProperty("db.password");
    }
}
