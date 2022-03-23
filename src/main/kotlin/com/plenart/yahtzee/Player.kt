package com.plenart.yahtzee

class Player {
    val dice: ArrayList<Die> = arrayListOf()
    //var continuing = true

    init{
        for(i in 1..6){
            dice.add(Die())
        }
    }

    fun rollAllDice(){
        for(die: Die in dice){
            die.locked = false
            die.roll()
        }
    }

    fun rollRemainingDice(){
        for(die: Die in dice)
            die.roll()

    }

}