package com.hcs.common.event;

import lombok.Getter;

@Getter
public abstract class Event {

    private long timestamp;

    protected Event() {
        this.timestamp = System.currentTimeMillis();
    }


}
