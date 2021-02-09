package com.westminster.models;

public class COMMON_PROCESS {
    private String name;
    private ThreadGroup group;

    COMMON_PROCESS(String name, ThreadGroup group){
        this.name = name;
        this.group = group;
    }
    public final String getName() {
        return this.name;
    }

}
