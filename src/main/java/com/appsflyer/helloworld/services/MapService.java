package com.appsflyer.helloworld.services;

import com.appsflyer.helloworld.entities.Client;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;


@Service
@Getter
@Setter
public class MapService {

    ConcurrentHashMap<Integer, Client> map;

    public MapService() {
        this.map = new ConcurrentHashMap<>();
    }
}
