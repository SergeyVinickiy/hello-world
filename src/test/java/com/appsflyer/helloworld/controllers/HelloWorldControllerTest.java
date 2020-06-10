package com.appsflyer.helloworld.controllers;

import com.appsflyer.helloworld.services.MapService;
import com.appsflyer.helloworld.services.RequestService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class HelloWorldControllerTest {

    private RequestService requestService = new RequestService(new MapService());
    private HelloWorldController helloWorldController = new HelloWorldController(requestService);

    private MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(helloWorldController).build();

    @Test
    void hello() throws Exception {
        int[] clientIds = {1, 2};
        for (int clintId : clientIds) {
            mockMvc.perform(get("/hello-world?clientId=" + clintId))
                    .andExpect(status().isOk()).andReturn();
        }

    }

}