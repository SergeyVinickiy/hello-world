package com.appsflyer.helloworld.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RequestServiceTest {
    MapService mapService = new MapService();
    private RequestService requestService = new RequestService(mapService);

    @Test
    void addRequestForNewClient() {
        requestService.addRequestForNewClient(1);
        assertFalse(requestService.isClientMissing(1));
    }

    @Test
    void isClientMissing() {
        assertTrue(requestService.isClientMissing(1));
        requestService.addRequestForNewClient(1);
        assertFalse(requestService.isClientMissing(1));

    }

    @Test
    void changeRequestAmount() {
        requestService.addRequestForNewClient(1);
        requestService.changeRequestAmount(1);
        assertTrue(mapService.getMap().get(1).getNumberOfRequests() == 2);
    }
}