package com.queue.queue.Queue;

import com.queue.nosqlDB.service.RequestService;
import com.queue.queue.LinkedQueuesRealisation.LinkedQueue;
import com.queue.queue.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component("VideoQueue")
public class VideoQueue implements IQueue {
    @Autowired
    RequestService requestService;


    private LinkedQueue queue = new LinkedQueue();
    private Lock lock = new ReentrantLock();
    private static final int MINToMILLISECOND = 60000;

    public void setQueue(LinkedQueue queue){
        this.queue = queue;
    }

    public void setRequest (Request request){
        lock.lock();
        try {
            if(request.getDeleteTime() == 0) {
                queue.setRequest(request);
            }
            else {
                queue.setRequest(request);
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        if(deleteRequest(request)){
                            requestService.updateToUnexecuted(request.getGuid());
                        }

                    }
                };
                timer.schedule(task,MINToMILLISECOND * request.getDeleteTime());
            }
        }
        finally {
            lock.unlock();
        }
    }

    public Request getRequest(){
        lock.lock();
        try {
            return queue.getRequest();
        }
        finally {
            lock.unlock();
        }
    }

    public boolean deleteRequest(Request request){
        lock.lock();
        try {
            return queue.deleteFromQueue(request);
        }
        finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

