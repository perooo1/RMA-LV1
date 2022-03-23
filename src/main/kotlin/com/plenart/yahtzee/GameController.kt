package com.plenart.yahtzee

class GameController : CombinationChecker {
    private val player: Player = Player()
    private var turn = 1

    fun startGame() {
        player.rollAllDice()

        do {
            displayCurrentHand()
            lockDice()

            if (!isContinuing()) {
                endTurn()
                break;
            }

            turn++
            player.rollRemainingDice()
            displayRolledRemainingDice()

        } while (turn < 3)

        endTurn()
    }

    private fun displayRolledRemainingDice() {
        println("Rolled remaining dice: ")
        for (die: Die in player.dice) {
            if(!die.locked)
                print("${die.value}, ")
        }
        println()
    }

    private fun endTurn() {
        println("You have ended your turn.")
        checkCombinations()
        println("Thank You for playing! :)")
    }

    private fun lockDice() {
        println("Enter dice values you wish to keep with no separators.")
        val enteredValue = readLine()?.filter {
            it.isDigit()
        }?.toMutableList()

        println("You locked Dices: \n")
        enteredValue?.let {
            for (i in enteredValue) {
                print("$i, ")
                for (die: Die in player.dice) {
                    val firstElementFound = player.dice.find { (it.value == i.digitToInt()) }
                    if ((die.value == firstElementFound?.value) && !die.locked) {
                        die.locked = true
                        break
                    }

                }

            }
        }

    }

    private fun isContinuing(): Boolean {
        println("\nDo you wish to continue with the game?")
        println("To stop the game type 'no', else press any key")

        return readLine() != "no"

    }

    private fun displayCurrentHand() {
        println("Your current hand:")
        for (die: Die in player.dice) {
            print("${die.value}, ")
        }
        println()
    }

    override fun checkCombinations(): String {
        return if (checkYahtzee()) {
            "You rolled Yahtzee!"
        } else if (checkPoker()) {
            "You rolled Poker!"
        } else if (checkScale()) {
            "You rolled a scale!"
        } else
            "You rolled no combinations this time!"

    }


    override fun checkYahtzee(): Boolean {
        val lockedDice = player.dice.filter { it.locked }

        val yahtzee = lockedDice
            .groupingBy { it.value }
            .eachCount().filter { it.value >= 5 }

        return yahtzee.isNotEmpty()


    }

    override fun checkPoker(): Boolean {
        val lockedDice = player.dice.filter { it.locked }

        val poker = lockedDice
            .groupingBy { it.value }
            .eachCount().filter { it.value == 4 }

        return poker.isNotEmpty()

    }

    override fun checkScale(): Boolean {
        val lockedDiceValues = getLockedDiceValues()

        val bigScale = arrayListOf(1, 2, 3, 4, 5)
        val smallScale = arrayListOf(2, 3, 4, 5, 6)

        return lockedDiceValues.containsAll(bigScale) || lockedDiceValues.containsAll(smallScale)
    }

    private fun getLockedDiceValues(): ArrayList<Int> {
        val lockedDice = player.dice.filter { it.locked }
        val lockedDiceValues = arrayListOf<Int>()
        for (die: Die in lockedDice) {
            lockedDiceValues.add(die.value)
        }
        return lockedDiceValues
    }


}