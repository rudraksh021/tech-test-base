package com.cleanarch.clean

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.cleanarch.clean.workers.SyncWork
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 * Created by Aanal Shah on 05/12/2023
 */
@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var workManager: WorkManager

    override fun onCreate() {
        super.onCreate()
        workManager.enqueueUniqueWork(
            SyncWork::class.java.simpleName,
            ExistingWorkPolicy.KEEP,
            SyncWork.getOneTimeWorkRequest()
        )
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

}