package io.github.simonvar.insulter.feature

import com.badoo.mvicore.feature.ActorReducerFeature
import io.github.simonvar.insulter.feature.data.InsultEffect
import io.github.simonvar.insulter.feature.data.InsultNews
import io.github.simonvar.insulter.feature.data.InsultState
import io.github.simonvar.insulter.feature.data.InsultWish

class InsultFeature(state: InsultState,
                    actor: InsultActor,
                    reducer: InsultReducer,
                    newsPublisher: InsultNewsPublisher)
    : ActorReducerFeature<InsultWish, InsultEffect, InsultState, InsultNews>(
    initialState = state,
    actor = actor,
    reducer = reducer,
    newsPublisher = newsPublisher
)