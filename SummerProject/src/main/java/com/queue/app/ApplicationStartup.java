package com.queue.app;

import com.queue.nosqlDB.service.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    IRequestService requestService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        fillQueues();
    }

    private void fillQueues(){
        requestService.fillQueuesWithUnexecutedRequests();
    }
}
