package com.hoocons.hooconsandroid.EventBus;

/**
 * Created by hungnguyen on 9/17/17.
 */

public class PasswordCollected {
    private String password;

    public PasswordCollected(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
