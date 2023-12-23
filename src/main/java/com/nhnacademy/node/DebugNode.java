package com.nhnacademy.node;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.json.JSONObject;

import com.nhnacademy.Input;
import com.nhnacademy.Wire;
import com.nhnacademy.message.JsonMessage;
import com.nhnacademy.message.Message;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class DebugNode extends ActiveNode implements Input {
    private final Set<Wire> inputWires = new HashSet<>();

    public DebugNode(String name, int x, int y) {
        this(UUID.randomUUID(), name, x, y);
    }

    public DebugNode(UUID id, String name, int x, int y) {
        super(id, name, x, y);
    }

    @Override
    public JSONObject toJson() {
        var obj = super.toJson();

        obj.put("type", "DebugNode");

        return obj;
    }

    @Override
    public void wireIn(Wire wire) {
        inputWires.add(wire);
    }

    /**
     * input 와이어에 있는 큐가 비어있는 동안 메시지를 출력하지 않다가 큐에 메시지가 들어오면 해당 메시지를 로그로 출력함
     */
    @Override
    public void process() {
        try {
            for (Wire wire : inputWires) {
                var q = wire.getMessageQue();

                if (!q.isEmpty()) {
                    JsonMessage msg = (JsonMessage) q.poll();

                    log.debug(msg.toString());
                }
            }

        } catch (Exception ignore) {
            log.error(ignore.getMessage());
        }
    }
}