package com.demolotteryapp.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["drwNo"])
data class LotteryInfo (
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
)

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