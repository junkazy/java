package com.lgcns.tct.sp.sub3.dto;

public abstract class State {

    private String name;

    public State(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String run() throws Exception;
}
