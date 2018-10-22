package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author anonymity
 * @create 2018-10-19 15:20
 **/
@Configuration
public class RabbitMqConfig {

    /**
     * 如果exchange和queue都是持久化的,那么它们之间的binding也是持久化的,如果exchange和queue两者之间有一个持久化，一个非持久化,则不允许建立绑定.
     注意：一旦创建了队列和交换机,就不能修改其标志了,例如,创建了一个non-durable的队列,然后想把它改变成durable的,唯一的办法就是删除这个队列然后重现创建。
     * @return
     */

    @Bean
    public Queue directQueue(){
        return new Queue("direct", false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange", false, false);
    }

    @Bean
    public Binding bingMsgQueue(){
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("rabbit.msg");
    }

}
