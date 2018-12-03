package com.queue.queue;

import com.queue.queue.Queue.*;
import com.queue.constants.*;
import org.springframework.stereotype.Component;

import java.util.Queue;

@Component("QueueRepository")
public class QueueRepository {
    private IQueue deviseQueue = new DeviceQueue();
    private IQueue videoQueue = new VideoQueue();


    public IQueue getQueueByServiceID(int serviceId){
        switch (serviceId){
            case (ServiceID.DEVICE_ID):
                return deviseQueue;
            case (ServiceID.VIDEO_ID):
                return videoQueue;
            default:
                return null;
        }
    }

}
