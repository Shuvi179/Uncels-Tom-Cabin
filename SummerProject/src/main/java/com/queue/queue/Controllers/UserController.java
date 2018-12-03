package com.queue.queue.Controllers;

import com.queue.constants.URL;
import com.queue.queue.Request;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @PostMapping
    public ResponseEntity<Request> sendRequestToUserService(@RequestBody Request requestToUserService){

        ResponseEntity<Request> responseUser = postRequest(requestToUserService, URL.USER_URL);

        if(responseUser.getStatusCode() == HttpStatus.OK){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Request>  postRequest(Request requestToPost, String url){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Request> request = new HttpEntity<>(requestToPost);
        ResponseEntity<Request> response = restTemplate
                .exchange(url, HttpMethod.POST, request, Request.class);
        return response;
    }
}

