package com.queue.queue.Controllers;

import com.queue.constants.ServiceID;
import com.queue.queue.Queue.DeviceQueue;
import com.queue.queue.QueueRepository;
import com.queue.queue.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/device")
public class DeviceController {

    @Autowired
    private QueueRepository repository;

    @GetMapping
    public ResponseEntity<Request> checkDeviceRequest(){
        if(repository.getQueueByServiceID(ServiceID.DEVICE_ID).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(repository.getQueueByServiceID(ServiceID.DEVICE_ID).getRequest(),HttpStatus.OK);
    }

}


