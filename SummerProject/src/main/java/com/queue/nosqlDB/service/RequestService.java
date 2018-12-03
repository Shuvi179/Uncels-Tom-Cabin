package com.queue.nosqlDB.service;

import com.queue.nosqlDB.dao.IRequestDAO;
import com.queue.nosqlDB.entity.RequestEntity;
import com.queue.queue.QueueRepository;
import com.queue.queue.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.queue.constants.StatusID.*;

@Service
public class RequestService implements IRequestService {

    @Autowired
    IRequestDAO requestDAO;

    @Autowired
    QueueRepository repository;

    @Override
    public void fillQueuesWithUnexecutedRequests() {
        List<RequestEntity> unexecuted = requestDAO.getAllWithTheSameStatus(RECEIVED);
        for(Request request : unexecuted){
            int serviceId = request.getServiceId();
            repository.getQueueByServiceID(serviceId).setRequest(request);
        }
    }

    @Override
    public synchronized Request save(Request request){
        return requestDAO.saveToDatabase(request);
    }

    @Override
    public void delete(String guid){
        requestDAO.delete(guid);
    }

    @Override
    public void updateToExecuted(String guid) {
        requestDAO.updateCompletionTime(guid);
        updateStatus(guid, EXECUTED);
    }

    @Override
    public void updateToSent(String guid) {
        updateStatus(guid, SENT);
    }

    @Override
    public void updateToUnexecuted(String guid){
        updateStatus(guid, UNEXECUTED);
    }

    private void updateStatus(String guid, byte statusId){
        if(requestDAO.isExists(guid))
            requestDAO.updateStatus(guid, statusId);
    }
}
