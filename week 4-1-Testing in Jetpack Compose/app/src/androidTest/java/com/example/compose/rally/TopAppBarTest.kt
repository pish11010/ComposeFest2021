package com.example.compose.rally

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.example.compose.rally.ui.components.RallyTopAppBar
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class TopAppBarTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun rallyTopAppBarTest_currentTabSelected() {
    val allScreens = RallyScreen.values().toList()
    composeTestRule.setContent {
      RallyTopAppBar(
        allScreens = allScreens,
        onTabSelected = { },
        currentScreen = RallyScreen.Accounts
      )
    }

    composeTestRule
      .onNodeWithContentDescription(RallyScreen.Accounts.name)
      .assertIsSelected()
  }

  @Test
  fun rallyTopAppBarTest_currentLabelExists() {
    val allScreens = RallyScreen.values().toList()
    composeTestRule.setContent {
      RallyTopAppBar(
        allScreens = allScreens,
        onTabSelected = { },
        currentScreen = RallyScreen.Accounts
      )
    }

    composeTestRule
      .onNode(
        hasText(RallyScreen.Accounts.name.uppercase()) and
          hasParent(
            hasContentDescription(RallyScreen.Accounts.name)
          ),
        useUnmergedTree = true
      )
      .assertExists()
  }


  @Test
  fun rallyTopAppBarTest_selectTabBills() {
    val currentScreen = mutableStateOf(RallyScreen.Accounts)
    composeTestRule.setContent {
      RallyTopAppBar(
        allScreens = RallyScreen.values().toList(),
        onTabSelected = { currentScreen.value = it },
        currentScreen = currentScreen.value
      )
    }
    composeTestRule
      .onNodeWithContentDescription(RallyScreen.Bills.name)
      .performClick()

    assertEquals(RallyScreen.Bills, currentScreen.value)
  }

  @Test
  fun rallyTopAppBarTest_selectTabOverview() {
    val currentScreen = mutableStateOf(RallyScreen.Accounts)
    composeTestRule.setContent {
      RallyTopAppBar(
        allScreens = RallyScreen.values().toList(),
        onTabSelected = { currentScreen.value = it },
        currentScreen = currentScreen.value
      )
    }

    composeTestRule
      .onNodeWithContentDescription(RallyScreen.Accounts.name)
      .assertIsSelected()

    composeTestRule
      .onNodeWithContentDescription(RallyScreen.Overview.name)
      .performClick()

    assertEquals(RallyScreen.Overview, currentScreen.value)

    composeTestRule
      .onNodeWithContentDescription(RallyScreen.Overview.name)
      .assertIsSelected()
  }
}