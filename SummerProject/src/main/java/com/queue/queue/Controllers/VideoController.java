package com.queue.queue.Controllers;

import com.queue.constants.ServiceID;
import com.queue.queue.Queue.VideoQueue;
import com.queue.queue.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private QueueRepository repository;

    @GetMapping
    public ResponseEntity<Object> checkVideoRequest(){
        if(repository.getQueueByServiceID(ServiceID.VIDEO_ID).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(repository.getQueueByServiceID(ServiceID.VIDEO_ID).getRequest(), HttpStatus.OK);
    }
}