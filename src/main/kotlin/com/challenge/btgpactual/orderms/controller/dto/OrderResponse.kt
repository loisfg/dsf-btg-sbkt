package com.challenge.btgpactual.orderms.controller.dto

import java.math.BigDecimal

data class OrderResponse(
    val orderId: Long = 0,
    val customerId: Long = 0,
    val total: BigDecimal = BigDecimal.ZERO
)
