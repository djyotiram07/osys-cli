package com.cli.api;

import jakarta.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class User {

    public Map<String, String> osUser() {
        Map<String, String> user = new HashMap<>();
        user.put("User Name", System.getProperty("user.name"));
        user.put("User Country", System.getProperty("user.country"));
        user.put("User Language", System.getProperty("user.language"));
        user.put("User Home", System.getProperty("user.home"));
        return user;
    }
}
