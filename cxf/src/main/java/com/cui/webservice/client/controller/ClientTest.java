package com.cui.webservice.client.controller;

import com.cui.webservice.client.config.ClientUtil;
import com.cui.webservice.server.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.namespace.QName;

/**
 * @Auther: cui
 * @Date: 2019/6/13
 * @Description:
 */
@Slf4j
@RestController
public class ClientTest {

    @GetMapping(path = "/webserviceClient/test")
    public ResponseEntity<String> testWebservice() throws Exception {
        Client client = ClientUtil.getClient();
        QName qName = ClientUtil.getQName("cuiGetUser");
        Object[] result = client.invoke(qName, "123456", "cui", "25");
        User user = new User();
        BeanUtils.copyProperties(result[0], user);
        log.info("user:{}", user);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
