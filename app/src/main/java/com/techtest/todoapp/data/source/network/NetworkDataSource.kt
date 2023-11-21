package com.techtest.todoapp.data.source.network

/**
 * Main entry point for accessing tasks data from the network.
 *
 */
interface NetworkDataSource {

    suspend fun loadTasks(): List<ResultsItem>

    suspend fun saveTasks(tasks: List<NetworkNews>)
}
