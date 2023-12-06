package com.cleanarch.data.util

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by Aanal Shah on 05/12/2023
 **/
interface DispatchersProvider {
    fun getIO(): CoroutineDispatcher
    fun getMain(): CoroutineDispatcher
    fun getMainImmediate(): CoroutineDispatcher
    fun getDefault(): CoroutineDispatcher
}