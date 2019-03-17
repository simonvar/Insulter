package io.github.simonvar.insulter.feature

import com.badoo.mvicore.element.NewsPublisher

class InsultNewsPublisher : NewsPublisher<InsultWish, InsultEffect, InsultState, InsultNews> {
    override fun invoke(wish: InsultWish, effect: InsultEffect, state: InsultState) = when (effect) {
        is InsultEffect.ErrorLoading -> InsultNews.ResponseError(
            effect.throwable
        )
        is InsultEffect.CopiedInsult -> InsultNews.Copied
        else -> null
    }
}