package com.techtest.todoapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techtest.todoapp.data.source.local.LocalTask
import com.techtest.todoapp.data.source.local.TaskDao

/**
 * The Room Database that contains the Task table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(entities = [LocalTask::class], version = 1, exportSchema = false)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}
