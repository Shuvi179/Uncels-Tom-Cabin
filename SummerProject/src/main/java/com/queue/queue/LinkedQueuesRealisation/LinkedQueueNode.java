package com.queue.queue.LinkedQueuesRealisation;

import com.queue.queue.Request;


public class LinkedQueueNode {
    private Request request;
    private LinkedQueueNode nodeNext;
    private LinkedQueueNode nodePrevious;

    public void setRequest(Request request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }

    public LinkedQueueNode getNodeNext() {
        return nodeNext;
    }

    public void setNodeNext(LinkedQueueNode node) {
        this.nodeNext = node;
    }

    public LinkedQueueNode getNodeLast() {
        return nodePrevious;
    }

    public void setNodeLast(LinkedQueueNode nodeLast) {
        this.nodePrevious = nodeLast;
    }
}

