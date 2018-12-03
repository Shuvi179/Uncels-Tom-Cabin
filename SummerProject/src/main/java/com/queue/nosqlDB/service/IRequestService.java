package com.queue.nosqlDB.service;

import com.queue.queue.Request;

public interface IRequestService {

    void fillQueuesWithUnexecutedRequests();

    Request save(Request request);

    void updateToExecuted(String guid);

    void updateToUnexecuted(String guid);

    void updateToSent(String guid);

    void delete(String guid);
}
