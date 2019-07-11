package com.cui.mq.rabbitMq;

import com.cui.redis.rediscache.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: cui
 * @Date: 2019/7/11 10:47
 * @Description:
 */
@Slf4j
@Component
//@RabbitListener(queues = RabbitMQConfig.QUEUE_A)
public class MsgReceiverTwo {

    //@RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.QUEUE_A,containerFactory = "rabbitListenerContainerFactory2")
    public void process(User content){
        log.info("two接收处理队列A当中的消息： " + content);
        //log.info("two接收处理队列A当中的消息body： " + body);
        //log.info("two接收处理队列A当中的消息headers： " + headers);
    }
}
