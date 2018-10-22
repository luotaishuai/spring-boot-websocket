package com.example.demo.config;

import com.example.demo.entity.MessageReserve;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @author anonymity
 * @create 2018-10-22 16:24
 **/
@Configuration
public class RedisConfig {

    @Bean
    public MessageListenerAdapter messageListenerAdapter(MessageReserve reserve){
        // defaultListenerMethod 一定要和参数中method同名
        return new MessageListenerAdapter(reserve, "message");
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory, MessageListenerAdapter adapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        // 指定channel
        container.addMessageListener(adapter, new PatternTopic("chat"));
        return container;
    }
}
