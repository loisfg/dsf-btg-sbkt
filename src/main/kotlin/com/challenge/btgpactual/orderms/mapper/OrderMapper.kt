package com.challenge.btgpactual.orderms.mapper

import com.challenge.btgpactual.orderms.controller.dto.OrderResponse
import com.challenge.btgpactual.orderms.entity.OrderEntity

object OrderMapper {
    fun fromEntity(orderEntity: OrderEntity): OrderResponse {
        return OrderResponse(
            orderId = orderEntity.orderId,
            customerId = orderEntity.customerId,
            total = orderEntity.total
        )
    }
}