package com.cui.webservice.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cui.webservice"})
public class CxfApplication {

    public static void main(String[] args) {
        SpringApplication.run(CxfApplication.class, args);
        //定义webService的发布地址，提供给外界使用接口的地址
        //String url = "http://localhost:8081/wsServeice";
        //Endpoint.publish(url,new WebServiceImpl());
        System.out.println("发布webService成功！");
    }

}
