package com.techtest.todoapp.addedittask

import androidx.compose.material.Surface
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.SavedStateHandle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.techtest.todoapp.R
import com.techtest.todoapp.HiltTestActivity
import com.techtest.todoapp.data.TaskRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

/**
 * Integration test for the Add Task screen.
 */
@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class AddEditTaskScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<HiltTestActivity>()
    private val activity get() = composeTestRule.activity

    @Inject
    lateinit var repository: TaskRepository

    @Before
    fun setup() {
        hiltRule.inject()

        // GIVEN - On the "Add Task" screen.
        composeTestRule.setContent {
            AppCompatTheme {
                Surface {
                    AddEditTaskScreen(
                        viewModel = AddEditTaskViewModel(repository, SavedStateHandle()),
                        topBarTitle = R.string.add_task,
                        onTaskUpdate = { },
                        onBack = { },
                    )
                }
            }
        }
    }

    @Test
    fun emptyTask_isNotSaved() {
        // WHEN - Enter invalid title and description combination and click save
        findTextField(R.string.title_hint).performTextClearance()
        findTextField(R.string.description_hint).performTextClearance()
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.cd_save_task))
            .performClick()

        // THEN - Entered Task is still displayed (a correct task would close it).
        composeTestRule
            .onNodeWithText(activity.getString(R.string.empty_task_message))
            .assertIsDisplayed()
    }

    @Test
    fun validTask_isSaved() = runTest {
        // WHEN - Valid title and description combination and click save
        findTextField(R.string.title_hint).performTextInput("title")
        findTextField(R.string.description_hint).performTextInput("description")
        composeTestRule.onNodeWithContentDescription(activity.getString(R.string.cd_save_task))
            .performClick()

        // THEN - Verify that the repository saved the task
        val tasks = repository.getTasks(true)
        assertEquals(1, tasks.size)
        assertEquals("title", tasks[0].title)
        assertEquals("description", tasks[0].description)
    }

    private fun findTextField(text: Int): SemanticsNodeInteraction {
        return composeTestRule.onNode(
            hasSetTextAction() and hasText(activity.getString(text))
        )
    }
}
