package com.queue.queue;


public class Request {

    private String guid;
    private byte serviceId;
    private byte priority;
    private int timeLock = 0;
    private int deleteTime = 0;
    private String creationTime;
    private Object body;

    public Request() {
    }

    public Request(String guid, byte serviceId, byte priority, int timeLock, int deleteTime, Object body){
        this.guid = guid;
        this.serviceId = serviceId;
        this.priority = priority;
        this.timeLock = timeLock;
        this.deleteTime = deleteTime;
        this.body = body;
    }

    public Request(byte serviceId, byte priority, Object body){
        this.serviceId = serviceId;
        this.priority = priority;
        this.body = body;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public byte getServiceId() {
        return serviceId;
    }

    public void setServiceId(byte serviceId) {
        this.serviceId = serviceId;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public int getTimeLock() {
        return timeLock;
    }

    public void setTimeLock(int timeLock) {
        this.timeLock = timeLock;
    }

    public int getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(int deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}

