package com.wei.accio.domain;

public enum DayOff {

    dayOff("特休"),
    must("必休"),
    rotate("輪休");

    private final String displayName;

    DayOff(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return getDisplayName();
    }
}
