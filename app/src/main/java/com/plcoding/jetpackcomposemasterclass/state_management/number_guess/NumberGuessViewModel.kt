package com.plcoding.jetpackcomposemasterclass.state_management.number_guess

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class NumberGuessViewModel: ViewModel() {

    /*
        number and attempts are not directly used inside compose function are state but not compose state
        guessText is generated using them.
     */
    private var number = Random.nextInt(1, 101)
    private var attempts = 0

    private val _state = MutableStateFlow(NumberGuessState())
    val state = _state.asStateFlow()

    fun onAction(action: NumberGuessAction) {
        when(action) {
            NumberGuessAction.OnGuessClick -> {
                val guess = state.value.numberText.toIntOrNull()
                if(guess != null) {
                    attempts++
                }

                _state.update { it.copy(
                    guessText = when {
                        guess == null -> "Please enter a number."
                        number > guess -> "Nope, my number is larger."
                        number < guess -> "Nope, my number is smaller."
                        else -> "That was it! You needed $attempts attempts."
                    },
                    isGuessCorrect = guess == number,
                    numberText = ""
                ) }
            }
            is NumberGuessAction.OnNumberTextChange -> {
                /*
                    - update is used instead of copy for thread safe updates
                 */
                _state.update { it.copy(
                    numberText = action.numberText
                ) }
            }
            NumberGuessAction.OnStartNewGameButtonClick -> {
                number = Random.nextInt(1, 101)
                _state.update { it.copy(
                    numberText = "",
                    guessText = null,
                    isGuessCorrect = false
                ) }
            }
        }
    }
}