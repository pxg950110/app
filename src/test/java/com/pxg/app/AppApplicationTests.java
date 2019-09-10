package com.pxg.app;

import com.pxg.app.core.service.KettleService;
import com.pxg.app.core.service.TbUserService;
import com.pxg.app.core.service.TestService;
import com.pxg.app.util.Service.mail.KettleMailServer;
import com.pxg.app.util.rabbit.RabbitProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pentaho.di.core.exception.KettleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppApplicationTests {


    @Autowired
    TbUserService tbUserService;

    @Autowired
    private RabbitProducer rabbitProducer;
    @Autowired
    private KettleMailServer kettleMailServer;

    @Autowired
    KettleService kettleService;
    @Autowired
    private TestService testService;

    @Test
    public void contextLoads() throws KettleException, UnsupportedEncodingException {
        kettleService.test();
    }
}
