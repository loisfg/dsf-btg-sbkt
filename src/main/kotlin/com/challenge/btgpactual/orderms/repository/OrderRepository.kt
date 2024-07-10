package com.challenge.btgpactual.orderms.repository

import com.challenge.btgpactual.orderms.controller.dto.OrderResponse
import com.challenge.btgpactual.orderms.entity.OrderEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository: MongoRepository<OrderEntity, Long> {
    fun findAllByCustomerId(customerId: Long, pageRequest: PageRequest): Page<OrderEntity>
}