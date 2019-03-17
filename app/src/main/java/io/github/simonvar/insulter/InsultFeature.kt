package io.github.simonvar.insulter

import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ReducerFeature

class InsultFeature : ReducerFeature<InsultWish, InsultState, InsultNews>(
    initialState = InsultState(null, false),
    reducer = InsultReducer
)

object InsultReducer : Reducer<InsultState, InsultWish> {

    override fun invoke(state: InsultState, wish: InsultWish) = when(wish){
        is InsultWish.Insult -> state.copy(text = wish.text, isLoading = false)
        is InsultWish.Loading -> state.copy(text = state.text, isLoading = true)
    }

}

sealed class InsultWish {
    data class Insult(val text: String) : InsultWish()
    object Loading : InsultWish()
}

data class InsultState(
    val text: String?,
    val isLoading: Boolean
)

sealed class InsultNews {
    data class ResponseError(val throwable: Throwable) : InsultNews()
}