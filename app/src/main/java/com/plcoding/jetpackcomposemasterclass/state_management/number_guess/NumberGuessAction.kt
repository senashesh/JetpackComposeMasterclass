package com.plcoding.jetpackcomposemasterclass.state_management.number_guess

sealed interface NumberGuessAction {
    data object OnGuessClick: NumberGuessAction
    data class OnNumberTextChange(val numberText: String): NumberGuessAction
    data object OnStartNewGameButtonClick: NumberGuessAction
}