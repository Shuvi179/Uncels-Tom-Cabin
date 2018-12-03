package com.queue.queue.Controllers;

import com.queue.nosqlDB.service.IRequestService;
import com.queue.queue.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.queue.constants.URL.MOBILE_URL;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    @Autowired
    IRequestService requestService;

    @PostMapping
    public ResponseEntity<Request> sendNotification(@RequestBody Request request){
        requestService.save(request);
        ResponseEntity<Request> response = sendToMobile(request, MOBILE_URL);
        if (response.getStatusCode() == HttpStatus.OK)
            requestService.updateToSent(request.getGuid());
        else
            requestService.updateToUnexecuted(request.getGuid());
        return response;
    }

    private ResponseEntity<Request> sendToMobile(Request requestToSend, String url){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Request> request = new HttpEntity<>(requestToSend);
        return restTemplate.exchange(url, HttpMethod.POST, request, Request.class);
    }

}


