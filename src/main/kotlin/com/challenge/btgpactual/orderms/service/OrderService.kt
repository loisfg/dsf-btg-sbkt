package com.challenge.btgpactual.orderms.service

import com.challenge.btgpactual.orderms.controller.dto.OrderResponse
import com.challenge.btgpactual.orderms.entity.OrderEntity
import com.challenge.btgpactual.orderms.entity.OrderItem
import com.challenge.btgpactual.orderms.listener.dto.OrderCreatedEvent
import com.challenge.btgpactual.orderms.mapper.OrderMapper
import com.challenge.btgpactual.orderms.repository.OrderRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.bson.Document
import org.springframework.core.annotation.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation.*
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class OrderService(
    var orderRepository: OrderRepository,
    var mongoTemplate: MongoTemplate
) {
    fun save(orderCreatedEvent: OrderCreatedEvent) {
        OrderEntity(
            orderId = orderCreatedEvent.codigoPedido,
            customerId = orderCreatedEvent.codigoCliente,
            total = getTotal(orderCreatedEvent),
            itens = orderCreatedEvent.itens.map { OrderItem(it.produto, it.quantidade, it.preco) }
        ).let {
            orderRepository.save(it)
        }

    }

    fun getTotal(event: OrderCreatedEvent): BigDecimal {
        return if (event.itens.isEmpty())
            BigDecimal.ZERO
        else
            event.itens.sumOf { it.preco.multiply(it.quantidade.toBigDecimal()) }
    }

    fun findAllByCustomerId(customerId: Long, pageRequest: PageRequest): Page<OrderResponse> {
        return orderRepository.findAllByCustomerId(customerId, pageRequest)
            .map { OrderMapper.fromEntity(it) }
    }

    fun findTotalOnOrdersByCustomerId(customerId: Long): BigDecimal {
        val aggregations = newAggregation(
            match(Criteria.where("customerId").`is`(customerId)),
            group().sum("total").`as`("total")
        )

        val reponse = mongoTemplate.aggregate(aggregations, "tb_orders", Document::class.java)

        return reponse.uniqueMappedResult?.getOrDefault("total", BigDecimal.ZERO) as BigDecimal
    }
}