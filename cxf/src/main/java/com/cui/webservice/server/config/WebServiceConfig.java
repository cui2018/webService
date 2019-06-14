package com.cui.webservice.server.config;

import com.cui.webservice.server.impl.UserServiceImpl;
import com.cui.webservice.server.service.UserService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @Auther: cui
 * @Date: 2019/6/6
 * @Description:
 */
@Configuration
public class WebServiceConfig {

    @Bean(name = "cxfServlet")
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(),"/webServiceDemo/*", "/demo/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public void endpoint() {
        Endpoint.publish("/webservice", userService());
        Endpoint.publish("/webservice2", userService());
        /*EndpointImpl endpoint = new EndpointImpl(springBus(), userService());
        endpoint.publish("/webservice");
        return endpoint;*/
    }
}
