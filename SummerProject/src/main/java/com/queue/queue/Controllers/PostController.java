package com.queue.queue.Controllers;

import com.queue.constants.URL;
import com.queue.nosqlDB.service.IRequestService;
import com.queue.queue.QueueRepository;
import com.queue.queue.Request;
import com.queue.queue.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    IRequestService requestService;

    @PostMapping
    public void postToMobileAndWeb(@RequestBody Response responseToPost) {
        requestService.updateToExecuted(responseToPost.getGuid());

        postRequest(responseToPost, URL.MOBILE_URL);
        postRequest(responseToPost, URL.WEB_URL);

    }
    private void postRequest(Response responseToPost, String url){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Response> request = new HttpEntity<>(responseToPost);
        restTemplate.exchange(url, HttpMethod.POST, request, Request.class);
    }
}


