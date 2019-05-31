package com.cui.mq.rabbitMq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Auther: cui
 * @Date: 2019/5/20 17:26
 * @Description: mq config information
 */
@Configuration
public class RabbitMQConfig {

    @Value("${mq.rabbit.host}")
    private String HOST;
    @Value("${mq.rabbit.port}")
    private Integer PORT;
    @Value("${mq.rabbit.virtualHost}")
    private String VIRTUALHOST;
    @Value("${mq.rabbit.username}")
    private String USERNAME;
    @Value("${mq.rabbit.password}")
    private String PASSWORD;


    public static final String EXCHANGE_A = "my-mq-exchange_A";
    public static final String EXCHANGE_B = "my-mq-exchange_B";
    public static final String EXCHANGE_C = "my-mq-exchange_C";


    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";


    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(this.HOST, this.PORT);

        connectionFactory.setUsername(this.USERNAME);
        connectionFactory.setPassword(this.PASSWORD);
        connectionFactory.setVirtualHost(this.VIRTUALHOST);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);

        return connectionFactory;
    }

    /*@Bean
    public Queue queue() {
        return new Queue("mall.paied.order", true);
    }*/
    /*@Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(USERNAME);
        connectionFactory.setPassword(PASSWORD);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }*/

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(){
        return new RabbitAdmin(connectionFactory());
    }

}
