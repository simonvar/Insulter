package io.github.simonvar.insulter.feature

import android.content.Context
import com.badoo.mvicore.feature.ActorReducerFeature
import io.github.simonvar.insulter.feature.data.InsultEffect
import io.github.simonvar.insulter.feature.data.InsultNews
import io.github.simonvar.insulter.feature.data.InsultState
import io.github.simonvar.insulter.feature.data.InsultWish

class InsultFeature(context: Context) : ActorReducerFeature<InsultWish, InsultEffect, InsultState, InsultNews>(
    initialState = InsultState(null, false),
    actor = InsultActor(context),
    reducer = InsultReducer(),
    newsPublisher = InsultNewsPublisher()
)