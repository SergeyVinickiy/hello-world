package com.appsflyer.helloworld.services;

import com.appsflyer.helloworld.entities.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClientService {

    private MapService mapService;

    public ClientService(MapService mapService) {
        this.mapService = mapService;
    }

    public void addRequestForNewClient(int id){

        mapService.getMap().put(id, new Client(1, System.currentTimeMillis()));
        log.info("New user created. Id: " + id);
    }

    public Client getExistingClient(int id){
        try{
        return mapService.getMap().get(id);}
        catch(NullPointerException e)
        {
            log.info("Requested client is missing. Id: " + id);
            return null; }
    }

}
