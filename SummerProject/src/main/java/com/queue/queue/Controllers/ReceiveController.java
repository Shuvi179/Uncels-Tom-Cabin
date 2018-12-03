package com.queue.queue.Controllers;

import com.queue.constants.PriorityType;
import com.queue.nosqlDB.service.IRequestService;
import com.queue.queue.Queue.IQueue;
import com.queue.queue.QueueRepository;
import com.queue.queue.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping(value = "/queue")
public class ReceiveController {
    private static final int MINToMILLISECOND = 60000;

    @Autowired
    QueueRepository repository;

    @Autowired
    IRequestService requestService;

    @PostMapping
    public ResponseEntity<Request> addRequestInQueue(@RequestBody Request request){
        request = requestService.save(request);

        if(repository.getQueueByServiceID(request.getServiceId())!=null){
            return postRequestInQueue(repository.getQueueByServiceID(request.getServiceId()), request);
        }
        else {
            return new ResponseEntity <>(HttpStatus.BAD_REQUEST);
        }
    }

    private ResponseEntity<Request> postRequestInQueue(IQueue queue , Request request){
        if(request.getTimeLock() != 0){
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    request.setPriority(PriorityType.HIGH_PRIORITY);
                    queue.setRequest(request);
                }
            };
            timer.schedule(task,MINToMILLISECOND*request.getTimeLock());
            return new ResponseEntity<>(request, HttpStatus.OK);
        }
        else {
            queue.setRequest(request);
            return new ResponseEntity<>(request, HttpStatus.OK);
        }
    }
}
