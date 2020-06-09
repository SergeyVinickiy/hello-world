package com.appsflyer.helloworld.controllers;


import com.appsflyer.helloworld.errors.RateLimitedException;
import com.appsflyer.helloworld.services.ClientService;
import com.appsflyer.helloworld.services.RequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class HelloWorldController {

    private ClientService clientService;
    private RequestService requestService;
    @Value(("${positiveResponse}"))
    private String positiveResponse;

    public HelloWorldController(ClientService clientService, RequestService requestService) {
        this.clientService = clientService;
        this.requestService = requestService;
    }

    @GetMapping("/hello-world")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody
    String hello(@RequestParam(name = "clientId") int clientId) {

        if (clientService.isClientMissing(clientId)) {
            clientService.addRequestForNewClient(clientId);
            return positiveResponse;
        }

        if (requestService.inTimeWindow(clientId)) {
            if (requestService.isNewRequestsAllowed(clientId)) {
                requestService.changeRequestAmount(clientId);
                return positiveResponse;
            } else {
                throw new RateLimitedException(clientId);
            }
        } else {
            clientService.addRequestForNewClient(clientId);
            return positiveResponse;

        }
    }
}
