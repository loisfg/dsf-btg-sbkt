package com.challenge.btgpactual.orderms.listener

import com.challenge.btgpactual.orderms.config.RabbitMqConfig.Companion.ORDER_CREATED_QUEUE
import com.challenge.btgpactual.orderms.listener.dto.OrderCreatedEvent
import com.challenge.btgpactual.orderms.service.OrderService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.Message
import org.springframework.stereotype.Component
import kotlin.math.log

@Component
class OrderCreatedListener(
    val orderService: OrderService
) {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)


    @RabbitListener(queues = [ORDER_CREATED_QUEUE])
    fun listen(message: Message<OrderCreatedEvent>) {
        logger.info("Message consumed: {}", message)

        orderService.save(message.payload)
    }
}