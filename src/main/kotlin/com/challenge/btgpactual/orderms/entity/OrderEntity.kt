package com.challenge.btgpactual.orderms.entity

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.data.mongodb.core.mapping.MongoId
import java.math.BigDecimal

@Document(collection = "tb_orders")
data class OrderEntity(
    @MongoId
    var orderId: Long,
    @Indexed(name = "customer_id_index")
    var customerId: Long,
    @Field(targetType = FieldType.DECIMAL128)
    var total: BigDecimal,
    var itens: List<OrderItem>
)
