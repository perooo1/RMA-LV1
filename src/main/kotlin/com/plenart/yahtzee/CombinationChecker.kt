package com.plenart.yahtzee

interface CombinationChecker {
    fun checkCombinations(): String
    fun checkYahtzee(): Boolean
    fun checkPoker(): Boolean
    fun checkScale(): Boolean

}