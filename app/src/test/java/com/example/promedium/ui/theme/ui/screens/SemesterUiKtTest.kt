package com.example.promedium.ui.theme.ui.screens

import androidx.navigation.NavController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.promedium.ui.theme.ui.view_model.SemesterViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class SemesterUiKtTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private var viewModel = SemesterViewModel(navController = NavController(context))


    @get:Rule val composeTestRule = createComposeRule()


    @Test
    fun testSemesterView(){

    }
}