package org.zerock.controller.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AopTestServiceTest {

    @Autowired
    private AopTestService service;

    @Test
    public void testc(){
        log.info(String.valueOf(service));
        log.info(service.getClass().getName());
    }

    @Test
    public void testAdd() throws Exception{
        log.info(String.valueOf(service.doAdd("123", "456")));
    }

}