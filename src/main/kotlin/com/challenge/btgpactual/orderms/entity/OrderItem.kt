package com.challenge.btgpactual.orderms.entity

import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import java.math.BigDecimal

data class OrderItem(
    var product: String,
    var quantity: Int,
    @Field(targetType = FieldType.DECIMAL128)
    var price: BigDecimal
)
