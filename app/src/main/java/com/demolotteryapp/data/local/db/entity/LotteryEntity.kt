package com.demolotteryapp.data.local.db.entity

import androidx.room.Entity

@Entity(primaryKeys = ["drwNo"])
data class LotteryEntity(
    val totSellamnt: Long,
    val returnValue: String,
    val drwNoDate: String,
    val firstWinamnt: Long,
    val drwtNo6: Int,
    val drwtNo4: Int,
    val firstPrzwnerCo: Long,
    val drwtNo5: Int,
    val bnusNo: Int,
    val firstAccumamnt: Long,
    val drwNo: Int,
    val drwtNo2: Int,
    val drwtNo3: Int,
    val drwtNo1: Int
) {
    fun toList() = listOf(drwtNo1,drwtNo2,drwtNo3,drwtNo4,drwtNo5,drwtNo6)
    fun toListTrend() = listOf(drwtNo1,drwtNo2,drwtNo3,drwtNo4,drwtNo5,drwtNo6,bnusNo)
    override fun toString(): String {
        return if (returnValue.equals("fail")) "N/A" else "${drwtNo1}:${drwtNo2}:${drwtNo3}:${drwtNo4}:${drwtNo5}:${drwtNo6} - ${bnusNo}"
    }
}

/*

Example:
Url: https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=800
Response:
{
  "totSellamnt": 76994062000,
  "returnValue": "success",
  "drwNoDate": "2018-03-31",
  "firstWinamnt": 1632246205,
  "drwtNo6": 45,
  "drwtNo4": 12,
  "firstPrzwnerCo": 11,
  "drwtNo5": 28,
  "bnusNo": 26,
  "firstAccumamnt": 17954708255,
  "drwNo": 800,
  "drwtNo2": 4,
  "drwtNo3": 10,
  "drwtNo1": 1
}

 */