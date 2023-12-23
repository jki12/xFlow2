package com.nhnacademy.node;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public abstract class ActiveNode extends Node implements Runnable {
    public static final int DEFAULT_INTERVAL = 1_000;

    protected long startTime;
    protected Thread thread;
    @Setter
    protected long interval;

    protected ActiveNode(UUID id, String name, int x, int y) {
        super(id, name, x, y);

        thread = new Thread(this, name);
        interval = DEFAULT_INTERVAL;
    }

    public void start() {
        startTime = System.currentTimeMillis();

        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    protected void process() {
    }

    protected void preprocess() {
    }

    protected void postprocess() {
    }

    public void run() {
        preprocess();

        while (!Thread.currentThread().isInterrupted()) {
            try {
                process();

                Thread.sleep(interval);
            } catch (Exception ignore) {
                log.error(ignore.getMessage());
                
                Thread.currentThread().interrupt();
            }
        }

        postprocess();
    }
}
