package com.appsflyer.helloworld.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client {

    private int numberOfRequests;
    private long creationDate;

    public Client(int numberOfRequests, long creationDate) {
        this.numberOfRequests = numberOfRequests;
        this.creationDate = creationDate;
    }
}
