package io.github.simonvar.insulter.feature.transforms

import com.badoo.mvicore.element.NewsPublisher
import io.github.simonvar.insulter.feature.data.InsultEffect
import io.github.simonvar.insulter.feature.data.InsultNews
import io.github.simonvar.insulter.feature.data.InsultState
import io.github.simonvar.insulter.feature.data.InsultWish

class InsultNewsPublisher : NewsPublisher<InsultWish, InsultEffect, InsultState, InsultNews> {

    override fun invoke(wish: InsultWish, effect: InsultEffect, state: InsultState): InsultNews? =
        when (effect) {
            is InsultEffect.ErrorLoading -> InsultNews.ResponseError(
                effect.throwable
            )
            is InsultEffect.CopiedInsult -> InsultNews.Copied
            is InsultEffect.OpenAbout -> InsultNews.About
            else -> null
        }

}