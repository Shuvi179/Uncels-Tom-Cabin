package com.queue.nosqlDB.dao;

import com.queue.nosqlDB.entity.RequestEntity;
import com.queue.queue.Request;

import java.util.List;

public interface IRequestDAO {

    RequestEntity getEntityByGuid(String guid);

    List<RequestEntity> getAllWithTheSameStatus(byte statusId);

    Request saveToDatabase(Request request);

    void updateStatus(String guid, byte statusId);

    void updateCompletionTime(String guid);

    boolean isExists(String guid);

    void delete(String guid);
}
