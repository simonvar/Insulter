package io.github.simonvar.insulter

import android.app.Application
import io.github.simonvar.insulter.api.InsultApiService
import io.github.simonvar.insulter.feature.*
import io.github.simonvar.insulter.feature.data.InsultState
import io.github.simonvar.insulter.services.ClipboardService
import io.github.simonvar.insulter.services.ShareService
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class InsultApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val servicesModule = module {
            factory { ClipboardService(get()) }
            factory { ShareService(get()) }
            factory { InsultApiService("json") }
        }

        val insultModule = module {
            factory { InsultState(null, false, InsultLanguage.EN) }
            factory { InsultActor(get(), get(), get()) }
            factory { InsultReducer() }
            factory { InsultNewsPublisher() }
            single { InsultFeature(get(), get(), get(), get()) }
        }

        startKoin(applicationContext, modules = listOf(servicesModule, insultModule))
    }

}