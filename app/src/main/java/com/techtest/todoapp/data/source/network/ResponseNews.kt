package com.techtest.todoapp.data.source.network

import com.techtest.todoapp.data.source.local.LocalTask


data class ResponseNews(

    val next: String? = null,

    val previous: Any? = null,

    val count: Int? = null,

    val results: List<ResultsItem?>? = null
)

data class ResultsItem(

    val summary: String? = null,

    val newsSite: String? = null,

    val featured: Boolean? = null,

    val updatedAt: String? = null,

    val imageUrl: String? = null,

    val id: Int = 0,

    val title: String? = null,

    val publishedAt: String? = null,

    val url: String? = null,

    val launches: List<Any?>? = null,

    val events: List<Any?>? = null
)

fun ResultsItem.toLocal() = LocalTask(
    id = id.toString(),
    title = title ?: "",
    description = summary ?: "",
    isCompleted = false,
)
