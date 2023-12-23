package com.nhnacademy.node;

import java.util.UUID;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Node {
    protected final UUID id;
    protected final long createdTime;
    protected String name;
    protected int x;
    protected int y;

    protected Node(UUID id, String name, int x, int y) {
        this.createdTime = System.currentTimeMillis();

        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();

        obj.put("name", getName());
        obj.put("id", getId());
        obj.put("x", x);
        obj.put("y", y);

        return obj;
    }
}
