package com.challenge.btgpactual.orderms.controller.dto

import org.springframework.data.domain.Page

data class PaginationReponse(
    var page: Int,
    var pageSize: Int,
    var totalElements: Long,
    var totalPages: Int
) {

}
