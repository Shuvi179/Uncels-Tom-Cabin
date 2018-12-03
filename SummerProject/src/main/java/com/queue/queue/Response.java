package com.queue.queue;

public class Response {
    String guid;
    String status;

    public Response(){}

    public Response(String guid, String status){
        this.guid = guid;
        this.status = status;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

