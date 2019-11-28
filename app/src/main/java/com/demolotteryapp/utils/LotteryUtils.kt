package com.demolotteryapp.utils

import com.demolotteryapp.data.local.db.entity.LotteryEntity
import com.demolotteryapp.ui.WinnerType

class LotteryUtils {
    companion object {

        /** Generate number of lottery number from @from to @to
         * @return list of lottery number
         * */
        fun generateRandomLotteryNumber(from: Int, to: Int, number: Int): List<Int> {
            return (from..to).shuffled().take(number)
        }

        /**
         * @param randomLotteryNumber : Here are 6 lottery numbers given from random
         * @param lotteryEntity : The lottery info to check
         *
         * Note: Each element in each @param cannot have the same number.
         * */
        fun checkWinner(
            randomLotteryNumber: List<Int>?,
            lotteryEntity: LotteryEntity?
        ): WinnerType {
            if (randomLotteryNumber == null || lotteryEntity == null) return WinnerType.NONE

            val intersect = randomLotteryNumber!!.intersect(lotteryEntity!!.toList())
            val hasBonus = randomLotteryNumber!!.contains(lotteryEntity!!.bnusNo)
            return when (intersect.size) {
                6 -> WinnerType.FIRST
                5 -> if (hasBonus) WinnerType.SECOND else WinnerType.THIRD
                4 -> WinnerType.FOURTH
                3 -> WinnerType.FIRTH
                else -> WinnerType.NONE
            }
        }

        /**
         * Sort the lottery numbers by frequency
         * @param lotteries Among the winning information which was saved in Splash Screen
         * @return List Pair sorted
         * */
        fun performTrending(lotteries: List<LotteryEntity>): List<Pair<Int, Int>> {
            val result = lotteries
                .flatMap { it.toListTrend() }
                .groupBy { it }
                .map { Pair(it.key, it.value.size) }
                .sortedByDescending { it.second }
            return result
        }
    }
}