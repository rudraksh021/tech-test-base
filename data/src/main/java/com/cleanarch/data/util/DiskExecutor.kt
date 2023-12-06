package com.cleanarch.data.util

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by Aanal Shah on 05/12/2023
 */
class DiskExecutor : Executor {

    private val executor: Executor = Executors.newSingleThreadExecutor()

    override fun execute(runnable: Runnable) {
        executor.execute(runnable)
    }
}
