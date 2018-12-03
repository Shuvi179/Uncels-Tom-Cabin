package com.queue.queue.Queue;

import com.queue.queue.Request;

public interface IQueue {

    void setRequest (Request request);
    Request getRequest();
    boolean isEmpty();
}