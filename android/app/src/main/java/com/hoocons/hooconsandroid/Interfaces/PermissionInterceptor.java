package com.hoocons.hooconsandroid.Interfaces;

/**
 * Created by hungnguyen on 9/30/17.
 */

public interface PermissionInterceptor {
    boolean intercept(String url, String[] permissions, String action);
}
