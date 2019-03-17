package io.github.simonvar.insulter.feature

import com.badoo.mvicore.feature.ActorReducerFeature

class InsultFeature : ActorReducerFeature<InsultWish, InsultEffect, InsultState, InsultNews>(
    initialState = InsultState(null, false),
    actor = InsultActor(),
    reducer = InsultReducer(),
    newsPublisher = InsultNewsPublisher()
)