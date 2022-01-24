package org.ipps.models;

public enum Permission {
    employee("employee"),
    admin("admin");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
