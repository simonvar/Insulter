package io.github.simonvar.insulter

import android.app.Application
import io.github.simonvar.insulter.api.InsultApiService
import io.github.simonvar.insulter.api.InsultRepository
import io.github.simonvar.insulter.feature.InsultFeature
import io.github.simonvar.insulter.feature.executors.InsultActorExecutor
import io.github.simonvar.insulter.feature.executors.InsultActorExecutorImpl
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
            factory <Clipboard> { ClipboardService(get()) }
            factory <Share> { ShareService(get()) }
            factory <InsultRepository> { InsultApiService("json") }
        }

        val insultModule = module {
            factory <InsultActorExecutor> { InsultActorExecutorImpl(get(), get(), get()) }
            single { InsultFeature(get()) }
        }

        startKoin(applicationContext, modules = listOf(servicesModule, insultModule))
    }

}