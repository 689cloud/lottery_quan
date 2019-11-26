package com.demolotteryapp.data.remote.model

import com.demolotteryapp.data.local.db.entity.LotteryEntity

data class LotteryApiResponse(
    val page: Long,
    val results: List<LotteryEntity>,
    val total_results: Long,
    val total_pages: Long
)