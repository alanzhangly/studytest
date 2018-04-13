package com.alan.tcp.face;

import java.io.Serializable;

/**
 * Created by Ke Zhang on 2018/4/10.
 */
public class RequestModel implements Serializable {
    private String command;
    private int size;

    @Override
    public String toString() {
        return "RequestModel{" +
                "command='" + command + '\'' +
                ", size=" + size +
                '}';
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
