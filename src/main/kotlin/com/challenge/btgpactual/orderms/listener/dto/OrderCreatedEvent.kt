package com.challenge.btgpactual.orderms.listener.dto

data class OrderCreatedEvent(
    var codigoPedido: Long,
    var codigoCliente: Long,
    var itens: List<OrderItemEvent>
)
