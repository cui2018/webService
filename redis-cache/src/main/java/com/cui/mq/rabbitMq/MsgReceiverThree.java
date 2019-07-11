package com.cui.mq.rabbitMq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Auther: cui
 * @Date: 2019/7/11 11:42
 * @Description:
 */
@Slf4j
@Component
public class MsgReceiverThree {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_A)
    public void process(String content, @Headers Map<String,Object> headers, @Payload String body ){
        log.info("three接收处理队列A当中的消息： " + content);
        log.info("three接收处理队列A当中的消息body： " + body);
        log.info("three接收处理队列A当中的消息headers： " + headers);
    }
}
