package com.dadatop.cd.firemonitor.websocket4image.entity;

public class Config {
    private int id;
    private int idle;
    private int idletime;
    private int cload;
    private int csize;
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdle() {
        return idle;
    }

    public void setIdle(int idle) {
        this.idle = idle;
    }

    public int getIdletime() {
        return idletime;
    }

    public void setIdletime(int idletime) {
        this.idletime = idletime;
    }

    public int getCload() {
        return cload;
    }

    public void setCload(int cload) {
        this.cload = cload;
    }

    public int getCsize() {
        return csize;
    }

    public void setCsize(int csize) {
        this.csize = csize;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
