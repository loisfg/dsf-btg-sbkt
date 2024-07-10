package com.challenge.btgpactual.orderms.controller

import com.challenge.btgpactual.orderms.controller.dto.ApiResponse
import com.challenge.btgpactual.orderms.controller.dto.OrderResponse
import com.challenge.btgpactual.orderms.mapper.PaginationMapper
import com.challenge.btgpactual.orderms.service.OrderService
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController(
    val orderService: OrderService
) {

    @GetMapping("/customers/{customersId}/orders")
    fun listOrder(
        @RequestParam(name = "page", defaultValue = "0") page: Int,
        @RequestParam(name = "pageSize", defaultValue = "10") pageSize: Int,
        @PathVariable(name = "customerId") customerId: Long
    ): ResponseEntity<ApiResponse<OrderResponse>> {
        val pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize))
        val totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId)
        return ResponseEntity.ok(
            ApiResponse(
                mapOf(Pair("totalOnOrders", totalOnOrders)),
                pageResponse.content,
                PaginationMapper.fromPage(pageResponse)
            )
        )
    }
}