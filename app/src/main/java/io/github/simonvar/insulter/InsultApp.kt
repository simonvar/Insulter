package io.github.simonvar.insulter

import android.app.Application
import io.github.simonvar.insulter.api.InsultApiService
import io.github.simonvar.insulter.api.InsultRepository
import io.github.simonvar.insulter.base.ObserveMainThreadRxComposer
import io.github.simonvar.insulter.feature.data.InsultState
import io.github.simonvar.insulter.feature.models.InsultLanguage
import io.github.simonvar.insulter.feature.transforms.InsultActor
import io.github.simonvar.insulter.feature.InsultFeature
import io.github.simonvar.insulter.feature.transforms.InsultNewsPublisher
import io.github.simonvar.insulter.feature.transforms.InsultReducer
import io.github.simonvar.insulter.services.Clipboard
import io.github.simonvar.insulter.services.ClipboardService
import io.github.simonvar.insulter.services.Share
import io.github.simonvar.insulter.services.ShareService
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module


class InsultApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val servicesModule = module {
            factory { ClipboardService(get()) as Clipboard }
            factory { ShareService(get()) as Share }
            factory { InsultApiService("json") as InsultRepository }
        }

        val insultModule = module {
            factory { InsultState(null, false, InsultLanguage.EN) }
            factory {
                InsultActor(
                    get(),
                    get(),
                    get(),
                    ObserveMainThreadRxComposer()
                )
            }
            factory { InsultReducer() }
            factory { InsultNewsPublisher() }
            single {
                InsultFeature(
                    get(),
                    get(),
                    get(),
                    get()
                )
            }
        }

        startKoin(applicationContext, modules = listOf(servicesModule, insultModule))
    }

}