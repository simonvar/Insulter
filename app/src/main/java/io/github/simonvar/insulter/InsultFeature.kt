package io.github.simonvar.insulter

import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ReducerFeature

class InsultFeature : ReducerFeature<ResponseWish, InsultState, Nothing>(
    initialState = InsultState(null, null, false),
    reducer = InsultReducer
)

object InsultReducer : Reducer<InsultState, ResponseWish> {

    override fun invoke(state: InsultState, wish: ResponseWish) = when(wish){
        is ResponseWish.Insult -> state.copy(text = wish.text, errorText = null, isLoading = false)
        is ResponseWish.Error -> state.copy(text = null, errorText = wish.text, isLoading = false)
        is ResponseWish.Loading -> state.copy(text = state.text, errorText = null, isLoading = true)
    }

}

sealed class ResponseWish {
    data class Insult(val text: String) : ResponseWish()
    data class Error(val text: String) : ResponseWish()
    object Loading : ResponseWish()
}

data class InsultState(
    val text: String?,
    val errorText: String?,
    val isLoading: Boolean
)