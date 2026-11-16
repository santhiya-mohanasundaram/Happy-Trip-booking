package com.tripease.travelbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class TravelBookingApplication {

    public static void main(String[] args) {
        loadDotEnv();
        SpringApplication.run(TravelBookingApplication.class, args);
    }

    private static void loadDotEnv() {
        File[] candidates = {
            new File(".env"),
            new File("../.env"),
            new File("backend/.env"),
            new File("Backend/.env")
        };

        for (File file : candidates) {
            if (file.exists() && file.isFile()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        line = line.trim();
                        if (line.isEmpty() || line.startsWith("#")) continue;
                        int eqIdx = line.indexOf('=');
                        if (eqIdx > 0) {
                            String key = line.substring(0, eqIdx).trim();
                            String value = line.substring(eqIdx + 1).trim();
                            if ((value.startsWith("\"") && value.endsWith("\"")) ||
                                (value.startsWith("'") && value.endsWith("'"))) {
                                value = value.substring(1, value.length() - 1);
                            }
                            if (System.getProperty(key) == null && System.getenv(key) == null) {
                                System.setProperty(key, value);
                            }
                        }
                    }
                    System.out.println("[TripEase] Successfully loaded environment configuration from " + file.getName());
                    break;
                } catch (Exception e) {
                    System.err.println("[TripEase] Warning: Could not read .env file: " + e.getMessage());
                }
            }
        }
    }
}
