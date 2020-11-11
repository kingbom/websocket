package com.kingbom.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class UserStorage {
    private static UserStorage instance;

    @Getter
    private Set<String> users;

    public UserStorage() {
        users = new HashSet<>();
    }

    public static synchronized UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    public void setUser(String username) throws Exception {
        if (users.contains(username)) {
            throw new Exception(String.format("User already exists with userName %s", username));
        }

        users.add(username);
    }
}
