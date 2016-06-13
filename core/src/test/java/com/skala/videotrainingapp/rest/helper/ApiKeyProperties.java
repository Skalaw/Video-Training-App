package com.skala.videotrainingapp.rest.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Skala
 */
public class ApiKeyProperties {
    public String getApiKey() {
        String apiKey;

        Properties prop = new Properties();
        String propFileName = "video_api.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inputStream != null) {
            try {
                prop.load(inputStream);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        apiKey = prop.getProperty("apiKey");

        return apiKey;
    }
}
