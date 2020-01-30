package com.kailashdabhi.obvioustest

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

  @Rule
  @JvmField
  var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

  @Test
  fun mainActivityTest() {
    val actionMenuItemView = onView(
      allOf(
        withId(R.id.action_addNote), withText("Add note"),
        childAtPosition(
          childAtPosition(
            withId(R.id.action_bar),
            1
          ),
          0
        ),
        isDisplayed()
      )
    )
    actionMenuItemView.perform(click())

    val appCompatEditText = onView(
      allOf(
        withId(R.id.editTitle),
        childAtPosition(
          childAtPosition(
            withClassName(`is`("androidx.core.widget.NestedScrollView")),
            0
          ),
          0
        ),
        isDisplayed()
      )
    )
    appCompatEditText.perform(replaceText("Note "), closeSoftKeyboard())

    val appCompatEditText2 = onView(
      allOf(
        withId(R.id.editContent),
        childAtPosition(
          childAtPosition(
            withClassName(`is`("androidx.core.widget.NestedScrollView")),
            0
          ),
          1
        ),
        isDisplayed()
      )
    )
    appCompatEditText2.perform(replaceText("Co"), closeSoftKeyboard())

    val appCompatEditText3 = onView(
      allOf(
        withId(R.id.editTitle), withText("Note "),
        childAtPosition(
          childAtPosition(
            withClassName(`is`("androidx.core.widget.NestedScrollView")),
            0
          ),
          0
        ),
        isDisplayed()
      )
    )
    appCompatEditText3.perform(replaceText("Note 1"))

    val appCompatEditText4 = onView(
      allOf(
        withId(R.id.editTitle), withText("Note 1"),
        childAtPosition(
          childAtPosition(
            withClassName(`is`("androidx.core.widget.NestedScrollView")),
            0
          ),
          0
        ),
        isDisplayed()
      )
    )
    appCompatEditText4.perform(closeSoftKeyboard())

    val appCompatEditText5 = onView(
      allOf(
        withId(R.id.editContent), withText("Co"),
        childAtPosition(
          childAtPosition(
            withClassName(`is`("androidx.core.widget.NestedScrollView")),
            0
          ),
          1
        ),
        isDisplayed()
      )
    )
    appCompatEditText5.perform(replaceText("Content  1"))

    val appCompatEditText6 = onView(
      allOf(
        withId(R.id.editContent), withText("Content  1"),
        childAtPosition(
          childAtPosition(
            withClassName(`is`("androidx.core.widget.NestedScrollView")),
            0
          ),
          1
        ),
        isDisplayed()
      )
    )
    appCompatEditText6.perform(closeSoftKeyboard())

    val appCompatButton = onView(
      allOf(
        withId(R.id.save), withText("Save"),
        childAtPosition(
          childAtPosition(
            withClassName(`is`("androidx.core.widget.NestedScrollView")),
            0
          ),
          2
        ),
        isDisplayed()
      )
    )
    appCompatButton.perform(click())

    val appCompatImageButton = onView(
      allOf(
        withContentDescription("Navegar hacia arriba"),
        childAtPosition(
          allOf(
            withId(R.id.action_bar),
            childAtPosition(
              withId(R.id.action_bar_container),
              0
            )
          ),
          2
        ),
        isDisplayed()
      )
    )
    appCompatImageButton.perform(click())

    val textView = onView(
      allOf(
        withText("Note List"),
        childAtPosition(
          allOf(
            withId(R.id.action_bar),
            childAtPosition(
              withId(R.id.action_bar_container),
              0
            )
          ),
          0
        ),
        isDisplayed()
      )
    )
    textView.check(matches(withText("Note List")))
  }

  private fun childAtPosition(
    parentMatcher: Matcher<View>, position: Int
  ): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("Child at position $position in parent ")
        parentMatcher.describeTo(description)
      }

      public override fun matchesSafely(view: View): Boolean {
        val parent = view.parent
        return parent is ViewGroup && parentMatcher.matches(parent)
            && view == parent.getChildAt(position)
      }
    }
  }
}
