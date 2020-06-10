package com.appsflyer.helloworld.services;

import com.appsflyer.helloworld.entities.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * Methods to work with Map
 */


@Service
@Slf4j
@ConfigurationProperties("request.service")
public class RequestService {

    private MapService mapService;

    @Value("${allowedTimeWindow}")
    private long allowedTimeWindow;

    @Value("${allowedRequestQuantity}")
    private int allowedRequestQuantity;

    public RequestService(MapService mapService) {
        this.mapService = mapService;
    }

    public void addRequestForNewClient(int id) {
        mapService.getMap().put(id, new Client(1, System.currentTimeMillis()));
        log.info("New user created. Id: " + id);
    }

    public boolean isClientMissing(int id) {
        Client client = mapService.getMap().get(id);
        if (client == null) {
            log.info("Requested client is missing. Id: " + id);
            return true;
        } else {
            return false;
        }
    }

    public void changeRequestAmount(int clientId) {
        Client client = mapService.getMap().get(clientId);
        int k = client.getNumberOfRequests() + 1;
        client.setNumberOfRequests(k);

        log.info("Client " + clientId + " did " + k + " requests");
    }

    public boolean inTimeWindow(int clientId) {
        long timeWindow = System.currentTimeMillis() - mapService.getMap().get(clientId).getCreationDate();
        return ((allowedTimeWindow - timeWindow) > 0);
    }

    public boolean isNewRequestsAllowed(int clientId) {
        return (allowedRequestQuantity - mapService.getMap().get(clientId).getNumberOfRequests() > 0);
    }


}
