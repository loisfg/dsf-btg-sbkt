package com.challenge.btgpactual.orderms.mapper

import com.challenge.btgpactual.orderms.controller.dto.PaginationReponse
import org.springframework.data.domain.Page

object PaginationMapper {
    fun fromPage(page: Page<*>): PaginationReponse {
        return PaginationReponse(
            page.number,
            page.size,
            page.totalElements,
            page.totalPages
        )
    }
}