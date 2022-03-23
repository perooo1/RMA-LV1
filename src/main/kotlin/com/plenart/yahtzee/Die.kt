package com.plenart.yahtzee

import kotlin.random.Random

class Die(var value: Int = (1..6).random()) {
    var locked = false

    fun roll(){
        if(!locked){
            value = Random.nextInt(1,7)
        }
    }
}