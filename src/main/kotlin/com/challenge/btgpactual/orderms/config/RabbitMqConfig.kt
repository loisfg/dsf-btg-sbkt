package com.challenge.btgpactual.orderms.config

import org.springframework.amqp.core.Declarable
import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfig {

    companion object {
        const val ORDER_CREATED_QUEUE: String = "btg-pactual-order-created"
    }

    @Bean
    fun jackson2JsonMessageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun orderCreatedQueue(): Declarable {
        return Queue(ORDER_CREATED_QUEUE)
    }
}