package com.freyvik.jUnit_test.app.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RunAppTest {

    private final ServerProperties serverProperties;

    @Autowired
    public RunAppTest(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Test
    void runAppTest() {
        Assertions.assertEquals("localhost", serverProperties.getAddress());
    }
}
