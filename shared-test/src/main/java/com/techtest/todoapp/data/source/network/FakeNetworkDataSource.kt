package com.techtest.todoapp.data.source.network

class FakeNetworkDataSource(
    var tasks: MutableList<NetworkNews>? = mutableListOf()
) : NetworkDataSource {
    override suspend fun loadTasks() = tasks ?: throw Exception("Task list is null")

    override suspend fun saveTasks(tasks: List<NetworkNews>) {
        this.tasks = tasks.toMutableList()
    }
}
